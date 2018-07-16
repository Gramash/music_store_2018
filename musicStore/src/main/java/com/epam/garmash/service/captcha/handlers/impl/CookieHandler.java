package com.epam.garmash.service.captcha.handlers.impl;

import com.epam.garmash.service.captcha.Captcha;
import com.epam.garmash.service.captcha.handlers.CaptchaHandler;
import com.epam.garmash.web.ViewConstants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

public class CookieHandler implements CaptchaHandler {

    private Map<String, Captcha> captchaMap;

    public CookieHandler(Map<String, Captcha> captchaMap) {
        this.captchaMap = captchaMap;
    }

    @Override
    public String init() {
        String temp = UUID.randomUUID().toString();
        captchaMap.put(temp, new Captcha());
        return temp;
    }

    @Override
    public void storeCaptcha(HttpServletRequest req, HttpServletResponse resp, Captcha captcha) {
        resp.addCookie(new Cookie(ViewConstants.CAPTCHA_COOKIE, req.getParameter(ViewConstants.CAPTCHA_ID)));
        captchaMap.replace(req.getParameter(ViewConstants.CAPTCHA_ID), captcha);
    }

    @Override
    public boolean checkCaptcha(HttpServletRequest req) {
        String cookieID = getCaptchaIdFromCookie(req.getCookies());
        return req.getParameter(ViewConstants.CAPTCHA_INPUT_FILED)
                .equals(captchaMap.get(cookieID).getCaptchaValue());
    }

    private String getCaptchaIdFromCookie(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(ViewConstants.CAPTCHA_COOKIE)) {
                return cookie.getValue();
            }
        }
        return "";
    }
}