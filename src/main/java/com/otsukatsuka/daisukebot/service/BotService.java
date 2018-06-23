package com.otsukatsuka.daisukebot.service;
import com.otsukatsuka.daisukebot.entity.Bot;
import com.otsukatsuka.daisukebot.repository.BotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BotService {

    @Autowired
    BotRepository botRepository;

    /*
     * 与えられたテキストからbotIdを判定する
     * 見つからなかったらnull
     */
    public Optional<Integer> getOptionalBotId(String text){

        List<Bot> list = botRepository.findAll();

        Optional<String> containNickName = getContainNickName(list.stream().map(x -> x.getNickname()).collect(Collectors.toList()), text);

        if(!containNickName.isPresent()){
            return Optional.ofNullable(null);
        }

        Optional<Bot> botOptional = list.stream().filter(x -> x.getNickname().equals(containNickName.get())).findFirst();
        return Optional.ofNullable(botOptional.isPresent() ? botOptional.get().getBotId() : null);
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
}
