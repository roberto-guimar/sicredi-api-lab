package sicredi_api_lab.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Errors {

    private String message;

    public Errors() {
    }

    public Errors(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Errors{" +
                "message='" + message + '\'' +
                '}';
    }
}