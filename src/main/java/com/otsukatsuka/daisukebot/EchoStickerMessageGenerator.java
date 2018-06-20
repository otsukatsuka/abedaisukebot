package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.StickerMessage;

public class EchoStickerMessageGenerator extends MessageGeneratorBase<StickerMessageContent> {

    public EchoStickerMessageGenerator(StickerMessageContent stickerMessageContent){
        super(stickerMessageContent);
    }

    public EchoStickerMessageGenerator(){}

    @Override
    public <TMessageGenerator extends MessageGeneratorInterface> TMessageGenerator generate(MessageContent messageContent) {
        StickerMessageContent content = as(messageContent,StickerMessageContent.class);
        return (TMessageGenerator) new EchoStickerMessageGenerator(content);
    }

    @Override
    protected Message generateFromMessageEvent(StickerMessageContent stickerMessageContent) {
        return new StickerMessage(stickerMessageContent.getPackageId(), stickerMessageContent.getStickerId());
    }

    @Override
    protected Message generate() {
        return new StickerMessage("1", "13");
    }
}
