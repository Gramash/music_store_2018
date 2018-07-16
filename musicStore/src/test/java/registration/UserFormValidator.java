package registration;

import com.epam.garmash.dto.UserDto;
import com.epam.garmash.utils.validator.RegistrationValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserFormValidator {

    private static final String PASS = "Password1";
    private static final String NAME = "Name";
    private static final String LAST_NAME = "Surname";
    private static final String EMAIL = "email@aemail.com";

    private static final String INVALID_NAME = "name1";
    private static final String INVALID_PASS = "pass";
    private static final String INVALID_EMAIL = "emailaemail.com";

    private static final int USER_DTO_FIELDS_NUMBER = 5;

    private UserDto validUser = new UserDto(NAME, LAST_NAME, EMAIL, PASS, PASS);
    private UserDto invalidUser5WrongFields = new UserDto(INVALID_NAME, EMAIL, INVALID_EMAIL, INVALID_PASS, PASS);

    private RegistrationValidator registrationValidator;

    @Before
    public void setUp() {
        registrationValidator = new RegistrationValidator();
    }

    @Test
    public void shouldReturnEmptyMap_IfUserDtoFitsRequirements() {
        Map<String, String> validationResult = registrationValidator.validate(validUser);
        assertTrue(validationResult.isEmpty());
    }

    @Test
    public void shouldReturnMapWithNumberOfErrorsEqualToNumberOfInvalidFieldsInUserDto() {
        Map<String, String> validationResult = registrationValidator.validate(invalidUser5WrongFields);
        assertEquals(validationResult.size(), USER_DTO_FIELDS_NUMBER);
    }


}
