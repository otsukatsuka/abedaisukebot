package com.otsukatsuka.daisukebot.repository;
import com.otsukatsuka.daisukebot.entity.Bot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BotRepository extends JpaRepository<Bot,Integer>, JpaSpecificationExecutor<Bot> {

}