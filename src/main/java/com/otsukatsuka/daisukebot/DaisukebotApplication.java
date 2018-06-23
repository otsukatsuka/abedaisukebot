package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.event.message.LocationMessageContent;
import com.linecorp.bot.model.message.LocationMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import com.otsukatsuka.daisukebot.model.Bot;
import com.otsukatsuka.daisukebot.service.BotService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.spring.boot.annotation.EventMapping;

import java.util.List;


@SpringBootApplication
@LineMessageHandler
public class DaisukebotApplication {

	public static void main(String[] args) {
        SpringApplication.run(DaisukebotApplication.class, args);
	}

	@EventMapping
    public List<Message> handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        System.out.println("event: " + event);

        MessageProvider messageProvider = new MessageProvider();

        if(event.getMessage().getText().equals("おはよう")){
            return messageProvider.EchoTextMessageAndSticker(event.getMessage(), "1", "13");
        }
        if(event.getMessage().getText().equals("エラー")){
            return messageProvider.ErrorMessage();
        }

        int botId = new BotService().getBotId(event.getMessage().getText());

        return messageProvider.EchoOptionalTextMessage("botId" + String.valueOf(botId));
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
