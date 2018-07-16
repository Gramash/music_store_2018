package com.epam.garmash.service.captcha.handlers;

import com.epam.garmash.service.captcha.Captcha;
import com.epam.garmash.service.captcha.handlers.impl.CookieHandler;
import com.epam.garmash.service.captcha.handlers.impl.HiddenFieldHandler;
import com.epam.garmash.service.captcha.handlers.impl.SessionHandler;

import java.util.HashMap;
import java.util.Map;

public class HandlerFactory {

    private static final String COOKIE = "cookie";
    private static final String SESSION = "session";
    private static final String HIDDEN_FIELD = "hidden field";

    private Map<String, CaptchaHandler> captchaHandlerMap = new HashMap<>();

    public HandlerFactory(Map<String, Captcha> captchaMap) {
        captchaHandlerMap.put(COOKIE, new CookieHandler(captchaMap));
        captchaHandlerMap.put(SESSION, new SessionHandler());
        captchaHandlerMap.put(HIDDEN_FIELD, new HiddenFieldHandler(captchaMap));
    }

    public CaptchaHandler createHandler(String initParam) {
        return captchaHandlerMap.get(initParam);
    }

}