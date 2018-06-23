package com.otsukatsuka.daisukebot.service;

import com.otsukatsuka.daisukebot.model.Bot;
import com.otsukatsuka.daisukebot.repository.BotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
        List<Bot> list = botRepository.findAll();

        System.out.println("count : " + String.valueOf(list.size()));

        list.forEach(x -> System.out.println("ニックネーム" + x.getNickname()));

        return list.stream().filter(x -> x.getNickname().equals(nickName)).findFirst().get().getBotId();
    }
}
