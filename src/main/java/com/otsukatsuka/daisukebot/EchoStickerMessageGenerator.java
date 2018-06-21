package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.StickerMessage;

import java.util.Map;

public class EchoStickerMessageGenerator extends MessageGeneratorBase<StickerMessageContent> {

    private EchoStickerMessageGenerator(Map<String, Object> parameters){
        super(parameters);
    }

    EchoStickerMessageGenerator(){}

    @Override
    public <TMessageGenerator extends MessageGeneratorInterface> TMessageGenerator createGenerator(Map<String, Object> parameters) {
        return (TMessageGenerator) new EchoStickerMessageGenerator(parameters);
    }

    @Override
    protected Message createFromMessageEvent(StickerMessageContent stickerMessageContent) {
        return new StickerMessage(stickerMessageContent.getPackageId(), stickerMessageContent.getStickerId());
    }

    protected Message create(Map<String,Object> parameters){
        String packageId = (String) parameters.getOrDefault(Consts.Parameters.Sticker.PackageId, "1");
        String stickerId = (String) parameters.getOrDefault(Consts.Parameters.Sticker.StickerId, "1");

        return new StickerMessage(packageId, stickerId);
    }
}
