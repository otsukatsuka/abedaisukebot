package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

import java.util.Map;

public class EchoTextMessageGenerator extends MessageGeneratorBase<TextMessageContent> {

    private EchoTextMessageGenerator(Map<String, Object> parameters) {
        super(parameters);
        this.clazz = TextMessageContent.class;
    }

    EchoTextMessageGenerator(){
        this.clazz = TextMessageContent.class;
    }

    @Override
    public <TMessageGenerator extends MessageGeneratorInterface> TMessageGenerator createGenerator(Map<String, Object> parameters) {
        return (TMessageGenerator) new EchoTextMessageGenerator(parameters);
    }

    @Override
    protected Message createFromMessageEvent(TextMessageContent textMessageContent) {
        return new TextMessage(textMessageContent.getText());
    }

    @Override
    protected Message create(Map<String,Object> parameters) {
        String text = (String) parameters.getOrDefault(Consts.Parameters.Text.Text, "");
        return new TextMessage(text);
    }
}
