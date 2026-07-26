package com.uptimecrew.expense_tracking.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TransactionKindTest {

    @Test
    void exposes_the_three_expected_constants() {
        assertArrayEquals(
            new TransactionKind[] {
                TransactionKind.DEDUCTIBLE,
                TransactionKind.NON_DEDUCTIBLE,
                TransactionKind.NEEDS_REVIEW
            },
            TransactionKind.values()
        );
    }

    @Test
    void valueOf_returns_matching_constant() {
        assertEquals(TransactionKind.DEDUCTIBLE, TransactionKind.valueOf("DEDUCTIBLE"));
    }
}
