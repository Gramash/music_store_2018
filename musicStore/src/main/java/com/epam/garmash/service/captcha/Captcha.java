package com.epam.garmash.service.captcha;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class Captcha {

    private BufferedImage captchaImg;
    private String captchaValue;
    private LocalDateTime expiredTime;

    public Captcha() {
        expiredTime = LocalDateTime.now().plusMinutes(5);
    }

    public Captcha(BufferedImage captchaImg, String captchaValue, LocalDateTime expiredTime) {
        this.captchaImg = captchaImg;
        this.captchaValue = captchaValue;
        this.expiredTime = expiredTime;
    }

    public BufferedImage getCaptchaImg() {
        return captchaImg;
    }

    public void setCaptchaImg(BufferedImage captchaImg) {
        this.captchaImg = captchaImg;
    }

    public String getCaptchaValue() {
        return captchaValue;
    }

    public void setCaptchaValue(String captchaValue) {
        this.captchaValue = captchaValue;
    }

    public LocalDateTime getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(LocalDateTime expiredTime) {
        this.expiredTime = expiredTime;
    }

    public boolean isExpired() {
        LocalDateTime now = LocalDateTime.now();
        return now.isEqual(expiredTime) || now.isAfter(expiredTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Captcha that = (Captcha) o;

        if (captchaImg != null ? !captchaImg.equals(that.captchaImg) : that.captchaImg != null) return false;
        if (captchaValue != null ? !captchaValue.equals(that.captchaValue) : that.captchaValue != null) return false;
        return expiredTime != null ? expiredTime.equals(that.expiredTime) : that.expiredTime == null;
    }

    @Override
    public int hashCode() {
        int result = captchaImg != null ? captchaImg.hashCode() : 0;
        result = 31 * result + (captchaValue != null ? captchaValue.hashCode() : 0);
        result = 31 * result + (expiredTime != null ? expiredTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Captcha{" +
                "captchaImg=" + captchaImg +
                ", captchaValue='" + captchaValue + '\'' +
                ", expiredTime=" + expiredTime +
                '}';
    }

}
