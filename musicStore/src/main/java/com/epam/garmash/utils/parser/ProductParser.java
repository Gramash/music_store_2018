package com.epam.garmash.utils.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductParser {

    private static final Pattern GET_PRODUCT_FIELDS = Pattern.compile("\\w+=[\\w\\.\\s]+");

    public static Map<String, String> parseFieldsValues(String productToString) {
        Matcher matcher = GET_PRODUCT_FIELDS.matcher(productToString);
        Map<String, String> productFieldValue = new HashMap<>();
        while (matcher.find()) {
            String field = matcher.group().split("=")[0];
            String value = matcher.group().split("=")[1];
            productFieldValue.put(field, value);
        }
        return productFieldValue;
    }

}
