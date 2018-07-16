package com.epam.garmash.web.servlets;

import com.epam.garmash.beans.User;
import com.epam.garmash.web.AppContextConstants;
import com.epam.garmash.web.ViewConstants;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/image")
public class AvatarServlet extends HttpServlet {

    private static final String RESPONSE_CONTENT_TYPE = "image/jpg";
    private static final String DEFAULT_IMAGE = "default.jpg";
    private static final String PRODUCT_IMAGE = "productImage";
    private static final Pattern IMAGE_FORMAT = Pattern.compile("\\.[0-9a-z]+$.*$");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType(RESPONSE_CONTENT_TYPE);
        User user = (User) req.getSession().getAttribute(ViewConstants.SIGNED_IN_USER);

        String imageName;
        String imagePath;

        if (req.getParameter(PRODUCT_IMAGE) != null) {
            imageName = req.getParameter(PRODUCT_IMAGE);
            imagePath = getServletContext().getInitParameter(AppContextConstants.PRODUCT_IMAGE_PATH);
        } else {
            imagePath = getServletContext().getInitParameter(AppContextConstants.AVATAR_PATH);
            imageName = user.getAvatar();
        }

        File file = new File(imagePath + imageName);
        if (!file.exists()) {
            file = new File(imagePath + DEFAULT_IMAGE);
            if (!file.exists()) {
                return;
            }
        }

        try (BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
             BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            BufferedImage bufferedImage = ImageIO.read(bis);
            ImageIO.write(bufferedImage, getImageFormat(imageName), bos);
        }
    }

    private String getImageFormat(String imageName) {
        Matcher matcher = IMAGE_FORMAT.matcher(imageName);
        return matcher.find() ? matcher.group().substring(1) : "";
    }

}
