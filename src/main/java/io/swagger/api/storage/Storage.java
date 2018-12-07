package io.swagger.api.storage;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;

import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.model.Transfer;
import org.springframework.stereotype.Component;
import org.threeten.bp.OffsetDateTime;

/**
 * @author gilfanovrenat
 */

@Component
public class Storage {

  private ConcurrentLinkedQueue<Transaction> transactionQueue;

  private Map<String, Account> accountMap;
  private List<Transaction> transactionList;

  @PostConstruct
  private void init() {
    this.transactionQueue = new ConcurrentLinkedQueue<>();
    this.accountMap = new HashMap<>();
    this.transactionList = new ArrayList<>();
  }

  public ConcurrentLinkedQueue<Transaction> getTransactionQueue() {
    return transactionQueue;
  }

  public Account addAccount(Account account) {
    Transaction transaction = new Transaction();
    transaction.setId(UUID.randomUUID().toString());
    transaction.setDestAccId(account.getId());
    transaction.setDestAccount(account);
    transaction.setStatus(Transaction.StatusEnum.ACTIVE);
    transaction.setAmount(account.getAmount());
    transaction.setStartDate(OffsetDateTime.now());
    transaction.setOperationType(Transaction.OperationTypeEnum.INITIALINCOME);
    transactionList.add(transaction);
    account.getTransactionList().add(transaction);
    this.transactionQueue.add(transaction);
    account.setAmount(BigDecimal.ZERO);
    this.accountMap.put(account.getId(), account);
    return account;
  }

  public Account getAccount(String accountId) {
    return this.accountMap.getOrDefault(accountId, null);
  }

  public Collection<Account> getAccounts() {
    return this.accountMap.values();
  }

  //TODO: implement pagination
  public List<Transaction> getTransactionList() {
    return this.transactionList;
  }

  //TODO: implement pagination
  public List<Transaction> getAccountTransactions(String accountId) {
    Account account = this.accountMap.getOrDefault(accountId, null);
    return account == null ? Collections.emptyList() : account.getTransactionList();
  }

  public Transaction createTransaction(Transfer transfer) {
    Account sourceAcc = this.getAccount(transfer.getSourceAccId());
    Account destAcc = this.getAccount(transfer.getDestAccId());
    if (sourceAcc == null || destAcc == null) {
      throw new IllegalArgumentException("Account does not exist");
    }
    Transaction transaction = new Transaction();
    transaction.setId(UUID.randomUUID().toString());
    transaction.setSourceAccId(sourceAcc.getId());
    transaction.setSourceAcc(sourceAcc);
    transaction.setDestAccId(destAcc.getId());
    transaction.setDestAccount(destAcc);
    transaction.setStatus(Transaction.StatusEnum.ACTIVE);
    transaction.setAmount(transfer.getAmount());
    transaction.setStartDate(OffsetDateTime.now());
    transaction.setOperationType(Transaction.OperationTypeEnum.TRANSFER);
    transactionList.add(transaction);
    sourceAcc.getTransactionList().add(transaction);
    destAcc.getTransactionList().add(transaction);
    this.transactionQueue.add(transaction);
    return transaction;
  }


}
