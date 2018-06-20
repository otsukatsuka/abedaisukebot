package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

public class EchoTextMessageGenerator extends MessageGeneratorBase<TextMessageContent> {

    private EchoTextMessageGenerator(TextMessageContent textMessageContent) {
        super(textMessageContent);
    }

    public EchoTextMessageGenerator(){}

    @Override
    public <TMessageGenerator extends MessageGeneratorInterface> TMessageGenerator generate(MessageContent messageContent) {
        TextMessageContent textMessageContent = as(messageContent, TextMessageContent.class);
        return (TMessageGenerator) new EchoTextMessageGenerator(textMessageContent);
    }

    @Override
    protected Message generateFromMessageEvent(TextMessageContent textMessageContent) {
        return new TextMessage(textMessageContent.getText());
    }

    @Override
    protected Message generate() {
        return new TextMessage("やあ！");
    }
}
