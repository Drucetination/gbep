package com.gbep.botapi.bot.service;

public class StatisticService {
    public String getStatistic(Long chatId){
        String level;
        String numberOfGames;
        level = "5";
        numberOfGames = "10";
        String answer = "level: " + level +"\n"+ "numberOfGames: " + numberOfGames;
        return answer;
    }
}
