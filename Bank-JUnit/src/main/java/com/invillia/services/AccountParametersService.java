package com.invillia.services;

import com.invillia.entity.AccountParameters;
import com.invillia.entity.request.AccountParametersRequest;
import com.invillia.entity.response.AccountParametersResponse;
import com.invillia.exception.ResourceNotFoundException;
import com.invillia.mapper.AccountParametersMapper;
import com.invillia.repository.AccountParametersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountParametersService {

    private final AccountParametersRepository accountParametersRepository;

    private final AccountParametersMapper accountParametersMapper;

    public AccountParametersService(AccountParametersRepository accountParametersRepository, AccountParametersMapper accountParametersMapper) {
        this.accountParametersRepository = accountParametersRepository;
        this.accountParametersMapper = accountParametersMapper;
    }

    @Transactional(readOnly = true)
    public List<AccountParametersResponse> findAll() {

        final List<AccountParameters> accountParameters = accountParametersRepository.findAll();

        return accountParametersMapper.clientToClientResponse(accountParameters);
    }

    @Transactional(readOnly = true)
    public AccountParametersResponse findById(final Long id) {

        return accountParametersRepository.findById(id)
                .map(accountParametersMapper::clientToClientResponse)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional
    public Long create(final AccountParametersRequest accountParametersRequest) {
        AccountParameters accountParameters = accountParametersMapper.clientRequestToClient(accountParametersRequest);
        accountParametersRepository.save(accountParameters);

        return accountParameters.getId();
    }

    @Transactional
    public void update(final Long id, final AccountParametersRequest accountParametersRequest) {

        final AccountParameters accountParameters = accountParametersRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        accountParametersMapper.updateClientByClientRequest(accountParameters, accountParametersRequest);

        accountParametersRepository.save(accountParameters);
    }

    @Transactional
    public void delete(final Long id) {
        final AccountParameters accountParameters = accountParametersRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        accountParametersRepository.delete(accountParameters);
    }

}
