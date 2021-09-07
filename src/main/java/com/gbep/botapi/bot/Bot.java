package com.gbep.botapi.bot;

import com.gbep.botapi.bot.handlers.MainMenuHandler;
import com.gbep.botapi.bot.service.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class Bot extends TelegramLongPollingBot {
    @Autowired
    MainService mainService;
    @Override
    public void onUpdateReceived(Update update) {


        Boolean insidePlay = false;
        Boolean step2 = false;
        Boolean step3 = false;
        // We check if the update has a message and the message has text
           if (update.hasMessage() && update.getMessage().hasText()) {

            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            switch (update.getMessage().getText()) {
                case "/help":
                    message.setText(mainService.getHelp());
                    break;
                case "/statistic":
                    message.setText(mainService.getStatistic(update.getMessage().getChatId()));
                    break;
                case "/playNewGame":
                    message.setText(mainService.startPlay());
                    insidePlay = true;
                    break;
                case "/makeNewGame":
                    message.setText(mainService.makeNewGame(update.getMessage().getChatId()));
                    break;
                default:
                    break;
            }
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            if(insidePlay){
                SendMessage message1 = new SendMessage(); // Create a SendMessage object with mandatory fields
                message1.setChatId(update.getMessage().getChatId().toString());
                message1.setText(mainService.getGameDataset(update.getMessage().getText()));
                step2 = true;
                try {
                    execute(message); // Call method to send the message
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                if(step2){
                    SendMessage message2 = new SendMessage(); // Create a SendMessage object with mandatory fields
                    message2.setChatId(update.getMessage().getChatId().toString());
                    message2.setText(mainService.getGame(update.getMessage().getText()));
                    step3 = true;
                    try {
                        execute(message); // Call method to send the message
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                if(step3){
                    SendMessage message2 = new SendMessage(); // Create a SendMessage object with mandatory fields
                    message2.setChatId(update.getMessage().getChatId().toString());
                    message2.setText(mainService.getQuestion(update.getMessage().getText()));
                    step3 = true;
                    try {
                        execute(message); // Call method to send the message
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
               insidePlay = step2 = step3 = false;

            }
        }

    }

    @Override
    public String getBotUsername() {
        return "@gbep_bot";
    }

    @SneakyThrows
    @Override
    public String getBotToken() {
        BufferedReader br = new BufferedReader(new FileReader("src/config.txt"));
        String line = br.readLine();
        return line;
    }

}
