package registration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import com.epam.garmash.service.captcha.Captcha;
import com.epam.garmash.service.captcha.CaptchaService;
import com.epam.garmash.service.captcha.handlers.HandlerFactory;
import com.epam.garmash.web.ViewConstants;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CaptchaService.class)
public class CaptchaValidator {

    private static final String HIDDEN_FIELD_INIT_PARAM = "hidden field";
    private static final String WRONG_CAPTCHA_VALUE = "";
    private static final String WRONG_CAPTCHA_MESSAGE = "Wrong captcha";
    private static final String CAPTCHA_EXPIRED_MESSAGE = "Your captcha has expired";

    private Map<String, Captcha> captchaMap = new HashMap<>();
    private CaptchaService captchaService;
    private Captcha EXPIRED_CAPTCHA = new Captcha();
    private HttpServletRequest requestMock;

    @Before
    public void setUp() {
        requestMock = mock(HttpServletRequest.class);
        EXPIRED_CAPTCHA.setExpiredTime(LocalDateTime.MIN);
        HandlerFactory handlerFactory = new HandlerFactory(captchaMap);
        captchaService = new CaptchaService(handlerFactory.createHandler(HIDDEN_FIELD_INIT_PARAM));
    }

    @Test
    public void shouldReturnEmptyString_WhenCaptchaCheckIsSuccessful() {
        when(requestMock.getParameter(ViewConstants.CAPTCHA_ID)).thenReturn("123");
        captchaService.storeCaptcha(requestMock, null);

        when(requestMock.getParameter(ViewConstants.CAPTCHA_INPUT_FILED)).thenReturn(captchaMap.get("123").getCaptchaValue());

        assertEquals("", captchaService.checkCaptcha(requestMock));
    }

    @Test
    public void shouldReturnErrorMessage_WhenCaptchaInputCaptchaValueIsWrong() {
        when(requestMock.getParameter(ViewConstants.CAPTCHA_ID)).thenReturn("123");
        captchaService.storeCaptcha(requestMock, null);

        when(requestMock.getParameter(ViewConstants.CAPTCHA_INPUT_FILED)).thenReturn(WRONG_CAPTCHA_VALUE);

        assertEquals(WRONG_CAPTCHA_MESSAGE, captchaService.checkCaptcha(requestMock));
    }

    @Test
    public void shouldReturnErrorMessage_WhenCaptchaHasBeenExpired() throws Exception {
        CaptchaService captchaServiceMock = PowerMockito.spy(captchaService);
        PowerMockito.doReturn(EXPIRED_CAPTCHA).when(captchaServiceMock, "createCaptcha", anyObject());

        captchaServiceMock.storeCaptcha(requestMock, null);

        assertEquals(CAPTCHA_EXPIRED_MESSAGE, captchaServiceMock.checkCaptcha(requestMock));
    }


}