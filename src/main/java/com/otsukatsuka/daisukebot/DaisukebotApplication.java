package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.event.message.LocationMessageContent;
import com.linecorp.bot.model.message.LocationMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;


@SpringBootApplication
@LineMessageHandler
public class DaisukebotApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaisukebotApplication.class, args);
	}

    @EventMapping
    public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        System.out.println("event: " + event);

        if(event.getMessage().getText().equals("スタンプ")){
            return new MessageProvider<StickerMessage>().Generate(Enums.Message.StickerMessage);
        }
        return new MessageProvider<TextMessage>().Generate(Enums.Message.EchoTextMessage, event.getMessage());
    }

    @EventMapping
    public LocationMessage handleLocationMessageEvent(MessageEvent<LocationMessageContent> event){
	    System.out.println("event: " + event);
	    return new LocationMessage("ここにいるよ", event.getMessage().getAddress(), event.getMessage().getLatitude(), event.getMessage().getLongitude());
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
