package com.invillia.repository;

import com.invillia.entity.AccountParameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountParametersRepository extends JpaRepository<AccountParameters, Long> {
}
