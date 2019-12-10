package com.invillia.factory;

import br.com.leonardoferreira.jbacon.JBacon;
import com.github.javafaker.Faker;
import com.invillia.entity.request.AccountParametersRequest;
import org.springframework.stereotype.Component;

@Component
public class AccountRequestFactory extends JBacon<AccountParametersRequest> {

    private final Faker faker;

    public AccountRequestFactory(Faker faker) {
        this.faker = faker;
    }

    @Override
    protected AccountParametersRequest getDefault() {
        AccountParametersRequest accountParametersRequest = new AccountParametersRequest();

        accountParametersRequest.setAgency(String.valueOf(faker.code().hashCode()));
        accountParametersRequest.setAccount(String.valueOf(faker.code().hashCode()));
        accountParametersRequest.setDocumentNumber(faker.number().digits(11));
        accountParametersRequest.setName(faker.zelda().character());
        accountParametersRequest.setType("corrente");

        return accountParametersRequest;
    }

    @Override
    protected AccountParametersRequest getEmpty() {
        return new AccountParametersRequest();
    }

    @Override
    protected void persist(AccountParametersRequest accountParametersRequest) {
        throw new UnsupportedOperationException();
    }
}
