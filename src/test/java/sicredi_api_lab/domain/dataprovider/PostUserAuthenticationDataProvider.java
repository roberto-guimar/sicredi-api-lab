package sicredi_api_lab.domain.dataprovider;

import sicredi_api_lab.domain.model.AuthenticationResponse;

import java.util.Map;

public interface PostUserAuthenticationDataProvider {
    AuthenticationResponse execute(Map<String, String> headers, Map<String, String> body);
}
