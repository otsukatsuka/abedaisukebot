package com.otsukatsuka.daisukebot;

import com.otsukatsuka.daisukebot.core.Consts;
import com.otsukatsuka.daisukebot.core.Enums.GeneratorType;
import com.linecorp.bot.model.message.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MessageBuilder {

    private List<Message> messageList;

    static class Builder{

        private List<Message> messageList = new ArrayList<>();
        private List<GeneratorType> generatorTypes = new ArrayList<>();
        private Map<String, Object> parameters;

        Builder(Map<String, Object> parameters){
            this.parameters = parameters;
        }

        Builder set(GeneratorType generatorType){
            generatorTypes.add(generatorType);
            return this;
        }

        MessageBuilder build() throws IOException {
            try {
                generatorTypes.forEach(generatorType -> {
                    Message message = null;
                    try {
                        message = generatorType.of(this.parameters).action();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    addMessage(message);
                });
            }catch (Exception e){
                System.out.println("Error !!! : " + e);
                Map<String, Object> parameters = new HashMap<String, Object>(){
                    {
                        put(Consts.Parameters.Text.Text, e.getMessage());
                    }
                };
                Message message = GeneratorType.EchoTextMessage.of(parameters).action();
                addMessage(message);
            }
            return new MessageBuilder(this);
        }

        private void addMessage(Message message){
            this.messageList.add(message);
        }
    }

    private MessageBuilder(Builder builder){
        this.messageList = builder.messageList;
    }

    List<Message> getMessageList() {
        return this.messageList;
    }

}
