package com.invillia.integration.AccountParameter;

import com.invillia.entity.AccountParameters;
import com.invillia.entity.request.AccountParametersRequest;
import com.invillia.exception.ResourceNotFoundException;
import com.invillia.factory.AccountRequestFactory;
import com.invillia.repository.AccountParametersRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateAccountIntegrationTest {

    private final AccountRequestFactory accountRequestFactory;
    private final AccountParametersRepository accountParametersRepository;

    @Autowired
    public CreateAccountIntegrationTest(AccountRequestFactory accountRequestFactory, AccountParametersRepository accountParametersRepository) {
        this.accountRequestFactory = accountRequestFactory;
        this.accountParametersRepository = accountParametersRepository;
    }

    @Test
    void withSuccess() {
        AccountParametersRequest request = accountRequestFactory.build();

        RestAssured
                .given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(request)
                .when()
                    .post("/bankAccount")
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .header("Location", Matchers.containsString("/bankAccount/1"));

        AccountParameters accountParameters = accountParametersRepository.findById(1L)
                .orElseThrow(ResourceNotFoundException::new);

        Assertions.assertAll("Assert account contet",
                () -> Assertions.assertEquals(Integer.valueOf(request.getAgency()), accountParameters.getAgency()),
                () -> Assertions.assertEquals(Integer.valueOf(request.getAccount()), accountParameters.getAccount()),
                () -> Assertions.assertEquals(request.getDocumentNumber(), accountParameters.getDocumentNumber()),
                () -> Assertions.assertEquals(request.getName(), accountParameters.getName()),
                () -> Assertions.assertEquals(request.getType(), accountParameters.getType()));


    }

    @Test
    void failInValidations() {
        RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(new AccountParametersRequest())
                .when()
                    .post("/bankAccount")
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("agency", Matchers.containsInAnyOrder("não pode ser nulo", "não pode estar em branco"))
                    .body("account", Matchers.containsInAnyOrder("não pode ser nulo", "não pode estar em branco"))
                    .body("documentNumber", Matchers.containsInAnyOrder("não pode estar em branco"))
                    .body("name", Matchers.containsInAnyOrder("não pode estar em branco"))
                    .body("type", Matchers.containsInAnyOrder("não pode estar em branco"));


    }
}
