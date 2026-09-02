package sicredi_api_lab.tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sicredi_api_lab.domain.usecase.GetDummyJsonUseCase;
import sicredi_api_lab.domain.usecase.GetLastStatusCodeUseCase;
import sicredi_api_lab.domain.usecase.StatusCodeValidatorUseCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static sicredi_api_lab.constants.StatusCode.STATUS_OK;

@SpringBootTest
public class GetEndpointShouldReturnOkTests {

    private final GetLastStatusCodeUseCase getLastStatusCodeUseCase;
    private final StatusCodeValidatorUseCase statusCodeValidatorUseCase;
    private final GetDummyJsonUseCase getDummyJsonUseCase;

    @Autowired
    public GetEndpointShouldReturnOkTests(GetLastStatusCodeUseCase getLastStatusCodeUseCase, StatusCodeValidatorUseCase statusCodeValidatorUseCase, GetDummyJsonUseCase getDummyJsonUseCase) {
        this.getLastStatusCodeUseCase = getLastStatusCodeUseCase;
        this.statusCodeValidatorUseCase = statusCodeValidatorUseCase;
        this.getDummyJsonUseCase = getDummyJsonUseCase;
    }

    @Test
    public void shouldReturn200WhenCallingTestEndpoint() {
        var response = getDummyJsonUseCase.execute();
        statusCodeValidatorUseCase.execute(getLastStatusCodeUseCase.execute(), STATUS_OK);

        assertEquals("ok", response.getStatus());
        assertEquals("GET", response.getMethod());
    }

    @Test
    public void shouldThrowExceptionWhenStatusCodeIsWrong() {
        var response = getDummyJsonUseCase.execute();
        statusCodeValidatorUseCase.execute(getLastStatusCodeUseCase.execute(), STATUS_OK);

        String statusErrado = "error";
        assertNotEquals(statusErrado, response.getStatus(), "O status retornado está incorreto!");
        assertEquals("GET", response.getMethod());
    }

    @Test
    public void shouldThrowExceptionWhenMethodIsWrong() {
        var response = getDummyJsonUseCase.execute();
        statusCodeValidatorUseCase.execute(getLastStatusCodeUseCase.execute(), STATUS_OK);

        assertEquals("ok", response.getStatus());

        String metodoErrado = "POST";
        assertNotEquals(metodoErrado, response.getStatus(), "O status retornado está incorreto!");
    }
}
