package com.invillia.factory;

import br.com.leonardoferreira.jbacon.JBacon;
import br.com.leonardoferreira.jbacon.annotation.JBaconTemplate;
import com.github.javafaker.Faker;
import com.invillia.entity.AccountParameters;
import com.invillia.repository.AccountParametersRepository;
import org.springframework.stereotype.Component;

@Component
public class AccountParameterFactory extends JBacon<AccountParameters> {

    private final AccountParametersRepository accountParametersRepository;

    private final Faker faker;

    public AccountParameterFactory(AccountParametersRepository accountParametersRepository, Faker faker) {
        this.accountParametersRepository = accountParametersRepository;
        this.faker = faker;
    }


    @Override
    protected AccountParameters getDefault() {
        AccountParameters accountParameters = new AccountParameters();

        accountParameters.setAgency(Integer.valueOf(faker.number().digits(4)));
        accountParameters.setAccount(Integer.valueOf(faker.number().digits(5)));
        accountParameters.setDocumentNumber(faker.number().digits(11));
        accountParameters.setName(faker.zelda().character());
        accountParameters.setType("corrente");

        return accountParameters;
    }

    @Override
    protected AccountParameters getEmpty() {
        return new AccountParameters();
    }

    @Override
    protected void persist(AccountParameters accountParameters) {
        accountParametersRepository.save(accountParameters);
    }

    @JBaconTemplate
    protected AccountParameters accountInvalid() {
        AccountParameters accountParameters = new AccountParameters();
        accountParameters.setAgency(192);
        accountParameters.setAccount(3245);
        accountParameters.setDocumentNumber("26268738888");
        accountParameters.setName("Diogo");
        accountParameters.setType("poupanca");

        return accountParameters;
    }
}
