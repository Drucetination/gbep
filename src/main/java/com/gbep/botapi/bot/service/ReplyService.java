package com.gbep.botapi.bot.service;


import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class ReplyService {


    public SendMessage getReplyMessage(String chatId, String replyMessage) {
        return new SendMessage(chatId, replyMessage);
    }


}
