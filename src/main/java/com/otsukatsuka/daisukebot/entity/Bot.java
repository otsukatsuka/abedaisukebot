package com.otsukatsuka.daisukebot.entity;

import javax.persistence.*;

@Entity
@Table(name = "bot")
public class Bot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int botId;

    @Column(name = "bot_type")
    private int botType;

    @Column(name = "nickname")
    private String nickname;


    public int getBotId(){
        return this.botId;
    }

    public void setBotId(int botId){
        this.botId = botId;
    }

    public int getBotType(){
        return this.botType;
    }

    public void setBotType(int botType){
        this.botType = botType;
    }

    public String getNickname(){
        return this.nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }
}
