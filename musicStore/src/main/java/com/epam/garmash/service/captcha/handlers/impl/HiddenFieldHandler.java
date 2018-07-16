package com.epam.garmash.service.captcha.handlers.impl;

import com.epam.garmash.service.captcha.Captcha;
import com.epam.garmash.service.captcha.handlers.CaptchaHandler;
import com.epam.garmash.web.ViewConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

public class HiddenFieldHandler implements CaptchaHandler {

    private Map<String, Captcha> captchaMap;

    public HiddenFieldHandler(Map<String, Captcha> captchaMap) {
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
        captchaMap.put(req.getParameter(ViewConstants.CAPTCHA_ID), captcha);
    }

    @Override
    public boolean checkCaptcha(HttpServletRequest req) {
        return req.getParameter(ViewConstants.CAPTCHA_INPUT_FILED)
                .equals(captchaMap.get(req.getParameter(ViewConstants.CAPTCHA_ID)).getCaptchaValue());
    }

}