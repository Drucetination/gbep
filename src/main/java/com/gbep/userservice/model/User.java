package com.gbep.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    private Integer id;
    @Column(name = "name")
    private String userName;
    private Long level;
    @Column(name = "Games")
    private Long numberOfGames;
    @Column(name = "Chat")
    private Integer chatID;

    public User() {
    }

}
