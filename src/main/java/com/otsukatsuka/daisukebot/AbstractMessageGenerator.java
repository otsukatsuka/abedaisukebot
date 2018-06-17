package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.message.Message;

public abstract class AbstractMessageGenerator<TMessageContent> {
    private TMessageContent messageContent;

    public AbstractMessageGenerator(TMessageContent messageContent){
        this.messageContent = messageContent;
    }

    public AbstractMessageGenerator(){
    }

    public TMessageContent getMessageContent(){
        return this.messageContent;
    }

    public abstract Message Generate();
}
