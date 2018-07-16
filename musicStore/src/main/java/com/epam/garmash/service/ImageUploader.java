package com.epam.garmash.service;

import com.epam.garmash.exception.imageUpload.ImageUploadException;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ImageUploader {

    private static final Logger LOG = Logger.getLogger(ImageUploader.class);
    private static final String WRONG_FILE_TYPE = "Please select file of image type";
    private static final String FILENAME_PATTERN = "(filename=\"[^\\.]*)(\\.\\w+)";
    private static final String LOG_PART_HEADER = "Part Header = {0}";
    private static final String CONTENT_DISPOSITION = "content-disposition";

    public void uploadFile(String name, String path, Part part) throws IOException {
        File image = new File(path + name);
        part.write(image.getAbsolutePath());
    }

    public Optional<String> getImageName(Part part, String name) {
        Optional<String> result = Optional.empty();
        if (part != null) {
            String extension = getExtension(part);
            result = Optional.of(name + extension);
        }
        return result;
    }

    public void validate(Part part) {
        BufferedImage image = null;
        InputStream partInputStream = null;
        ByteArrayInputStream fileInputStream = null;
        try {
            partInputStream = part.getInputStream();
            byte[] imageBytes = new byte[partInputStream.available()];
            partInputStream.read(imageBytes);

            fileInputStream = new ByteArrayInputStream(imageBytes);
            image = ImageIO.read(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fileInputStream);
            IOUtils.closeQuietly(partInputStream);
        }
        if (image == null) {
            throw new ImageUploadException(WRONG_FILE_TYPE);
        }
    }


    private String getHeader(final Part part) {
        String partHeader = part.getHeader(CONTENT_DISPOSITION);
        LOG.debug(LOG_PART_HEADER + partHeader);
        return partHeader;
    }

    private String getExtension(Part part) {
        String result = "";
        String input = getHeader(part);
        Matcher m = Pattern.compile(FILENAME_PATTERN).matcher(input);
        while (m.find()) {
            result = m.group(2);
        }
        return result;
    }

}