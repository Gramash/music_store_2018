package com.epam.garmash.service;

import com.epam.garmash.service.captcha.Captcha;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.Map;

public class CaptchaCleaner extends Thread {

    private static final Logger LOG = Logger.getLogger(CaptchaCleaner.class);

    private Map<String, Captcha> captchaMap;
    private long expirationIntervalMilliseconds;

    public CaptchaCleaner(Map<String, Captcha> captchaMap, int expirationIntervalMinutes) {
        this.captchaMap = captchaMap;
        this.expirationIntervalMilliseconds = expirationIntervalMinutes * 60000;
    }

    @Override
    public void run() {
        while (true) {
            LOG.debug("captcha map cleaner thread started");
            cleanExpired();
            try {
                LOG.debug("cleaner thread is sleeping");
                sleep(expirationIntervalMilliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void cleanExpired() {
        if (captchaMap.isEmpty()) {
            LOG.trace("captchaMap is empty. Nothing to clear");
            return;
        }
        Iterator<Map.Entry<String, Captcha>> iterator = captchaMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Captcha captcha = iterator.next().getValue();
            if (captcha.isExpired()) {
                LOG.debug("cleaner thread removed expired captcha" + captcha.getCaptchaValue());
                iterator.remove();
            }
        }
    }


}