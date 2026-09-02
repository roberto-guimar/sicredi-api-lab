package sicredi_api_lab.tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sicredi_api_lab.domain.usecase.GetLastStatusCodeUseCase;
import sicredi_api_lab.domain.usecase.PostErrorsUserAuthenticationUseCase;
import sicredi_api_lab.domain.usecase.PostUserAuthenticationUseCase;
import sicredi_api_lab.domain.usecase.StatusCodeValidatorUseCase;

import java.util.HashMap;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static sicredi_api_lab.constants.StatusCode.STATUS_BAD_REQUEST;
import static sicredi_api_lab.constants.StatusCode.STATUS_OK;

@SpringBootTest
public class PostUserAuthenticationTest {

    private final GetLastStatusCodeUseCase getLastStatusCodeUseCase;
    private final StatusCodeValidatorUseCase statusCodeValidatorUseCase;
    private final PostUserAuthenticationUseCase postUserAuthenticationUseCase;
    private final PostErrorsUserAuthenticationUseCase postErrorsUserAuthenticationUseCase;

    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> body = new HashMap<>();


    private final String X_CONTENT_TYPE = "application/json";
    private final String X_CONTENT_TYPE_EMPTY = "";
    private final String EMPTY_USERNAME = "";
    private final String USERNAME = "emilys";
    private final String PASSWORD = "emilyspass";
    private final String EMPTY_PASSWORD = "";
    private final String ERROR_MESSAGE = "Username and password required";

    @Autowired
    public PostUserAuthenticationTest(GetLastStatusCodeUseCase getLastStatusCodeUseCase, StatusCodeValidatorUseCase statusCodeValidatorUseCase, PostUserAuthenticationUseCase postUserAuthenticationUseCase, PostErrorsUserAuthenticationUseCase postErrorsUserAuthenticationUseCase) {
        this.getLastStatusCodeUseCase = getLastStatusCodeUseCase;
        this.statusCodeValidatorUseCase = statusCodeValidatorUseCase;
        this.postUserAuthenticationUseCase = postUserAuthenticationUseCase;
        this.postErrorsUserAuthenticationUseCase = postErrorsUserAuthenticationUseCase;
    }

    @Test
    public void shouldAuthenticateUserWithValidCredentials() {
        headers.put("Content-Type", X_CONTENT_TYPE);

        body.put("username", USERNAME);
        body.put("password", PASSWORD);

        var response = postUserAuthenticationUseCase.execute(headers, body);
        statusCodeValidatorUseCase.execute(getLastStatusCodeUseCase.execute(), STATUS_OK);

        assertNotNull(response.getAccessToken());
        assertNotNull(response.getRefreshToken());

        assertEquals(1, response.getId());
        assertEquals("emilys", response.getUsername());
        assertEquals("emily.johnson@x.dummyjson.com", response.getEmail());
        assertEquals("Emily", response.getFirstName());
        assertEquals("Johnson", response.getLastName());
        assertEquals("female", response.getGender());
        assertEquals("https://dummyjson.com/icon/emilys/128", response.getImage());
    }

    @Test
    public void shouldAuthenticateUserWithUsernameEmptyCredentials() {
        headers.put("Content-Type", X_CONTENT_TYPE);

//        body.put("username", USERNAME);
        body.put("password", PASSWORD);

        var actualError = postErrorsUserAuthenticationUseCase.execute(headers, body);
        statusCodeValidatorUseCase.execute(
                getLastStatusCodeUseCase.execute(),
                STATUS_BAD_REQUEST
        );

        assertEquals(
                ERROR_MESSAGE,
                actualError.getMessage()
        );
    }

    @Test
    public void shouldAuthenticateUserWithUsernameBlankCredentials() {
        headers.put("Content-Type", X_CONTENT_TYPE);

        body.put("username", EMPTY_USERNAME);
        body.put("password", PASSWORD);

        var actualError = postErrorsUserAuthenticationUseCase.execute(headers, body);
        statusCodeValidatorUseCase.execute(
                getLastStatusCodeUseCase.execute(),
                STATUS_BAD_REQUEST
        );

        assertEquals(
                ERROR_MESSAGE,
                actualError.getMessage()
        );
    }

    @Test
    public void shouldAuthenticateUserWithPasswordEmptyCredentials() {
        headers.put("Content-Type", X_CONTENT_TYPE);

        body.put("username", USERNAME);
//        body.put("password", PASSWORD);

        var actualError = postErrorsUserAuthenticationUseCase.execute(headers, body);
        statusCodeValidatorUseCase.execute(
                getLastStatusCodeUseCase.execute(),
                STATUS_BAD_REQUEST
        );

        assertEquals(
                ERROR_MESSAGE,
                actualError.getMessage()
        );
    }

    @Test
    public void shouldAuthenticateUserWithPasswordBlankCredentials() {
        headers.put("Content-Type", X_CONTENT_TYPE);

        body.put("username", USERNAME);
        body.put("password", EMPTY_PASSWORD);

        var actualError = postErrorsUserAuthenticationUseCase.execute(headers, body);
        statusCodeValidatorUseCase.execute(
                getLastStatusCodeUseCase.execute(),
                STATUS_BAD_REQUEST
        );

        assertEquals(
                ERROR_MESSAGE,
                actualError.getMessage()
        );
    }

    @Test
    public void shouldAuthenticateUserWithContentTypeEmptyCredentials() {
//        headers.put("Content-Type", X_CONTENT_TYPE_EMPTY);

        body.put("username", USERNAME);
        body.put("password", PASSWORD);

        var actualError = postErrorsUserAuthenticationUseCase.execute(headers, body);
        statusCodeValidatorUseCase.execute(
                getLastStatusCodeUseCase.execute(),
                STATUS_BAD_REQUEST
        );

        assertEquals(
                ERROR_MESSAGE,
                actualError.getMessage()
        );
    }
}
