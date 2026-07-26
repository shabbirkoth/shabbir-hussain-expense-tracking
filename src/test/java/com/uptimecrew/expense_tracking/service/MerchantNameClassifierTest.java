package com.uptimecrew.expense_tracking.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.uptimecrew.expense_tracking.model.Transaction;
import com.uptimecrew.expense_tracking.model.TransactionKind;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MerchantNameClassifierTest {

    @Test
    void classifies_known_keyword_merchant_as_deductible() {
        TransactionClassifier classifier = new MerchantNameClassifier();
        Transaction transaction = new Transaction(
            "txn-synth-001",
            "acct-synth-001",
            new BigDecimal("487.50"),
            "Uber Technologies",
            LocalDate.of(2026, 3, 1)
        );

        TransactionKind result = classifier.classify(transaction);

        assertNotNull(result);
        assertEquals(TransactionKind.DEDUCTIBLE, result);
    }

    @Test
    void classifies_unknown_merchant_as_non_deductible() {
        TransactionClassifier classifier = new MerchantNameClassifier();
        Transaction transaction = new Transaction(
            "txn-synth-002",
            "acct-synth-001",
            new BigDecimal("487.50"),
            "Whole Foods Market",
            LocalDate.of(2026, 3, 1)
        );

        TransactionKind result = classifier.classify(transaction);

        assertNotNull(result);
        assertEquals(TransactionKind.NON_DEDUCTIBLE, result);
    }
}
