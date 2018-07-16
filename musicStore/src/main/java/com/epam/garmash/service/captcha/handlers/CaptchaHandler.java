package com.epam.garmash.service.captcha.handlers;

import com.epam.garmash.service.captcha.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * An interface that provides contract for classes
 * that are to handle captcha initiation, storing,
 * and validation.
 */
public interface CaptchaHandler {

    /**
     * Performs all necessary preparations to further
     * work with captcha - generation of captcha ID and
     * placing it in the storage with a "dummy" object
     * that is to be replaced with and actual Captcha {@link Captcha}
     * in the further stage.
     *
     * @return generated captcha ID
     */
    String init();

    /**
     * Stores captcha object {@link Captcha} in a specific storage,
     * that may vary depending on the implementation,
     * under a key obtained form {@link #init()} method.
     * Puts captcha ID value in web content so that in can be obtained
     * there in order to validate its actual value with the input.
     *
     * @param req     HttpRequest that is used to obtain captcha ID
     * @param resp    HttpResponse to pass captcha ID value to web context
     * @param captcha captcha instance to put it in the local storage under the corresponding ID
     */
    void storeCaptcha(HttpServletRequest req, HttpServletResponse resp, Captcha captcha);

    /**
     * Checks whether actual captcha value taken from the local
     * storage by corresponding captcha ID is equal to the users input.
     *
     * @param req HttpRequest to get captcha id, that is used to take
     *            actual captcha value, and users input.
     * @return boolean representation of the validation result.
     */
    boolean checkCaptcha(HttpServletRequest req);


}