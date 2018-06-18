package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.StickerMessage;

public class StickerMessageGenerator extends AbstractMessageGenerator<StickerMessageContent> {

    public StickerMessageGenerator(StickerMessageContent stickerMessageContent) {
        super(stickerMessageContent);
    }

    public StickerMessageGenerator(){
    }
    @Override
    public <T extends MessageGeneratorInterface, TMessageContent extends MessageContent> T create(TMessageContent messageContent) {
        return (T) new StickerMessageGenerator((StickerMessageContent) messageContent);
    }

    @Override
    public Message createMessage() {
        return new StickerMessage("1","13");
    }
}
