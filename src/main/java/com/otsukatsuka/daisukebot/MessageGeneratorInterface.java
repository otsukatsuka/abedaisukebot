package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.message.Message;


public interface MessageGeneratorInterface {

    public <T extends MessageGeneratorInterface, TMessageContent extends MessageContent> T create(TMessageContent messageContent);
    public Message createMessage();
}
