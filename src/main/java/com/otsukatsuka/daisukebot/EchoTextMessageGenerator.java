package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.otsukatsuka.daisukebot.core.Consts;

import java.util.Map;

public class EchoTextMessageGenerator extends MessageGeneratorBase<TextMessageContent> {

    private EchoTextMessageGenerator(Map<String, Object> parameters) {
        super(parameters);
    }

    @Override
    protected Class<TextMessageContent> getMessageContentClass() {
        return TextMessageContent.class;
    }

    public EchoTextMessageGenerator(){
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
