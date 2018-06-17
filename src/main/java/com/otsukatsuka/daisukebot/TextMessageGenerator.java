package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;

public class TextMessageGenerator extends AbstractMessageGenerator {

    public TextMessage EchoTextMessage(TextMessageContent content){
        return new TextMessage(content.getText());
    }
}
