package com.invillia.mapper;

import com.invillia.entity.AccountParameters;
import com.invillia.entity.request.AccountParametersRequest;
import com.invillia.entity.response.AccountParametersResponse;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountParametersMapper {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public AccountParametersResponse clientToClientResponse(final AccountParameters accountParameters) {
        final AccountParametersResponse accountParametersResponse = new AccountParametersResponse();

        accountParametersResponse.setId(accountParameters.getId());
        accountParametersResponse.setAgency(accountParameters.getAgency());
        accountParametersResponse.setAccount(accountParameters.getAccount());
        accountParametersResponse.setDocumentNumber(accountParameters.getDocumentNumber());
        accountParametersResponse.setName(accountParameters.getName());
        accountParametersResponse.setType(accountParameters.getType());
        accountParametersResponse.setCreatedAt(accountParameters.getCreatedAt().format(formatter));
        accountParametersResponse.setUpdatedAt(accountParameters.getUpdatedAt().format(formatter));

        return accountParametersResponse;
    }

    public List<AccountParametersResponse> clientToClientResponse(final List<AccountParameters> accountParameters) {

        return accountParameters.stream()
                .map(this::clientToClientResponse)
                .collect(Collectors.toList());
    }

    public AccountParameters clientRequestToClient(final AccountParametersRequest accountParametersRequest) {
        final AccountParameters accountParameters = new AccountParameters();

        accountParameters.setAgency(Integer.valueOf(accountParametersRequest.getAgency()));
        accountParameters.setAccount(Integer.valueOf(accountParametersRequest.getAccount()));
        accountParameters.setDocumentNumber(accountParametersRequest.getDocumentNumber());
        accountParameters.setName(accountParametersRequest.getName());
        accountParameters.setType(accountParametersRequest.getType());

        return accountParameters;
    }

    public void updateClientByClientRequest(final AccountParameters accountParameters, final AccountParametersRequest accountParametersRequest) {
        accountParameters.setAgency(Integer.valueOf(accountParametersRequest.getAgency()));
        accountParameters.setAccount(Integer.valueOf(accountParametersRequest.getAccount()));
        accountParameters.setDocumentNumber(accountParametersRequest.getDocumentNumber());
        accountParameters.setName(accountParametersRequest.getName());
        accountParameters.setType(accountParametersRequest.getType());
    }


}
