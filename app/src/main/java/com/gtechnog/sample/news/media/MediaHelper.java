package com.gtechnog.sample.news.media;

public class MediaHelper {

    public static String getMediaFormatStringByMediaType(MediaType mediaType) {
        String format = "";
        switch (mediaType) {
            case MEDIUM_IMAGE:
                format = "mediumThreeByTwo210";
                break;
            case NORMAL_IMAGE:
                format = "Normal";
                break;
            case LARGE_THUMBNAIL:
                format = "thumbLarge";
                break;
            case STANDARD_THUMBNAIL:
                format = "Standard Thumbnail";
                break;
        }
        return format;
    }
}
