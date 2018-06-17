package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.StickerMessage;

public class StickerMessageGenerator extends AbstractMessageGenerator {

    public StickerMessageGenerator(StickerMessageContent stickerMessageContent) {
        super(stickerMessageContent);
    }

    public StickerMessageGenerator(){

    }

    @Override
    public Message Generate() {
        return new StickerMessage("1","13");
    }
}
