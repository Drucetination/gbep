package com.gbep.botapi.bot.service;

import com.gbep.botapi.bot.model.QuestionResponse;
import com.gbep.botapi.bot.model.User;
import com.gbep.botapi.bot.model.UserConfig;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;
import java.util.Optional;

@Service
public class MainService {

    static private final String MASTER_HOST = "http://localhost:8090/api/";

    @LoadBalanced
    private final RestTemplate restTemplate = new RestTemplate();

    public UserConfig getConfig(String user_id) {
        return restTemplate.getForObject(MASTER_HOST + "/config/" + user_id, UserConfig.class);
    }

    public String getStatistic(Long chatId) {
        String level = restTemplate.getForObject(MASTER_HOST, String.class);
        String numberOfGames = restTemplate.getForObject(MASTER_HOST, String.class);

        String answer = "level: " + level + "\n" + "numberOfGames: " + numberOfGames;
        return answer;
    }

    public String startPlay(String user_id) {
        restTemplate.getForObject(MASTER_HOST + "status/" + user_id + "/CHOOSING_DATASET", Boolean.class);
        return "Введите номер игры, в которую хотите сыграть:\n" +
                "1. Определения\n" +
                "2. Викторина\n" +
                "3. Пропущенные слова\n";
    }

    public String getGameDataset(String gameID, String user_id) {
        String service;
        switch (gameID) {
            case "1":
                service = "DEFINITIONS";
                break;
            case "2":
                service = "QUESTIONS";
                break;
            case "3":
                service = "SENTENCES";
                break;
            default:
                return "ERROR";
        }
        List<String> datasets = restTemplate.getForObject(MASTER_HOST + "service/" + user_id + "/" + service + "/", List.class);
        return "Введите название игры, в которую хотите сыграть: " + datasets.toString();
    }

    public String getGame(String gameID, String user_id) {
        Boolean isExisting = restTemplate.getForObject(MASTER_HOST + user_id + "/new_game/" + gameID + "/", Boolean.class);
        if (Boolean.TRUE.equals(isExisting)) {
            return "Игра сформирована. Напишите ОК чтобы начать. ";

        }
        return "Ошибка";
    }

    public String startGame(String user_id) {
        QuestionResponse question = restTemplate.getForObject(MASTER_HOST + user_id + "/start/", QuestionResponse.class);
        assert question != null;
        if (question.isFinished()) {
            return "Конец игры";
        } else {
            return question.getQuestion();
        }
    }

    public String getQuestion(String user_id) {
        QuestionResponse question = restTemplate.getForObject(MASTER_HOST + user_id + "/play/", QuestionResponse.class);
        assert question != null;
        if (question.isFinished()) {

            return "Конец игры." + finishGame(user_id);
        } else {
            return question.getQuestion();
        }
    }

    public Boolean compareAnswer(String user_id, String answer) {
        return restTemplate.getForObject(MASTER_HOST + user_id + "/answer/" + answer, Boolean.class);
    }

    public String finishGame(String user_id) {
        Boolean won = restTemplate.getForObject(MASTER_HOST + user_id + "/results/", Boolean.class);
        if (Boolean.TRUE.equals(won)) {
            return "Поздравляем с победой!";
        }
        return "Проигрыш";
    }

    public Boolean searchUser(String user_id) {
        return restTemplate.getForObject(MASTER_HOST + "users/" + user_id, Boolean.class);
    }


    public String getHelp() {
        return "Помощь!";

    }

    public String makeNewGame(String user_id) {
        restTemplate.getForObject(MASTER_HOST + "status/" + user_id + "/CHOOSING_NEW_GAME_SERVICE", Boolean.class);
        return "Введите номер игры, для которой хотите создать датасет:\n" +
                "1. Определения\n" +
                "2. Викторина\n" +
                "3. Пропущенные слова\n";
    }

    public String chooseService(String text, String user_id) {
        String service;
        switch (text) {
            case "1":
                service = "DEFINITIONS";
                break;
            case "2":
                service = "QUESTIONS";
                break;
            case "3":
                service = "SENTENCES";
                break;
            default:
                return "ERROR";
        }

        restTemplate.getForObject(MASTER_HOST + "service/change/" + user_id + "/" + service + "/", Boolean.class);
        return "Введите название игры";
    }

    public String chooseName(String text, String service, String user_id) {
        Boolean isFree = restTemplate.getForObject(MASTER_HOST + user_id + "/new_game/" + service + "/" + text, Boolean.class);
        if(Boolean.TRUE.equals(isFree)) {
            return "Имя свободно. Загрузите файл в формате json";
        } else {
            return "Имя занято. Пожалуйста, выберите другое";
        }
    }

    public void stopGame(String user_id) {
        restTemplate.getForObject(MASTER_HOST + "status/" + user_id + "/INITIAL", Boolean.class);
    }

    public Boolean putJson(String path, String user_id) {
        return restTemplate.getForObject(MASTER_HOST + "service/put/" + user_id + "/" + path, Boolean.class);
    }
}
