package sicredi_api_lab.domain.usecase;

import org.springframework.stereotype.Service;
import sicredi_api_lab.domain.dataprovider.GetDummyJsonStatusDataProvider;
import sicredi_api_lab.domain.model.StatusTest;


@Service
public class GetDummyJsonUseCase {
    private final GetDummyJsonStatusDataProvider dataProvider;

    public GetDummyJsonUseCase(GetDummyJsonStatusDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public StatusTest execute() {
        return dataProvider.execute();
    }
}