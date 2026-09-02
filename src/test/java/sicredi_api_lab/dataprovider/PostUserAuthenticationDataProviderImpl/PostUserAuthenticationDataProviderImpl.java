package sicredi_api_lab.dataprovider.PostUserAuthenticationDataProviderImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sicredi_api_lab.dataprovider.services.RestAssuredService;
import sicredi_api_lab.domain.dataprovider.ErrorsUserAuthenticationDataProvider;
import sicredi_api_lab.domain.dataprovider.PostUserAuthenticationDataProvider;
import sicredi_api_lab.domain.model.AuthenticationResponse;
import sicredi_api_lab.domain.model.Errors;


import java.util.Map;

@Service
public class PostUserAuthenticationDataProviderImpl implements PostUserAuthenticationDataProvider, ErrorsUserAuthenticationDataProvider {

    private final static String ENDPOINT = "/auth/login";

    private final RestAssuredService restAssuredService;

    @Value("${integration.dummyjson-apis.url}")
    private String url;

    public PostUserAuthenticationDataProviderImpl(RestAssuredService restAssuredService) {
        this.restAssuredService = restAssuredService;
    }

    @Override
    public AuthenticationResponse execute(Map<String, String> headers, Map<String, String> body) {
        var fullUrl = String.format(url.concat(ENDPOINT));
        var response = restAssuredService.post(fullUrl, headers, body);
        return response.body().as(AuthenticationResponse.class);
    }

    @Override
    public Errors executeError(Map<String, String> headers, Map<String, String> body) {
        var fullUrl = String.format(url.concat(ENDPOINT));
        var response = restAssuredService.post(fullUrl, headers, body);
        return response.body().as(Errors.class);
    }
}
