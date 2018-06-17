package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

public class TextMessageGenerator extends AbstractMessageGenerator {

    public TextMessageGenerator(TextMessageContent textMessageContent) {
        super(textMessageContent);
    }

    @Override
    public Message Generate() {
        TextMessageContent textMessageContent = (TextMessageContent) getMessageContent();
        return new TextMessage(textMessageContent.getText());
    }
}
