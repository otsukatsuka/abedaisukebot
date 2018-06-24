package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.event.message.TextMessageContent;
import com.otsukatsuka.daisukebot.core.Consts;
import com.otsukatsuka.daisukebot.core.Enums.GeneratorType;

import com.linecorp.bot.model.message.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageProvider {

    private Map<String, Object> parameters = new HashMap<>();

    /*
     * 受け取ったテキストメッセージをそのまま返す
     */
    public List<Message> EchoTextMessage(TextMessageContent textMessageContent){
        setParameters(Consts.Parameters.MessageContent, textMessageContent);
        return new MessageBuilder.Builder(getParameters())
                .set(GeneratorType.EchoTextMessage)
                .build()
                .getMessageList();
    }

    /*
     * 受け取ったテキストメッセージをそのまま返してその後
     * 指定されたpackageIdとstickerIdのスタンプをおす
     */
    public List<Message> EchoTextMessageAndSticker(TextMessageContent textMessageContent, String packageId, String stickerId){
        setParameters(Consts.Parameters.MessageContent, textMessageContent);
        setParameters(Consts.Parameters.Sticker.PackageId, packageId);
        setParameters(Consts.Parameters.Sticker.StickerId, stickerId);

        return new MessageBuilder.Builder(getParameters())
                .set(GeneratorType.EchoTextMessage)
                .set(GeneratorType.StickerMessage)
                .build()
                .getMessageList();
    }

    public List<Message> EchoSetTextMessage(String text){
        setParameters(Consts.Parameters.Text.Text, text);
        return new MessageBuilder.Builder(getParameters())
                .set(GeneratorType.EchoTextMessage)
                .build()
                .getMessageList();
    }

    public List<Message> ErrorMessage(){
        setParameters(Consts.Parameters.Sticker.StickerId, 1);
        return new MessageBuilder.Builder(getParameters())
                .set(GeneratorType.StickerMessage)
                .build()
                .getMessageList();
    }

    private void setParameters(String key, Object value){
        parameters.put(key, value);
    }

    private Map<String, Object> getParameters(){
        return this.parameters;
    }
}
