package io.swagger.api.dao;

import io.swagger.api.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author gilfanovrenat
 */

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {
}
