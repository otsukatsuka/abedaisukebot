package com.otsukatsuka.daisukebot.repository;

import com.otsukatsuka.daisukebot.entity.MessageText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageTextRepository extends JpaRepository<MessageText,Integer>, JpaSpecificationExecutor<MessageText> {
    @Query(name = "selectAll")
    public List<MessageText> selectAll();
}
