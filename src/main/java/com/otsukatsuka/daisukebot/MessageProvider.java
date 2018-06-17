package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;

public class MessageProvider<TMessage extends Message> implements IMessageGenerator<TMessage> {
    @Override
    public TMessage Generate(Enums.Message message, MessageContent content) {
        return (TMessage) Create(message, content);
    }

    private Message Create(Enums.Message message, MessageContent content){
        switch (message){
            case EchoTextMessage:
                return new TextMessageGenerator().EchoTextMessage((TextMessageContent) content);
            case EchoLocationMessage:
                return null;
        }
        return null;
    }
}
