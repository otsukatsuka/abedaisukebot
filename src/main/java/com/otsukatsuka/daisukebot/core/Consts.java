package com.otsukatsuka.daisukebot.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 定数クラス
 */
public class Consts {

    public static final String EmptyString = "";

    public static final String DefaultPackageId = "1";
    public static final int MinStickerId = 1;
    public static final int MaxStickerId = 18;

    public static class Parameters{
        public static final String MessageContent = "MessageContent";

        public static class Text{
            public static final String Text = "Text";
        }

        public static class Sticker{
            public static final String PackageId = "PackageId";
            public static final String StickerId = "StickerId";
        }
    }

    public static class Api{
        public static class GnaviApi{
            public static List<String> Gnavi = new ArrayList<>(Arrays.asList("ぐるなび","ぐるナビ","グルナビ"));

            public static class Url{
                public static String GAreaSmallSearchApiUrl = "https://api.gnavi.co.jp/master/GAreaSmallSearchAPI/20150630/?";
                public static String GnaviRestSearchApiUrl = "https://api.gnavi.co.jp/RestSearchAPI/20150630/?";
                public static String CategorySmallSearchUrl = "https://api.gnavi.co.jp/master/CategorySmallSearchAPI/20150630/?";
            }

            public static class Parameters{
                public static String Apikey = "keyid";
                public static String Format = "format";
                public static String AreaSCode = "areacode_s";
                public static String CategoryCodeS = "category_s";
                public static String FreeWord = "freeword";
                public static String FreeWordCondition = "freeword_condition";
            }

            public static class FormatType{
                public static String Json = "json";
                public static String Xml = "xml";
            }

            public static class FreeWordCondition{
                public static int AND = 1;
                public static int OR = 2;
            }
        }
    }

    public static class bot{
        public static String GnaviNoResultMessage = "見つからなかった！飯屋探したいときは\n" +
                "[場所]で[フリーワード（なくてもいい）]な[店の種類](探して,見つけて,食いたい,etc.)\n" +
                "って言って\n" +
                "例えば\n" +
                "六本木でおしゃれな居酒屋探して\n" +
                "池袋で定食食いたい\n" +
                "とか！";
        public static List<String> beg = new ArrayList<>(Arrays.asList("見つけて", "みつけて", "探して", "さがして", "教えて", "おしえて", "食べたい", "食いたい", "くいたい" , "たべたい"));
        public static List<String> conjunction = new ArrayList<>(Arrays.asList("で", "の", "な"));
    }
}
