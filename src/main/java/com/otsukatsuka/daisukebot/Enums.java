package com.otsukatsuka.daisukebot;

import java.util.Map;
import java.util.Objects;

/*
 * Enum管理クラス
 */
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

        public MessageGeneratorInterface of(Map<String, Object> parameters){
            for (GeneratorType generatorType : GeneratorType.values()) {
                if (Objects.equals(generatorType.generatorCode, generatorCode))
                    return generatorType.messageGenerator.createGenerator(parameters);
            }
            throw new IllegalArgumentException("invalid generator");
        }
    }
}
