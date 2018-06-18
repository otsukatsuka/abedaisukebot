package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

import java.util.List;

public class TextMessageGenerator extends AbstractMessageGenerator<TextMessageContent> {

    public TextMessageGenerator(TextMessageContent textMessageContent) {
        super(textMessageContent);
    }

    public TextMessageGenerator(){}

    @Override
    public <T extends MessageGeneratorInterface, TMessageContent extends MessageContent> T create(TMessageContent messageContent) {
        return (T) new TextMessageGenerator((TextMessageContent) messageContent);
    }

    @Override
    public Message createMessage() {
        return new TextMessage(getMessageContent().getText());
    }
}
