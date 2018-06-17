package com.otsukatsuka.daisukebot;
import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.message.LocationMessage;
import com.linecorp.bot.model.message.TextMessage;

public class MessageManager<TMessageContent extends MessageContent> {
    private final TMessageContent messageContent;

    public MessageManager(TMessageContent messageContent){
        this.messageContent = messageContent;
    }

    public TextMessage getTextMessage(){
        return new MessageProvider<TextMessage>().Generate(Enums.Message.EchoTextMessage, this.messageContent);
    }

    public LocationMessage getLocationMessage(){
        return new MessageProvider<LocationMessage>().Generate(Enums.Message.EchoLocationMessage, this.messageContent);
    }
}
