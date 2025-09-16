package dev.shreyas.propagation;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.event.TransactionalEventListenerFactory;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public class PropagationExample {
    public final UserDao userDao;

    public PropagationExample(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public void updateUser() {
        System.out.println("is transaction active: " + TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("current transaction name: " + TransactionSynchronizationManager.getCurrentTransactionName());

        System.out.println("Do some db operations");
        userDao.dbOperationsProgrammaticWay2();
        System.out.println("Db operations done");
    }

    @Transactional
    public void updateUserFromNonTransactionMethod() {
        System.out.println("is transaction active: " + TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("current transaction name: " + TransactionSynchronizationManager.getCurrentTransactionName());

        System.out.println("Do some db operations");
        userDao.dbOperationsProgrammaticWay2();
        System.out.println("Db operations done");
    }
}

@Component
class UserDao {

    PlatformTransactionManager transactionManager;
    TransactionTemplate transactionTemplate;

    UserDao(PlatformTransactionManager transactionManager, TransactionTemplate transactionTemplate) {
        this.transactionManager = transactionManager;
        this.transactionTemplate = transactionTemplate;
    }

    /**
     * if(parent txn present)
     * use it;
     * else
     * create new txn;
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void dbOperations() {
        // some db operation;

        boolean isTransactionActive = TransactionSynchronizationManager.isActualTransactionActive();
        String currentTransactionNAme = TransactionSynchronizationManager.getCurrentTransactionName();
        System.out.println("*".repeat(20));
        System.out.println("Propagation.REQUIRED : is transaction active: " + isTransactionActive);
        System.out.println("Propagation.REQUIRED : current transaction name: " + currentTransactionNAme);
        System.out.println("*".repeat(20));
    }

    public void dbOperationsProgrammaticWay() {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setName("Testing REQUIRED Propagation");
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
        try {
            System.out.println("*".repeat(20));
            System.out.println("is transaction active: " + TransactionSynchronizationManager.isActualTransactionActive());
            System.out.println("current transaction name: " + TransactionSynchronizationManager.getCurrentTransactionName());
            System.out.println("*".repeat(20));
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
        }
    }

    public void dbOperationsProgrammaticWay2() {
        TransactionCallback<TransactionStatus> operations = status -> {
            System.out.println("*".repeat(20));
            System.out.println("is transaction active: " + TransactionSynchronizationManager.isActualTransactionActive());
            System.out.println("current transaction name: " + TransactionSynchronizationManager.getCurrentTransactionName());
            System.out.println("*".repeat(20));
            return status;
        };
        TransactionStatus status = transactionTemplate.execute(operations);
    }
}