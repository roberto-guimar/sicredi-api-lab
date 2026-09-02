package sicredi_api_lab.dataprovider;

import org.springframework.stereotype.Service;
import sicredi_api_lab.dataprovider.services.RestAssuredService;
import sicredi_api_lab.domain.dataprovider.GetLastStatusCodeDataProvider;

@Service
public class GetLastStatusCodeDataProviderImpl implements GetLastStatusCodeDataProvider {
    private final RestAssuredService rest;

    public GetLastStatusCodeDataProviderImpl(RestAssuredService rest) {
        this.rest = rest;
    }

    @Override
    public int execute() {
        return rest.getLastStatusCode();
    }
}