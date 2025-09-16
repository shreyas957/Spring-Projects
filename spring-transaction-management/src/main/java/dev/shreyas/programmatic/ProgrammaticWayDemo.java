package dev.shreyas.programmatic;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public class ProgrammaticWayDemo {
    public final PlatformTransactionManager transactionManager;
    public final TransactionTemplate transactionTemplate;

    public ProgrammaticWayDemo(PlatformTransactionManager transactionManager, TransactionTemplate transactionTemplate) {
        this.transactionManager = transactionManager;
        this.transactionTemplate = transactionTemplate;
    }

    // Approach 1
    public void updateUser() {
        TransactionStatus transactionStatus = transactionManager.getTransaction(null);
        try {
            // Some db operations
            System.out.println("User updated");
            transactionManager.commit(transactionStatus);
        } catch (TransactionException e) {
            transactionManager.rollback(transactionStatus);
        }
    }

    // Approach 2
    public void updateUSer2() {
        TransactionCallback<TransactionStatus> dbOperationTask = (TransactionStatus status) -> {
            // some DB operation
            System.out.println("User updated");
            return status;
        };
        TransactionStatus status = transactionTemplate.execute(dbOperationTask);
    }
}
