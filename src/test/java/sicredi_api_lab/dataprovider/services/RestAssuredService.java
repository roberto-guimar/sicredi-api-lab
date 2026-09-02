package sicredi_api_lab.dataprovider.services;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RestAssuredService {
    private Response response;

    public int getLastStatusCode() {
        return response.statusCode();
    }

    public Response get(String url) {
        return setResponse(RestAssured
                .given()
                .filter(new AllureRestAssured())
                .baseUri(url)
                .when()
                .log().all()
                .get());
    }

    public Response post(String url, Map<String, String> headers, Object body) {
        return setResponse(RestAssured
                .given()
                .filter(new AllureRestAssured())
                .baseUri(url)
                .headers(headers)
                .body(body)
                .when()
                .log().all()
                .post());
    }

    private Response setResponse(Response response) {
        this.response = response;
        this.response.then().log().all();
        return this.response;
    }
}