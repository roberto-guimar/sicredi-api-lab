package sicredi_api_lab.domain.usecase;

import org.springframework.stereotype.Service;
import sicredi_api_lab.domain.dataprovider.GetLastStatusCodeDataProvider;

@Service
public class GetLastStatusCodeUseCase {
    private final GetLastStatusCodeDataProvider dataProvider;

    public GetLastStatusCodeUseCase(GetLastStatusCodeDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public int execute() {
        return dataProvider.execute();
    }
}