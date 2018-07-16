package com.epam.garmash.utils.validator;

import com.epam.garmash.dto.UserDto;

import java.util.HashMap;
import java.util.Map;

public class RegistrationValidator {

    private static final String namePattern = "^[A-Z]([a-z]+)$";
    private static final String emailPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
    private static final String passPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
    private static final String NAME_KEY = "Name";
    private static final String NAME_MESSAGE = "Please input correct name";
    private static final String LAST_NAME_KEY = "Last Name";
    private static final String LAST_NAME_MESSAGE = "Please input correct last name";
    private static final String EMAIL_KEY = "E-mail";
    private static final String EMAIL_MESSAGE = "please input correct email";
    private static final String PASS_KEY = "Password";
    private static final String PASS_VALUE = "Password should meed the specified pattern";
    private static final String PASS_CHECK_KEY = "Password confirmation";
    private static final String PASS_CHECK_VALUE = "Your passwords do not match";

    public Map<String, String> validate(UserDto userDto) {
        Map<String, String> errors = new HashMap<>();
        if (!userDto.getName().matches(namePattern)) {
            errors.put(NAME_KEY, NAME_MESSAGE);
        }
        if (!userDto.getLastName().matches(namePattern)) {
            errors.put(LAST_NAME_KEY, LAST_NAME_MESSAGE);
        }
        if (!userDto.getEmail().matches(emailPattern)) {
            errors.put(EMAIL_KEY, EMAIL_MESSAGE);
        }
        if (!userDto.getPass().matches(passPattern)) {
            errors.put(PASS_KEY, PASS_VALUE);
        }
        if (!userDto.getPass().equals(userDto.getPassCheck())) {
            errors.put(PASS_CHECK_KEY, PASS_CHECK_VALUE);
        }
        return errors;
    }

}
