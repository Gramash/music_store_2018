package com.epam.garmash.utils.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestParser {

    private static final Pattern parameters = Pattern.compile("(.*?)=(.*?)&");

    public static Map<String, String> getQueryParameters(String request) {
        String modifiedRequest = request + "&";
        Matcher matcher = parameters.matcher(modifiedRequest);
        Map<String, String> result = new HashMap<>();
        while (matcher.find()) {
            result.put(matcher.group(1), matcher.group(2));
        }
        return result;
    }

}
