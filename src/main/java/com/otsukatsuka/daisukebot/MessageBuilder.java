package com.otsukatsuka.daisukebot;

import com.otsukatsuka.daisukebot.Enums.GeneratorType;
import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.message.Message;

import java.util.ArrayList;
import java.util.List;


public class MessageBuilder {

    private List<Message> messageList;

    public static class Builder{

        private List<Message> messageList = new ArrayList<>();
        private List<GeneratorType> generatorTypes = new ArrayList<>();
        private final MessageContent messageContent;

        Builder(MessageContent messageContent){
            this.messageContent = messageContent;
        }

        Builder set(GeneratorType generatorType){
            generatorTypes.add(generatorType);
            return this;
        }

        MessageBuilder build(){
            generatorTypes.forEach(generatorType -> {
                try{
                    Message message = generatorType.of(this.messageContent).createMessage();
                    System.out.println("add message : " + message);
                    addMessage(message);
                }catch (Exception e){
                    System.out.println(e);
                }
            });
            return new MessageBuilder(this);
        }

        private void addMessage(Message message){
            this.messageList.add(message);
        }
    }

    private MessageBuilder(Builder builder){
        this.messageList = builder.messageList;
    }

    public List<Message> getMessageList() {
        System.out.println("messageList : " + this.messageList);
        return this.messageList;
    }

}
