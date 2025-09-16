package dev.shreyas.declarative;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeclarativeWayDemo {

    @Transactional(transactionManager = "userTransactionManager")
    public void updateUser() {
        System.out.println("Updating user");
    }
}
