package com.datalinkx.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

public final class RefUtils {
    private RefUtils() { }

    public static List<String> decode(String ref) {
        try {
            return JsonUtils.toList(new String(Base64Utils.decodeBase64(ref), "UTF-8"), String.class);
        } catch (UnsupportedEncodingException e) {
            return new ArrayList<>();
        }
    }

    public static String toTable(String ref) {
        return decode(ref).stream().filter(StringUtils::isNotEmpty).collect(Collectors.joining("."));
    }

    @SneakyThrows
    public static String encode(List<String> infos) {
        return Base64Utils.encodeBase64(JsonUtils.toJson(infos).getBytes());
    }
}
