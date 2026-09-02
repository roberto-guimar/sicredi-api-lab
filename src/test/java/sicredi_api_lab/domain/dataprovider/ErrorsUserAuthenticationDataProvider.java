package sicredi_api_lab.domain.dataprovider;

import sicredi_api_lab.domain.model.Errors;

import java.util.Map;

public interface ErrorsUserAuthenticationDataProvider {
    Errors executeError(Map<String, String> headers, Map<String, String> body);
}