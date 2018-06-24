package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.event.message.LocationMessageContent;
import com.linecorp.bot.model.message.LocationMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import com.otsukatsuka.daisukebot.api.GnaviApiClient;
import com.otsukatsuka.daisukebot.config.ConfigReader;
import com.otsukatsuka.daisukebot.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.spring.boot.annotation.EventMapping;

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

	@EventMapping
    public List<Message> handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        System.out.println("event: " + event);

        if(event.getMessage().getText().equals("gnavi")){
            GnaviApiClient gnaviApiClient = GnaviApiClient.getInstance();
            gnaviApiClient.getGAreaSmallSearchJson();
            return null;
        }

        Optional<String> messageOptional = botService.getReplyTextMessage(event.getMessage().getText());
        if(!messageOptional.isPresent())
            return null;

        MessageProvider messageProvider = new MessageProvider();

        return messageProvider.EchoSetTextMessage(messageOptional.get());
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
