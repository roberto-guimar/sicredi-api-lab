package sicredi_api_lab.dataprovider.GetDummyjson;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sicredi_api_lab.dataprovider.services.RestAssuredService;
import sicredi_api_lab.domain.dataprovider.GetDummyJsonStatusDataProvider;
import sicredi_api_lab.domain.model.StatusTest;

@Service
public class GetDummyjsonDataProviderImpl implements GetDummyJsonStatusDataProvider {

    private final static String ENDPOINT = "/test";
    private final RestAssuredService restAssuredService;

    @Value("${integration.dummyjson-apis.url}")
    private String url;

    public GetDummyjsonDataProviderImpl(RestAssuredService restAssuredService) {
        this.restAssuredService = restAssuredService;
    }

    @Override
    public StatusTest execute() {
        var fullUrl = String.format(url.concat(ENDPOINT));
        var response = restAssuredService.get(fullUrl);
        return response.body().as(StatusTest.class);
    }
}