package com.invillia.controller;

import com.invillia.entity.request.AccountParametersRequest;
import com.invillia.entity.response.AccountParametersResponse;
import com.invillia.services.AccountParametersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/bankAccount")
public class AccountParametersController {

    private final AccountParametersService accountParametersService;

    public AccountParametersController(AccountParametersService accountParametersService) {
        this.accountParametersService = accountParametersService;
    }

    @GetMapping
    public List<AccountParametersResponse> findAll() {
        return accountParametersService.findAll();
    }

    @GetMapping("/{id}")
    public AccountParametersResponse findById(@PathVariable final Long id) {
        return accountParametersService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody final AccountParametersRequest accountParametersRequest) {
        Long id = accountParametersService.create(accountParametersRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/bankAccount/{id}")
                .build(id);

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable final Long id, @Valid @RequestBody final AccountParametersRequest accountParametersRequest) {
        accountParametersService.update(id, accountParametersRequest);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        accountParametersService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
