package com.otsukatsuka.daisukebot.service;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.otsukatsuka.daisukebot.EchoGnaviRestMessageGenerator;
import com.otsukatsuka.daisukebot.api.GnaviApiClient;
import com.otsukatsuka.daisukebot.core.Consts;
import com.otsukatsuka.daisukebot.entity.Bot;
import com.otsukatsuka.daisukebot.entity.MessageText;
import com.otsukatsuka.daisukebot.repository.BotRepository;
import com.otsukatsuka.daisukebot.repository.MessageTextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BotService {

    @Autowired
    BotRepository botRepository;

    @Autowired
    MessageTextRepository messageTextRepository;

    @Autowired
    GnaviApiClient gnaviApiClient;

    private List<MessageText> getAllReplyMessageByBotType(int botType){
        List<MessageText> messageTextList = messageTextRepository.findAll();
        return messageTextList.stream().filter(x -> x.getBotType() == botType).collect(Collectors.toList());
    }

    /*
     * 与えられたテキストからbotIdを判定する
     * 見つからなかったらnull
     */
    private Optional<Integer> getOptionalBotType(String text){

        List<Bot> list = botRepository.findAll();

        Optional<String> containNickName = getContainNickName(list.stream().map(x -> x.getNickname()).collect(Collectors.toList()), text);

        if(!containNickName.isPresent()){
            return Optional.ofNullable(null);
        }

        Optional<Bot> botOptional = list.stream().filter(x -> x.getNickname().equals(containNickName.get())).findFirst();
        return Optional.ofNullable(botOptional.isPresent() ? botOptional.get().getBotType() : null);
    }

    private Optional<String> getContainNickName(List<String> nickNameList, String text){

        String botNickName = "";

        for(String nickname : nickNameList){
            if(text.contains(nickname)){
                botNickName = nickname;
                break;
            }
        }

        return Optional.ofNullable(botNickName);
    }

    /*
     * 受け取ったメッセージから返信内容を決めて返す
     */
    public List<Message> getReplyTextMessage(String receivedText){
        Optional<Integer> botType = getOptionalBotType(receivedText);
        List<Message> messages = new ArrayList<>();

        try {
            List<Message> gnavi = gnaviApiClient.GnaviRestList(receivedText);
            return gnavi;
        }catch (Exception e){

            System.out.println(e);

            if(!botType.isPresent()){

                for(String str : Consts.bot.beg){
                    if(receivedText.contains(str)){
                        messages.add(new TextMessage(Consts.bot.GnaviNoResultMessage));
                        messages.add(new StickerMessage("1","10"));
                    }

                }
                return messages;
            }

            List<MessageText> messageTextList = getAllReplyMessageByBotType(botType.get());

            Collections.shuffle(messageTextList);

            Optional<String> message = messageTextList.stream().map(x -> x.getMessage()).findFirst();

            if(message.isPresent()){
                messages.add(new TextMessage(message.get()));

                int randomNumber = (int)(Math.random() * 10) + 1;
                if(randomNumber % 2 == 0){
                    int stickerId = new Random().nextInt(Consts.MaxStickerId - 1);
                    messages.add(new StickerMessage(Consts.DefaultPackageId, Integer.toString( stickerId + 1)));
                }
            }
            return messages;
        }
    }

}
