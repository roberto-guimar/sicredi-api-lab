package sicredi_api_lab.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusTest {

    private String status;
    private String method;

    public StatusTest() {
    }

    @JsonCreator
    public StatusTest(String status, String method) {
        this.status = status;
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "StatusTest{" +
                "status='" + status + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
