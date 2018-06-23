package com.otsukatsuka.daisukebot.entity;

import javax.persistence.*;

@Entity
@Table(name = "message_text")
public class MessageText {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;

    @Column(name = "bot_type")
    private int botType;

    @Column(name = "message")
    private String message;

    public int getMessageId(){
        return this.messageId;
    }

    public void setMessageId(int messageId){
        this.message = message;
    }

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
