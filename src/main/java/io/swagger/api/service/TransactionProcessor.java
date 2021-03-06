package io.swagger.api.service;

import java.math.BigDecimal;
import java.util.Queue;

import io.swagger.model.Account;
import io.swagger.model.Transaction;
import org.threeten.bp.OffsetDateTime;

import static io.swagger.model.Transaction.StatusEnum.COMPLETE;
import static io.swagger.model.Transaction.StatusEnum.FAILED;

/**
 * @author gilfanovrenat
 */

public class TransactionProcessor implements Runnable {

  private Queue<Transaction> queue;

  public TransactionProcessor(Queue<Transaction> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    while (true) {
      if (!this.queue.isEmpty()) {
        Transaction transaction = this.queue.poll();
        //here timeout can be added, to display transaction processing lag
        Account sourceAcc = transaction.getSourceAcc();
        Account destAcc = transaction.getDestAccount();
        BigDecimal amount = transaction.getAmount();
        transaction.setEndDate(OffsetDateTime.now());
        if (sourceAcc != null && sourceAcc.getAmount().compareTo(amount) < 0) {
          transaction.setStatus(FAILED);
        }
        else {
          if (sourceAcc != null) {
            sourceAcc.setAmount(sourceAcc.getAmount().subtract(amount));
          }
          destAcc.setAmount(destAcc.getAmount().add(amount));
          transaction.setStatus(COMPLETE);
        }
      }
    }
  }

}
