package sicredi_api_lab.domain.usecase;

import org.springframework.stereotype.Service;
import sicredi_api_lab.domain.dataprovider.ErrorsUserAuthenticationDataProvider;
import sicredi_api_lab.domain.model.Errors;

import java.util.Map;

@Service
public class PostErrorsUserAuthenticationUseCase {
    private final ErrorsUserAuthenticationDataProvider errorsUserAuthenticationDataProvider;

    public PostErrorsUserAuthenticationUseCase(ErrorsUserAuthenticationDataProvider errorsUserAuthenticationDataProvider) {
        this.errorsUserAuthenticationDataProvider = errorsUserAuthenticationDataProvider;
    }

    public Errors execute(Map<String, String> headers, Map<String, String> body) {
        return errorsUserAuthenticationDataProvider.executeError(headers, body);
    }
}
