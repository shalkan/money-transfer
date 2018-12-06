package io.swagger.api.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import io.swagger.api.dao.AccountRepository;
import io.swagger.api.dao.TransactionRepository;
import io.swagger.api.entity.AccountEntity;
import io.swagger.api.entity.TransactionEntity;
import io.swagger.model.Transaction;
import io.swagger.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.threeten.bp.Instant;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneId;

/**
 * @author gilfanovrenat
 */

@Service
public class TransferService {

  @Autowired
  private TransactionRepository transactionRepository;

  @Autowired
  private AccountRepository accountRepository;

  public Transaction getTransaction(Long transactionId) {
    return this.map(transactionRepository.findById(transactionId).orElse(null));
  }

  public List<Transaction> getTransactions() {
    return StreamSupport.stream(transactionRepository.findAll().spliterator(), false)
        .map(this::map).collect(Collectors.toList());
  }

  public List<Transaction> getTransactions(Long accId) {
    return StreamSupport.stream(
        transactionRepository.findAllBySourceAccIdOrDestAccId(accId, accId).spliterator(), false)
        .map(this::map).collect(Collectors.toList());
  }

  private Transaction map(TransactionEntity entity) {
    if(entity == null) {
      throw new IllegalArgumentException("Transaction does not exist");
    }
    Transaction transaction = new Transaction();
    transaction.setId(entity.getId());
    transaction.setSourceAccId(entity.getSourceAccId());
    transaction.setDestAccId(entity.getDestAccId());
    if (entity.getSourceAcc() != null) {
      transaction.setSourceCurrencyId(entity.getSourceAcc().getCurrencyId());
      transaction.setSourceCurrencyName(entity.getSourceAcc().getCurrencyEntity().getName());
      transaction.setSourceCurrencyRatio(entity.getSourceAcc().getCurrencyEntity().getRatio());
    }
    transaction.setDestCurrencyId(entity.getDestAcc().getCurrencyId());
    transaction.setDestCurrencyName(entity.getDestAcc().getCurrencyEntity().getName());
    transaction.setDestCurrencyRatio(entity.getDestAcc().getCurrencyEntity().getRatio());
    transaction.setStatus(entity.getStatus());
    transaction.setAmount(entity.getAmount());
    transaction.setStartDate(
        OffsetDateTime.ofInstant(Instant.ofEpochMilli(entity.getStartDate().getTime()),
            ZoneId.systemDefault()));
    transaction.setEndDate(
        OffsetDateTime.ofInstant(Instant.ofEpochMilli(entity.getEndDate().getTime()),
            ZoneId.systemDefault()));
    transaction.setOperationType(entity.getOperationType());
    return transaction;
  }

  //TODO: implement transfer failure handling & saving transaction info in that case
  @Transactional
  public Transaction handleTransfer(Transfer transfer) {
    AccountEntity sourceAcc = accountRepository.findById(transfer.getSourceAccId()).orElse(null);
    if(sourceAcc == null) {
      throw new IllegalArgumentException("Source account does not exist");
    }
    AccountEntity destAcc = accountRepository.findById(transfer.getDestAccId()).orElse(null);
    if(destAcc == null) {
      throw new IllegalArgumentException("Destination account does not exist");
    }
    if(sourceAcc.getAmount().compareTo(transfer.getAmount()) < 0) {
      throw new UnsupportedOperationException("Not enough money on source account");
    }

    TransactionEntity transaction = new TransactionEntity();
    transaction.setSourceAccId(sourceAcc.getId());
    transaction.setSourceAcc(sourceAcc);
    transaction.setDestAccId(destAcc.getId());
    transaction.setDestAcc(destAcc);
    transaction.setStatus(Transaction.StatusEnum.COMPLETE);
    transaction.setStartDate(new Timestamp(System.currentTimeMillis()));
    transaction.setEndDate(transaction.getStartDate());
    transaction.setAmount(transfer.getAmount());
    transaction.setOperationType(Transaction.OperationTypeEnum.TRANSFER);
    if(sourceAcc.getCurrencyId().equals(destAcc.getCurrencyId())) {
      sourceAcc.setAmount(sourceAcc.getAmount().subtract(transfer.getAmount()));
      destAcc.setAmount(destAcc.getAmount().add(transfer.getAmount()));
    } else {
      sourceAcc.setAmount(sourceAcc.getAmount().subtract(transfer.getAmount().multiply(sourceAcc.getCurrencyEntity().getRatio())));
      destAcc.setAmount(destAcc.getAmount().add(transfer.getAmount().divide(destAcc.getCurrencyEntity().getRatio())));
    }
    accountRepository.save(sourceAcc);
    accountRepository.save(destAcc);

    return map(transactionRepository.save(transaction));
  }

  public Transaction createIncomeTransaction(AccountEntity account) {
    TransactionEntity transaction = new TransactionEntity();
    transaction.setDestAccId(account.getId());
    transaction.setDestAcc(account);
    transaction.setStatus(Transaction.StatusEnum.COMPLETE);
    transaction.setStartDate(new Timestamp(System.currentTimeMillis()));
    transaction.setEndDate(transaction.getStartDate());
    transaction.setAmount(account.getAmount());
    transaction.setOperationType(Transaction.OperationTypeEnum.INITIALINCOME);
    return map(transactionRepository.save(transaction));
  }
}
