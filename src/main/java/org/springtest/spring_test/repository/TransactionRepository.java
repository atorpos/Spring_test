package org.springtest.spring_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springtest.spring_test.model.Transaction;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("""
        SELECT t.accountId AS accountId, COUNT(t.id) AS totalCount, COALESCE(SUM(CASE WHEN t.type = 'CREDIT' THEN t.amount ELSE 0 END), 0) AS totalCredits,
        COALESCE(SUM(CASE WHEN t.type = 'DEBIT' THEN t.amount ELSE 0 END), 0) AS totalDebits
        FROM Transaction t
            WHERE t.accountId = :accountId
        GROUP BY t.accountId
    """ )
    Optional<AccountSummaryProjection> findSummaryByAccountId(@Param("accountId") String accountId);

    }

