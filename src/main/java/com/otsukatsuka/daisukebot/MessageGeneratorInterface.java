package com.otsukatsuka.daisukebot;
import com.linecorp.bot.model.message.Message;

import java.io.IOException;
import java.util.Map;

public interface MessageGeneratorInterface{
    <TMessageGenerator extends MessageGeneratorInterface> TMessageGenerator createGenerator(Map<String,Object> params);
    Message action() throws IOException;
}

