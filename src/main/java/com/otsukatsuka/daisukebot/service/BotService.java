package com.otsukatsuka.daisukebot.service;

import com.otsukatsuka.daisukebot.entity.Bot;
import com.otsukatsuka.daisukebot.repository.BotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BotService {

    @Autowired
    BotRepository botRepository;

    public List<Bot> selectAll(){
        List<Bot> list = botRepository.findAll();
        return list;
    }

    public int getBotId(String nickName){
        System.out.println("getBotId : " + nickName);
        System.out.println("botRepository" + botRepository);

        List<Bot> list = botRepository.findAll();

        System.out.println("count : " + String.valueOf(list.size()));

        list.forEach(x -> System.out.println("ニックネーム" + x.getNickname()));

        return list.stream().filter(x -> x.getNickname().equals(nickName)).findFirst().get().getBotId();
    }
}
