package com.epam.garmash.service.captcha;


import com.epam.garmash.service.CaptchaCleaner;
import com.epam.garmash.service.captcha.handlers.CaptchaHandler;
import com.epam.garmash.service.captcha.handlers.impl.SessionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

public class CaptchaService {

    private static final int expirationTimeMinutes = 1;
    private static final int captchaLength = 6;
    private static final String CAPTCHA_EXPIRED = "Your captcha has expired";
    private static final String WRONG_CAPTCHA = "Wrong captcha";
    private static final String FONT_NAME = "Arial";
    private static final int FONT_SIZE = 30;

    private CaptchaHandler captchaHandler;
    private Captcha captcha;


    public CaptchaService(CaptchaHandler captchaHandler) {
        this.captchaHandler = captchaHandler;
    }

    //TODO
    public String setUpCaptcha() {
        return captchaHandler.init();
    }

    public Captcha storeCaptcha(HttpServletRequest req, HttpServletResponse resp) {
        captcha = createCaptcha(generateCaptchaValue(captchaLength));
        captchaHandler.storeCaptcha(req, resp, captcha);
        return captcha;
    }

    public String checkCaptcha(HttpServletRequest req) {
        String checkResult = "";
        if (captcha.isExpired()) {
            return CAPTCHA_EXPIRED;
        }
        if (!captchaHandler.checkCaptcha(req)) {
            captcha.setExpiredTime(LocalDateTime.MIN);
            checkResult = WRONG_CAPTCHA;
        }
        return checkResult;
    }

    public void startCaptchaCleaner(Map<String, Captcha> captchaMap) {
        if (!(captchaHandler instanceof SessionHandler))
            new CaptchaCleaner(captchaMap, expirationTimeMinutes).start();

    }

    private Captcha createCaptcha(String captchaValue) {
        int iTotalChars = 6;
        int iHeight = 55;
        int iWidth = 150;

        Font fntStyle1 = new Font(FONT_NAME, Font.BOLD, FONT_SIZE);
        Random randChars = new Random();
        BufferedImage biImage = new BufferedImage(iWidth, iHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2dImage = (Graphics2D) biImage.getGraphics();

        g2dImage.setFont(fntStyle1);

        for (int i = 0; i < iTotalChars; i++) {
            g2dImage.setColor(new Color(randChars.nextInt(255), randChars.nextInt(255), randChars.nextInt(255)));
            if (i % 2 == 0) {
                g2dImage.drawString(captchaValue.substring(i, i + 1), 25 * i, 24);
            } else {
                g2dImage.drawString(captchaValue.substring(i, i + 1), 25 * i, 35);
            }
        }
        return new Captcha(biImage, captchaValue, LocalDateTime.now().plusMinutes(expirationTimeMinutes));
    }

    private String generateCaptchaValue(int iTotalChars) {
        Random randChars = new Random();
        return (Long.toString(Math.abs(randChars.nextLong()), 36)).substring(0, iTotalChars);
    }

}