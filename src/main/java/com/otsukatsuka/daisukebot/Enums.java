package com.otsukatsuka.daisukebot;

import com.linecorp.bot.model.event.message.MessageContent;

import java.util.Objects;

public class Enums {

    public enum GeneratorType {
        EchoTextMessage(0,new EchoTextMessageGenerator()),
        StickerMessage(1,new EchoStickerMessageGenerator());

        private final MessageGeneratorInterface messageGenerator;
        private final int generatorCode;

        GeneratorType(int generatorCode, MessageGeneratorInterface messageGenerator) {
            this.generatorCode = generatorCode;
            this.messageGenerator = messageGenerator;
        }

        public MessageGeneratorInterface of(MessageContent messageContent){
            for (GeneratorType generatorType : GeneratorType.values()) {
                if (Objects.equals(generatorType.generatorCode, generatorCode))
                    return generatorType.messageGenerator.generate(messageContent);
            }
            throw new IllegalArgumentException("invalid generator");
        }
    }
}
