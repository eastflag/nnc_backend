package com.eastflag.nnc.common;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Base64;

@Getter
public class DataUri {
    private final Pattern regex = Pattern.compile("^data:(.*?);base64,(.*)", Pattern.CASE_INSENSITIVE);
    private byte[] data;
    private String contentType;

    public DataUri(String uri) {
        Matcher m = regex.matcher(uri);
        if (m.find()) {
            contentType = m.group(1);
            data = Base64.getDecoder().decode(m.group(2));
        } else {
            throw new IllegalArgumentException("Invalid data URI: " + uri);
        }
    }

//    public byte[] getData() {
//        return data;
//    }
//
//    public String getContentType() {
//        return contentType;
//    }
}
