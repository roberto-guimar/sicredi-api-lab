package sicredi_api_lab.domain.usecase;

import org.springframework.stereotype.Service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Service
public class StatusCodeValidatorUseCase {

    public void execute(int actualStatusCode, int expectedStatusCode) {
        assertThat(actualStatusCode, is(expectedStatusCode));
    }
}