package com.gtechnog.sample.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class MediaEntity {

    @SerializedName("url")
    private String url;

    @SerializedName("format")
    private String format;

    @SerializedName("height")
    private int height;

    @SerializedName("width")
    private int width;

    @SerializedName("type")
    private String type;

    @SerializedName("subtype")
    private String subType;

    @SerializedName("caption")
    private String caption;

    @SerializedName("copyright")
    private String copyright;

    public String getUrl() {
        return url;
    }

    public String getFormat() {
        return format;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaEntity that = (MediaEntity) o;
        return height == that.height &&
                width == that.width &&
                Objects.equals(url, that.url) &&
                Objects.equals(format, that.format) &&
                Objects.equals(type, that.type) &&
                Objects.equals(subType, that.subType) &&
                Objects.equals(caption, that.caption) &&
                Objects.equals(copyright, that.copyright);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, format, height, width, type, subType, caption, copyright);
    }

    @Override
    public String toString() {
        return "MediaEntity{" +
                "url='" + url + '\'' +
                ", format='" + format + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", type='" + type + '\'' +
                ", subType='" + subType + '\'' +
                ", caption='" + caption + '\'' +
                ", copyright='" + copyright + '\'' +
                '}';
    }
}
