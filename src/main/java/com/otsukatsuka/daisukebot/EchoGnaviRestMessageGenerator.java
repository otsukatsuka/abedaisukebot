package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.otsukatsuka.daisukebot.api.GnaviApiClient;
import com.otsukatsuka.daisukebot.api.result.GnaviRestSearchResult;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EchoGnaviRestMessageGenerator extends MessageGeneratorBase<TextMessageContent> {

    private EchoGnaviRestMessageGenerator(Map<String, Object> parameters){
        super(parameters);
    }

    public EchoGnaviRestMessageGenerator(){}
    @Override
    protected Class<TextMessageContent> getMessageContentClass() {
        return TextMessageContent.class;
    }

    @Override
    public <TMessageGenerator extends MessageGeneratorInterface> TMessageGenerator createGenerator(Map<String, Object> parameters) {
        return (TMessageGenerator) new EchoGnaviRestMessageGenerator(parameters);
    }

    @Override
    protected Message createFromMessageEvent(TextMessageContent textMessageContent){
        throw new NotImplementedException();
    }



    @Override
    protected Message create(Map<String,Object> parameters){
        throw new NotImplementedException();
    }
}
