package io.swagger.api.dao;

import java.util.List;

import io.swagger.api.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author gilfanovrenat
 */
public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {

  List<TransactionEntity> findAllBySourceAccIdOrDestAccId(Long sourceAccId, Long destAccId);
}
