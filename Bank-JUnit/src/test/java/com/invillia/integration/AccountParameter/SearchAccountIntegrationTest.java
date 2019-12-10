package com.invillia.integration.AccountParameter;

import com.invillia.entity.AccountParameters;
import com.invillia.factory.AccountParameterFactory;
import com.invillia.specification.CommonResponseSpecification;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SearchAccountIntegrationTest {

    private final AccountParameterFactory accountParameterFactory;

    @Autowired
    public SearchAccountIntegrationTest(AccountParameterFactory accountParameterFactory) {
        this.accountParameterFactory = accountParameterFactory;
    }

    @Test
    void findAll() {
        accountParameterFactory.create(5);

        RestAssured
                .given()
                    .log().all()
                .when()
                    .get("/bankAccount")
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK)
                    .body("$", Matchers.hasSize(5));
    }

    @Test
    void findById() {
        AccountParameters accountParameters = accountParameterFactory.create();

        RestAssured
                .given()
                    .log().all()
                .when()
                    .get("/bankAccount/1")
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK)
                    .body("agency", Matchers.is(accountParameters.getAgency()))
                    .body("account", Matchers.is(accountParameters.getAccount()))
                    .body("documentNumber", Matchers.is(accountParameters.getDocumentNumber()))
                    .body("name", Matchers.is(accountParameters.getName()))
                    .body("type", Matchers.is(accountParameters.getType()));
    }

    @Test
    void findAllNotReturn() {
        RestAssured
                .given()
                    .log().all()
                .when()
                    .get("/bankAccount")
                .then()
                    .log().all()
                    .equals(Matchers.is("[]"));
    }

    @Test
    void findByIdNotFound() {
        RestAssured
                .given()
                    .log().all()
                .when()
                    .get("/bankAccount/1")
                .then()
                    .log().all()
                    .spec(CommonResponseSpecification.notFound());
    }
}
