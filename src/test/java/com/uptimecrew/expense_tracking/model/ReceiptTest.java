package com.uptimecrew.expense_tracking.model;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReceiptTest {

    @Test
    void constructs_with_valid_inputs() {
        Instant capturedAt = Instant.parse("2026-03-01T12:00:00Z");
        Receipt subject = new Receipt(
            "rcpt-synth-001",
            "txn-synth-001",
            "s3://receipts/rcpt-synth-001.jpg",
            capturedAt
        );
        assertEquals("rcpt-synth-001", subject.getId());
        assertEquals("txn-synth-001", subject.getTransactionId());
        assertEquals("s3://receipts/rcpt-synth-001.jpg", subject.getImageRef());
        assertEquals(capturedAt, subject.getCapturedAt());
    }

    @Test
    void rejects_null_capturedAt() {
        assertThrows(NullPointerException.class, () -> new Receipt(
            "rcpt-synth-001",
            "txn-synth-001",
            "s3://receipts/rcpt-synth-001.jpg",
            null
        ));
    }

    @Test
    void rejects_blank_imageRef() {
        assertThrows(IllegalArgumentException.class, () -> new Receipt(
            "rcpt-synth-001",
            "txn-synth-001",
            "   ",
            Instant.parse("2026-03-01T12:00:00Z")
        ));
    }
}
