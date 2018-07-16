package com.epam.garmash.web.servlets;

import com.epam.garmash.beans.User;
import com.epam.garmash.dto.UserDto;
import com.epam.garmash.exception.imageUpload.ImageSizeException;
import com.epam.garmash.exception.imageUpload.ImageUploadException;
import com.epam.garmash.service.ImageUploader;
import com.epam.garmash.service.captcha.CaptchaService;
import com.epam.garmash.service.user.UserService;
import com.epam.garmash.utils.validator.RegistrationValidator;
import com.epam.garmash.web.AppContextConstants;
import com.epam.garmash.web.Messages;
import com.epam.garmash.web.Paths;
import com.epam.garmash.web.ViewConstants;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@WebServlet("/signup")
@MultipartConfig(maxRequestSize = 1024 * 1024 * 2)
public class SignUpServlet extends HttpServlet {

    private static final String EMPTY = "empty";

    private UserService userService;
    private RegistrationValidator registrationValidator;
    private CaptchaService captchaService;
    private ImageUploader imageUploader;
    private String picturePath;

    @Override
    public void init(ServletConfig config) {
        registrationValidator = new RegistrationValidator();
        userService = (UserService) config.getServletContext().getAttribute(AppContextConstants.USER_SERVICE);
        captchaService = (CaptchaService) config.getServletContext().getAttribute(AppContextConstants.CAPTCHA_SERVICE);
        imageUploader = new ImageUploader();
        picturePath = config.getServletContext().getInitParameter(AppContextConstants.AVATAR_PATH);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ViewConstants.NAME, req.getSession().getAttribute(ViewConstants.NAME));
        req.getSession().removeAttribute(ViewConstants.NAME);
        req.setAttribute(ViewConstants.LASE_NAME, req.getSession().getAttribute(ViewConstants.LASE_NAME));
        req.getSession().removeAttribute(ViewConstants.LASE_NAME);
        req.setAttribute(ViewConstants.EMAIL, req.getSession().getAttribute(ViewConstants.EMAIL));
        req.getSession().removeAttribute(ViewConstants.EMAIL);
        req.setAttribute(ViewConstants.VALIDATION_ERRORS, req.getSession().getAttribute(ViewConstants.VALIDATION_ERRORS));
        req.getSession().removeAttribute(ViewConstants.VALIDATION_ERRORS);

        req.getRequestDispatcher(Paths.HOME_SERVLET).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Part part;
        try {
            part = req.getPart(ViewConstants.PICTURE);
        } catch (IllegalStateException e) {
            throw new ImageSizeException("Image size exceeded maximum of 2 mb", e);
        }
        UserDto signUpForm = UserDto.createSignUpForm(req);

        Map<String, String> errorsMap = registrationValidator.validate(signUpForm);
        try {
            imageUploader.validate(part);
        } catch (ImageUploadException e) {
            errorsMap.put(Messages.IMG_UPLOAD, e.getMessage());
        }

        String avatarName = setAvatarName(part, signUpForm);

        String captchaError;
        if (!(captchaError = captchaService.checkCaptcha(req)).isEmpty()) {
            errorsMap.put(ViewConstants.CAPTCHA, captchaError);
        }

        User user;
        if (errorsMap.isEmpty()) {
            user = signUpForm.createUser();
            if (userService.addIfUnique(user)) {
                uploadImageToStorage(picturePath, avatarName, part);
                resp.sendRedirect(Paths.HOME_SERVLET);
                return;
            } else {
                errorsMap.put(Messages.SORRY, Messages.EMAIL_TAKEN);
            }
        }
        req.getSession().setAttribute(ViewConstants.VALIDATION_ERRORS, errorsMap);
        req.getSession().setAttribute(ViewConstants.NAME, signUpForm.getName());
        req.getSession().setAttribute(ViewConstants.LASE_NAME, signUpForm.getLastName());
        req.getSession().setAttribute(ViewConstants.EMAIL, signUpForm.getEmail());

        resp.sendRedirect(Paths.SIGN_UP_SERVLET);
    }

    private String setAvatarName(Part part, UserDto registrationForm) {
        Optional<String> avatarName = imageUploader.getImageName(part, registrationForm.getEmail());
        if (avatarName.isPresent()) {
            registrationForm.setAvatar(avatarName.get());
            return avatarName.get();
        } else {
            registrationForm.setAvatar(EMPTY);
            return EMPTY;
        }
    }

    private void uploadImageToStorage(String path, String name, Part part) {
        try {
            imageUploader.uploadFile(path, name, part);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}