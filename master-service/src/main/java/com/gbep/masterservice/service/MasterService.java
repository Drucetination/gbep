package com.gbep.masterservice.service;

import com.gbep.masterservice.VO.Pair;
import com.gbep.masterservice.VO.Microservice;
import com.gbep.masterservice.entity.QuestionResponse;
import com.gbep.masterservice.VO.Status;
import com.gbep.masterservice.VO.UserConfig;
import com.gbep.masterservice.repository.UserConfigRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Service
public class MasterService {
    @Autowired
    private UserConfigRepo repo;

    @Autowired
    private RestTemplate restTemplate;


    public boolean createNewGame(String name, String user_id) {
        if (repo.findById(user_id).isPresent()) {
            UserConfig user = repo.findById(user_id).get();

            user.setStatus(Status.WAITING_START);
            user.setDataset_name(name);
            user.setNext_question(0);

            List<String> questions = restTemplate.getForObject("http://" + user.getService().toString() + "/" + name + "/",
                    List.class);
            user.setQuestions(questions);

            repo.save(user);

            return true;
        }

        return false;
    }

    public List<String> getListOfDatasets(String ms_name, String user_id) {
        if (repo.findById(user_id).isPresent()) {
            UserConfig user = repo.findById(user_id).get();
            user.setService(Microservice.valueOf(ms_name));
            user.setStatus(Status.WAITING_GAME);

            repo.save(user);
        }
        return restTemplate.getForObject("http://" + ms_name + "/all", List.class);
    }

    public QuestionResponse getQuestion(String user_id) {
        if (repo.findById(user_id).isPresent()) {
            UserConfig user = repo.findById(user_id).get();
            int question_no = user.getNext_question();

            if (question_no == user.getQuestions().size()) {
                endGame(user_id);
                return new QuestionResponse(true, "Игра завершена");
            }

            String question_id = user.getQuestions().get(question_no);

            Microservice s = user.getService();
            Pair questions = s.getQuestion(restTemplate, user.getDataset_name(), question_id);

            user.setStatus(Status.WAITING_ANSWER);
            repo.save(user);

            return new QuestionResponse(false, questions.getSentence());
        } else {
            return new QuestionResponse(true, "");
        }
    }

    public void endGame(String user_id) {
        if (repo.findById(user_id).isPresent()) {
            UserConfig user = repo.findById(user_id).get();
            user.setNext_question(0);
            user.setCorrect_answered(0);

            user.setWonLast(countRating(user));

            user.setStatus(Status.FINISHED_GAME);
            user.setDataset_name("");
            repo.save(user);
        }

    }

    public boolean countRating(UserConfig user) {
        // SEND TO USER DB NEW RATING
        return user.getCorrect_answered() >= user.getQuestions().size() * 0.75;
    }


    public boolean getResults(String user_id) {
        if (repo.findById(user_id).isPresent()) {
            UserConfig user = repo.findById(user_id).get();
            user.setStatus(Status.INITIAL);
            repo.save(user);
            return user.isWonLast();
        } else {
            return false;
        }
    }

    public boolean registerUser(String user_id) {
        Optional<UserConfig> uc = repo.findUserConfigByUserid(user_id);
        if (uc.isEmpty()) {
            UserConfig new_uc = new UserConfig(user_id);
            repo.insert(new_uc);

            return true;

            // ADD USER TO USERSERVICE
        }

        return false;
    }

    public Boolean compareAnswer(String user_id, String answer) {
        if (repo.findById(user_id).isPresent()) {
            UserConfig user = repo.findById(user_id).get();

            Microservice s = user.getService();
            Pair question = s.getQuestion(restTemplate, user.getDataset_name(), user.getQuestions().get(user.getNext_question()));

            user.setNext_question(user.getNext_question() + 1);
            user.setStatus(Status.WAITING_QUESTION);

            if (Objects.equals(question.getWord(), answer)) {
                user.setCorrect_answered(user.getCorrect_answered() + 1);
                repo.save(user);
                return true;
            }

            repo.save(user);
            return false;

        }
        return false;
    }

    public UserConfig getConfig(String user_id) {
        Optional<UserConfig> cf = repo.findUserConfigByUserid(user_id);
        return cf.orElseGet(UserConfig::new);
    }

    public Boolean changeInitStatus(String user_id, String status) {
        if (repo.findById(user_id).isPresent()) {
            UserConfig user = repo.findById(user_id).get();
            user.setStatus(Status.valueOf(status));
            repo.save(user);
            return true;
        }
        return false;
    }

    public Boolean findByName(String user_id, String service, String name) {
        Boolean isFree = restTemplate.getForObject("https://" + service + "/check_name/" + name, Boolean.class);
        if (Boolean.TRUE.equals(isFree)) {
            if (repo.findById(user_id).isPresent()) {
                UserConfig user = repo.findById(user_id).get();
                user.setDataset_name(name);
                user.setStatus(Status.SENDING_FILE);
                user.setService(Microservice.valueOf(service));

                repo.save(user);
                return true;
            }

        }

        return false;
    }

    public Boolean changeService(String user_id, String service) {
        if (repo.findById(user_id).isPresent()) {
            UserConfig user = repo.findById(user_id).get();
            user.setService(Microservice.valueOf(service));
            user.setStatus(Status.CREATING_GAME_NAME);
            repo.save(user);
            return true;
        }
        return false;
    }


    public Boolean putJson(String full_path, String user_id) {
        if (repo.findById(user_id).isPresent()) {
            UserConfig user = repo.findById(user_id).get();
            Microservice s = user.getService();
            Boolean posted = s.postDataset(restTemplate, full_path);
            if (posted) {
                user.setStatus(Status.INITIAL);

            }

            repo.save(user);
            return posted;
        }
        return false;
    }
}
