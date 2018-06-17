package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;

public class MessageProvider<TMessage extends Message> {

    public TMessage Generate(Enums.Message message, MessageContent content) {
        return (TMessage) Create(message, content);
    }

    public TMessage Generate(Enums.Message message){
        return (TMessage) Create(message, null);
    }

    private static Message Create(Enums.Message message, MessageContent content){
        switch (message){
            case EchoTextMessage:
                return new TextMessageGenerator((TextMessageContent) content).Generate();
            case EchoLocationMessage:
                return null;
            case StickerMessage:
                return new StickerMessageGenerator().Generate();
        }
        return null;
    }
}
