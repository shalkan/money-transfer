package io.swagger.api.dao;

import io.swagger.api.entity.CurrencyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author gilfanovrenat
 */

@Repository
public interface CurrencyRepository extends CrudRepository<CurrencyEntity, Long> {
}
