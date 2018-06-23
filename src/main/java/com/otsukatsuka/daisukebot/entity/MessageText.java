package com.otsukatsuka.daisukebot.entity;

import javax.persistence.*;

@Entity
@NamedQuery(name = "selectAll", query = "select * from message_text order by bot_type desc")
public class MessageText {

    @Id
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
