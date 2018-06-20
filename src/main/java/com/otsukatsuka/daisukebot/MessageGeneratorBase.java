package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.message.Message;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MessageGeneratorBase<TMessageContent> implements MessageGeneratorInterface {

    private TMessageContent messageContent;

    protected MessageGeneratorBase(){}

    protected MessageGeneratorBase(TMessageContent messageContent){
        this.messageContent = messageContent;
    }

    private boolean hasSameMessageEvent(){
        return this.messageContent != null;
    }

    protected <T> T as(MessageContent content, Class<T> clazz){
        if(!clazz.isInstance(content)){
            return null;
        }
        return clazz.cast(content);
    }

    protected Message generateFromMessageEvent(TMessageContent messageContent){
        throw new NotImplementedException();
    }

    protected Message generate(){
        throw new NotImplementedException();
    }

    @Override
    public <TMessageGenerator extends MessageGeneratorInterface> TMessageGenerator generate(MessageContent messageContent) {
        return null;
    }

    @Override
    public Message action() {
        if(hasSameMessageEvent()){
            return generateFromMessageEvent(this.messageContent);
        }
        return generate();
    }
}
