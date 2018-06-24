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

    public enum GnaviApiParam{
        APIKEY(Consts.Api.GnaviApi.Parameters.Apikey),
        Format(Consts.Api.GnaviApi.Parameters.Format);

        private final String param;

        GnaviApiParam(final String param){
            this.param = param;
        }

        public String getParam(){
            return this.param;
        }
    }

    public enum GnaviApiFormatType{
        Json(Consts.Api.GnaviApi.FormatType.Json),
        Xml(Consts.Api.GnaviApi.FormatType.Xml);

        private final String formatType;

        GnaviApiFormatType(final String formatType){
            this.formatType = formatType;
        }

        public String getFormatType(){
            return this.formatType;
        }
    }
}
