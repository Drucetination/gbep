package com.gbep.botapi.bot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class User {
    private Integer id;
    private String userName;
    private Long level;
    private Long numberOfGames;
    private Long chatID;

}
