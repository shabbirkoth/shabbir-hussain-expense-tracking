package com.uptimecrew.expense_tracking.model;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpenseCategoryTest {

    @Test
    void constructs_with_valid_inputs() {
        ExpenseCategory subject = new ExpenseCategory(
            "cat-synth-001",
            "Travel",
            new BigDecimal("50.00")
        );
        assertEquals("cat-synth-001", subject.getId());
        assertEquals("Travel", subject.getName());
        assertEquals(0, new BigDecimal("50.00").compareTo(subject.getDeductiblePercent()));
    }

    @Test
    void rejects_null_name() {
        assertThrows(NullPointerException.class, () -> new ExpenseCategory(
            "cat-synth-001",
            null,
            new BigDecimal("50.00")
        ));
    }

    @Test
    void rejects_deductiblePercent_above_max() {
        assertThrows(IllegalArgumentException.class, () -> new ExpenseCategory(
            "cat-synth-001",
            "Travel",
            new BigDecimal("150.00")
        ));
    }

    @Test
    void rejects_negative_deductiblePercent() {
        assertThrows(IllegalArgumentException.class, () -> new ExpenseCategory(
            "cat-synth-001",
            "Travel",
            new BigDecimal("-1.00")
        ));
    }

    @Test
    void rejects_blank_id() {
        assertThrows(IllegalArgumentException.class, () -> new ExpenseCategory(
            "",
            "Travel",
            new BigDecimal("50.00")
        ));
    }
}
