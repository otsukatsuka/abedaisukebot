package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.message.Message;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Map;

public class MessageGeneratorBase<TMessageContent> implements MessageGeneratorInterface {

    private Map<String,Object> parameters;

    MessageGeneratorBase(){}

    MessageGeneratorBase(Map<String, Object> parameters){
        this.parameters = parameters;
    }

    private TMessageContent getMessageContent(){
        return (TMessageContent) parameters.getOrDefault(Consts.Parameters.MessageContent, null);
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
