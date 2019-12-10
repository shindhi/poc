package com.invillia.specification;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonResponseSpecification {

    public static ResponseSpecification notFound() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_NOT_FOUND)
                .expectBody("timestamp", Matchers.not(Matchers.emptyString()))
                .expectBody("status", Matchers.is(404))
                .expectBody("error", Matchers.is("Not Found"))
//                .expectBody("message", Matchers.is("Resource Not Found"))
                .expectBody("message", Matchers.is("No message available"))
                .expectBody("path", Matchers.not(Matchers.emptyString()))
                .build();
    }

}
