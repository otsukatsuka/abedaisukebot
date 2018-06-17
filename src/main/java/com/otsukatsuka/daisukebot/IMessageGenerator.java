package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.message.Message;

public interface IMessageGenerator<TMessage extends Message> {
    TMessage Generate(Enums.Message message, MessageContent content);
}
