package com.invillia.integration.AccountParameter;

import com.invillia.entity.request.AccountParametersRequest;
import com.invillia.factory.AccountParameterFactory;
import com.invillia.repository.AccountParametersRepository;
import com.invillia.specification.CommonResponseSpecification;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeleteAccountIntegrationTest {

    private final AccountParameterFactory accountParameterFactory;
    private final AccountParametersRepository accountParametersRepository;

    @Autowired
    public DeleteAccountIntegrationTest(final AccountParameterFactory accountParameterFactory,
                                        final AccountParametersRepository accountParametersRepository) {
        this.accountParameterFactory = accountParameterFactory;
        this.accountParametersRepository = accountParametersRepository;
    }

    @Test
    void withSuccess() {
        accountParameterFactory.create();

        RestAssured
                .given()
                    .log().all()
                .when()
                    .delete("/bankAccount/1")
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_NO_CONTENT);

        Assertions.assertEquals(0, accountParametersRepository.count());
    }

    @Test
    void notFound() {
        RestAssured
                .given()
                    .log().all()
                .when()
                    .delete("/bankAccount/1")
                .then()
                    .log().all()
                    .spec(CommonResponseSpecification.notFound());
    }


}
