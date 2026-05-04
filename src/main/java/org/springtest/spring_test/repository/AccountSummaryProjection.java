package org.springtest.spring_test.repository;

import java.math.BigDecimal;

public interface AccountSummaryProjection {
    String getAccountId();
    Long getTotalCount();
    BigDecimal getTotalCredit();
    BigDecimal getTotalDebit();
}
