package com.otsukatsuka.daisukebot;

public abstract class AbstractMessageGenerator<TMessageContent> implements MessageGeneratorInterface {
    private TMessageContent messageContent;

    protected AbstractMessageGenerator(TMessageContent messageContent){
        this.messageContent = messageContent;
    }

    protected AbstractMessageGenerator(){
    }

    public TMessageContent getMessageContent(){
        return this.messageContent;
    }
}
