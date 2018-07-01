package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.event.message.LocationMessageContent;
import com.linecorp.bot.model.message.LocationMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import com.otsukatsuka.daisukebot.api.GnaviApiClient;
import com.otsukatsuka.daisukebot.api.result.GnaviRestResult;
import com.otsukatsuka.daisukebot.api.result.GnaviRestSearchResult;
import com.otsukatsuka.daisukebot.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.spring.boot.annotation.EventMapping;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@SpringBootApplication
@LineMessageHandler
public class DaisukebotApplication {

	public static void main(String[] args) {
        SpringApplication.run(DaisukebotApplication.class, args);
	}

	@Autowired
    BotService botService;

	@Autowired
    GnaviApiClient gnaviApiClient;

	@EventMapping
    public List<Message> handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws IOException, InstantiationException, IllegalAccessException {
        System.out.println("event: " + event);
        return botService.getReplyTextMessage(event.getMessage().getText());
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
