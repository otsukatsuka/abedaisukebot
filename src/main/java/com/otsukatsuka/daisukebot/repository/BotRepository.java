package com.otsukatsuka.daisukebot.repository;
import com.otsukatsuka.daisukebot.model.Bot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotRepository extends JpaRepository<Bot,Integer> {

}
