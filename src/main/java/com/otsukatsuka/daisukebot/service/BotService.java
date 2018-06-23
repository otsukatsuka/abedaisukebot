package com.otsukatsuka.daisukebot.service;
import com.otsukatsuka.daisukebot.entity.Bot;
import com.otsukatsuka.daisukebot.entity.MessageText;
import com.otsukatsuka.daisukebot.repository.BotRepository;
import com.otsukatsuka.daisukebot.repository.MessageTextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BotService {

    @Autowired
    BotRepository botRepository;

    @Autowired
    MessageTextRepository messageTextRepository;

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

    public Optional<String> getReplayTextMessage(String receivedText){
        Optional<Integer> botType = getOptionalBotType(receivedText);

        if(!botType.isPresent()){
            return Optional.ofNullable(null);
        }

        List<MessageText> messageTextList = getAllReplyMessageByBotType(botType.get());

        Collections.shuffle(messageTextList);

        return messageTextList.stream().map(x -> x.getMessage()).findFirst();
    }
}
