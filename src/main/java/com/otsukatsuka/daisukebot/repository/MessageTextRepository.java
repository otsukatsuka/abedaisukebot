package com.otsukatsuka.daisukebot.repository;

import com.otsukatsuka.daisukebot.entity.MessageText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MessageTextRepository extends JpaRepository<MessageText,Integer>, JpaSpecificationExecutor<MessageText> {
}
