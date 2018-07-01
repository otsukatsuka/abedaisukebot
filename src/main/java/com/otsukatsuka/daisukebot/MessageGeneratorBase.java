package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.message.Message;
import com.otsukatsuka.daisukebot.core.Consts;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.Map;

public abstract class MessageGeneratorBase<TMessageContent> implements MessageGeneratorInterface {

    private Map<String,Object> parameters;

    protected abstract Class<TMessageContent> getMessageContentClass();

    MessageGeneratorBase(){}

    MessageGeneratorBase(Map<String, Object> parameters){
        this.parameters = parameters;
    }

    protected TMessageContent getMessageContent(){
        TMessageContent messageContent = as(parameters.getOrDefault(Consts.Parameters.MessageContent, null), getMessageContentClass());
        return messageContent;
    }
    private boolean hasSameMessageEvent(){
        return getMessageContent() != null;
    }

    protected Message createFromMessageEvent(TMessageContent messageContent) throws IOException {
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
            return createFromMessageEvent(getMessageContent());
        }
        return create(parameters);
    }
}
