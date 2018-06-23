package com.otsukatsuka.daisukebot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "message_text")
public class MessageText {
    @Column(name = "bot_type")
    private int botType;

    @Column(name = "message")
    private String message;

    public int getBotType(){
        return this.botType;
    }

    public void setBotType(int botType){
        this.botType = botType;
    }

    public String getMessage(){
        return this.message;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
