package com.gbep.botapi.bot;

import com.gbep.botapi.bot.model.UserConfig;
import com.gbep.botapi.bot.service.*;
import lombok.SneakyThrows;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Bot extends TelegramLongPollingBot {
    MainService mainService = new MainService();
    String next;

    private void sentMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onUpdateReceived(Update update) {

        if (update.getMessage().getDocument() != null) {
            String doc_id = update.getMessage().getDocument().getFileId();
            String doc_name = update.getMessage().getDocument().getFileName();
            String doc_mine = update.getMessage().getDocument().getMimeType();
            int doc_size = update.getMessage().getDocument().getFileSize();
            String getID = String.valueOf(update.getMessage().getFrom().getId());

            Document document = new Document();
            document.setMimeType(doc_mine);
            document.setFileName(doc_name);
            document.setFileSize(doc_size);
            document.setFileId(doc_id);

            GetFile getFile = new GetFile();
            getFile.setFileId(document.getFileId());
            String path = "data/" + getID +"_" + doc_name;
            try {
                org.telegram.telegrambots.meta.api.objects.File file = execute(getFile);

                downloadFile(file, new File(path));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            String user_id = update.getMessage().getFrom().getUserName();
            UserConfig cf = mainService.getConfig(user_id);
            Boolean created = mainService.putJson(getID +"_" + doc_name, user_id);
            if (created) {
                message.setText("Датасет создан!");
            } else {
                message.setText("Проблемы при создании, проверьте что вы загрузили корректный файл");
            }

            sentMessage(message);

        }

        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            String user_id = update.getMessage().getFrom().getUserName();
            UserConfig cf = mainService.getConfig(user_id);
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());

            if (Objects.equals(text, "/stop")) {
                mainService.stopGame(user_id);
            }


            switch (cf.getStatus()) {
                case INITIAL:
                    switch (text) {
                        case "/start":
                            mainService.searchUser(user_id);
                            SendMessage init_message = new SendMessage();
                            init_message.setChatId(update.getMessage().getChatId().toString());
                            init_message.setText("/help для получния справки\n /statistics для получения вашей статистики\n /playNewGame для начала новой игры \n /makeNewGame для загрузки нового датасета");
                            try {
                                execute(init_message); // Call method to send the message
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                            break;

                        case "/help":
                            message.setText(mainService.getHelp());
                            sentMessage(message);
                            break;
                        case "/statistic":
                            message.setText(mainService.getStatistic(update.getMessage().getChatId()));
                            sentMessage(message);
                            break;
                        case "/playNewGame":
                            message.setText(mainService.startPlay(user_id));
                            sentMessage(message);
                            break;
                        case "/makeNewGame":
                            message.setText(mainService.makeNewGame(user_id));
                            sentMessage(message);
                            break;
                    }
                    break;
                case CHOOSING_NEW_GAME_SERVICE:
                    message.setText(mainService.chooseService(update.getMessage().getText(), user_id));
                    sentMessage(message);
                case CREATING_GAME_NAME:
                    message.setText(mainService.chooseName(update.getMessage().getText(), cf.getService().toString(), user_id));
                    sentMessage(message);
                    break;
                case SENDING_FILE:
                    String doc_id = update.getMessage().getDocument().getFileId();
                    String doc_name = update.getMessage().getDocument().getFileName();
                    String doc_mine = update.getMessage().getDocument().getMimeType();
                    int doc_size = update.getMessage().getDocument().getFileSize();
                    String getID = String.valueOf(update.getMessage().getFrom().getId());

                    Document document = new Document();
                    document.setMimeType(doc_mine);
                    document.setFileName(doc_name);
                    document.setFileSize(doc_size);
                    document.setFileId(doc_id);

                    GetFile getFile = new GetFile();
                    getFile.setFileId(document.getFileId());
                    try {
                        org.telegram.telegrambots.meta.api.objects.File file = execute(getFile);
                        downloadFile(file, new File(getID+"_" + doc_name));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
                case CHOOSING_DATASET:
                    message.setText(mainService.getGameDataset(update.getMessage().getText(), user_id));
                    sentMessage(message);
                    break;
                case WAITING_GAME:
                    message.setText(mainService.getGame(update.getMessage().getText(), user_id));
                    sentMessage(message);
                    break;
                case WAITING_START:
                    next = mainService.startGame(user_id);
                    message.setText(next);
                    sentMessage(message);
                    break;
                case WAITING_ANSWER:
                    Boolean correct = mainService.compareAnswer(user_id, update.getMessage().getText());

                    String reply;
                    if (correct) {
                        reply = "Верный ответ";
                    } else {
                        reply = "Неверный ответ";
                    }
//                    break;
//                case WAITING_QUESTION:
                    next = mainService.getQuestion(user_id);
                    message.setText(reply + '\n' + next);
                    sentMessage(message);
                    break;
            }
        }

    }


    @Override
    public String getBotUsername() {
        return "gbep_bot";
    }

    @SneakyThrows
    @Override
    public String getBotToken() {

        Scanner scanner = new Scanner(new File("config.txt"));
        String data = scanner.nextLine();

        scanner.close();
        return data;
    }

}
