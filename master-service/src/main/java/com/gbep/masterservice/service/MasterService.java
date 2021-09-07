package com.gbep.masterservice.service;

import com.gbep.masterservice.entity.Microservice;
import com.gbep.masterservice.entity.QuestionResponse;
import com.gbep.masterservice.entity.Status;
import com.gbep.masterservice.entity.UserConfig;
import com.gbep.masterservice.repository.UserConfigRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

            user.setService(Microservice.valueOf(name));
            user.setStatus(Status.WAITING_GAME);
            user.setDataset_name(name);
            user.setNext_question(0);

            List<String> questions = restTemplate.getForObject("http://" + name + "/sentences/", List.class);
            user.setQuestions(questions);

            repo.save(user);

            return true;
        }

        return false;
    }

    public List<String> getListOfDatasets(String ms_name) {
        return restTemplate.getForObject("http://" + ms_name + "/all", List.class);
    }

    public QuestionResponse getQuestion(String user_id) {
        if (repo.findById(user_id).isPresent()) {
            UserConfig user = repo.findById(user_id).get();
            int question_no = user.getNext_question();


            String question_id = user.getQuestions().get(question_no);

            Microservice s = user.getService();
            String questions = s.getQuestion(restTemplate, user.getDataset_name(), question_id);

            user.setNext_question(user.getNext_question() + 1);


            if (question_no == user.getQuestions().size()) {
                endGame(user_id);
                return new QuestionResponse(true, questions);
            }

            return new QuestionResponse(false, questions);
        } else {
            return new QuestionResponse(true, "");
        }
    }

    public void endGame(String user_id) {
        if (repo.findById(user_id).isPresent()) {
            UserConfig user = repo.findById(user_id).get();
            user.setNext_question(0);

            user.setWonLast(countRating(user));

            user.setStatus(Status.FINISHED_GAME);
            user.setDataset_name("");
        }

    }

    public boolean countRating(UserConfig user) {
        // SEND TO USER DB NEW RATING
        return user.getCorrect_answered() >= user.getQuestions().size() * 0.75;
    }


    public boolean getResults(String user_id) {
        if (repo.findById(user_id).isPresent()) {
            UserConfig user = repo.findById(user_id).get();
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
}
