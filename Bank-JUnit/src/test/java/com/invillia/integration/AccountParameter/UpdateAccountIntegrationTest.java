package com.invillia.integration.AccountParameter;

import com.invillia.entity.AccountParameters;
import com.invillia.entity.request.AccountParametersRequest;
import com.invillia.exception.ResourceNotFoundException;
import com.invillia.factory.AccountParameterFactory;
import com.invillia.factory.AccountRequestFactory;
import com.invillia.repository.AccountParametersRepository;
import com.invillia.specification.CommonResponseSpecification;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateAccountIntegrationTest {

    private final AccountParameterFactory accountParameterFactory;
    private final AccountRequestFactory accountRequestFactory;
    private final AccountParametersRepository accountParametersRepository;

    @Autowired
    public UpdateAccountIntegrationTest(AccountParameterFactory accountParameterFactory, AccountRequestFactory accountRequestFactory, AccountParametersRepository accountParametersRepository) {
        this.accountParameterFactory = accountParameterFactory;
        this.accountRequestFactory = accountRequestFactory;
        this.accountParametersRepository = accountParametersRepository;
    }

    @Test
    void withSuccess() {
        accountParameterFactory.create();
        AccountParametersRequest request = accountRequestFactory.build();

        RestAssured
                    .given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .body(request)
                    .when()
                        .put("/bankAccount/1")
                    .then()
                        .log().all()
                        .statusCode(HttpStatus.SC_NO_CONTENT);


        AccountParameters account = accountParametersRepository.findById(1L)
                .orElseThrow(ResourceNotFoundException::new);

        Assertions.assertAll("Assert contact content",
                () -> Assertions.assertEquals(request.getName(), account.getName()),
                () -> Assertions.assertEquals(request.getType(), account.getType()));

    }

    @Test
    void failInValidation() {
        accountParameterFactory.create();

        RestAssured
                .given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(new AccountParametersRequest())
                .when()
                    .put("/bankAccount/1")
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("name", Matchers.containsInAnyOrder("não pode estar em branco"))
                    .body("type", Matchers.containsInAnyOrder("não pode estar em branco"));
    }

    @Test
    void updateNotFound() {
        AccountParametersRequest request = accountRequestFactory.build();

        RestAssured
                .given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(request)
                .when()
                    .put("/bankAccount/1")
                .then()
                    .log().all()
                    .spec(CommonResponseSpecification.notFound());

    }
}
