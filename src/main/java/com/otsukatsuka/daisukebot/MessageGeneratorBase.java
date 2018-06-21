package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.message.Message;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Map;

public class MessageGeneratorBase<TMessageContent> implements MessageGeneratorInterface {

    private Map<String,Object> parameters;

    MessageGeneratorBase(){}

    MessageGeneratorBase(Map<String, Object> parameters){
        this.parameters = parameters;
    }

    protected TMessageContent getMessageContent(){
        TMessageContent messageContent = (TMessageContent) parameters.getOrDefault(Consts.Parameters.MessageContent, null);
        return messageContent;
    }
    private boolean hasSameMessageEvent(){
        return getMessageContent() != null;
    }

    protected Message createFromMessageEvent(TMessageContent messageContent){
        throw new NotImplementedException();
    }

    protected Message create(Map<String,Object> parameters){
        throw new NotImplementedException();
    }

    protected <T> T as(Object obj, Class<T> clazz) {
        if( !clazz.isInstance(obj) ) {
            return null;
        }
        return clazz.cast(obj);
    }


    @Override
    public <TMessageGenerator extends MessageGeneratorInterface> TMessageGenerator createGenerator(Map<String, Object> parameters) {
        return null;
    }

    @Override
    public Message action() {
        if(hasSameMessageEvent()){
            System.out.println("start action " + "this : " +  this.getClass() + " message content : " + getMessageContent().getClass());
            Message message = createFromMessageEvent(getMessageContent());
            System.out.println("end action");
            return message;
        }
        return create(parameters);
    }
}
