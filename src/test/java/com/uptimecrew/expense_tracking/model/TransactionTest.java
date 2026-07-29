package com.uptimecrew.expense_tracking.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionTest {

    @Test
    void constructs_with_valid_inputs() {
        Transaction subject = new Transaction(
            "txn-synth-001",
            "acct-synth-001",
            new BigDecimal("487.50"),
            "Office Depot",
            LocalDate.of(2026, 3, 1)
        );
        assertEquals("txn-synth-001", subject.getId());
        assertEquals("acct-synth-001", subject.getAccountId());
        assertEquals(0, new BigDecimal("487.50").compareTo(subject.getAmount()));
        assertEquals("Office Depot", subject.getMerchantName());
        assertEquals(LocalDate.of(2026, 3, 1), subject.getOccurredOn());
    }

    @Test
    void rejects_null_merchantName() {
        assertThrows(NullPointerException.class, () -> new Transaction(
            "txn-synth-001",
            "acct-synth-001",
            new BigDecimal("487.50"),
            null,
            LocalDate.of(2026, 3, 1)
        ));
    }

    @ParameterizedTest(name = "amount \"{0}\" is rejected as non-positive")
    @CsvSource({
        "-1.00",
        "0.00",
        "-0.01"
    })
    void rejects_non_positive_amount(String amount) {
        assertThrows(IllegalArgumentException.class, () -> new Transaction(
            "txn-synth-001",
            "acct-synth-001",
            new BigDecimal(amount),
            "Office Depot",
            LocalDate.of(2026, 3, 1)
        ));
    }

    @Test
    void rejects_blank_accountId() {
        assertThrows(IllegalArgumentException.class, () -> new Transaction(
            "txn-synth-001",
            "   ",
            new BigDecimal("487.50"),
            "Office Depot",
            LocalDate.of(2026, 3, 1)
        ));
    }
}
