package sicredi_api_lab.domain.usecase;

import org.springframework.stereotype.Service;
import sicredi_api_lab.domain.dataprovider.PostUserAuthenticationDataProvider;
import sicredi_api_lab.domain.model.AuthenticationResponse;

import java.util.Map;

@Service
public class PostUserAuthenticationUseCase {
    private final PostUserAuthenticationDataProvider postUserAuthenticationDataProvider;

    public PostUserAuthenticationUseCase(PostUserAuthenticationDataProvider postUserAuthenticationDataProvider) {
        this.postUserAuthenticationDataProvider = postUserAuthenticationDataProvider;
    }

    public AuthenticationResponse execute(Map<String, String> headers, Map<String, String> body) {
        return postUserAuthenticationDataProvider.execute(headers, body);
    }
}
