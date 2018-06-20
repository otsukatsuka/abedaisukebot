package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.message.Message;

public interface MessageGeneratorInterface{

    <TMessageGenerator extends MessageGeneratorInterface> TMessageGenerator generate(MessageContent messageContent);
    Message action();
}

