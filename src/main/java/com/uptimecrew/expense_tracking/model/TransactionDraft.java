// path: src/main/java/com/uptimecrew/expense_tracking/model/TransactionDraft.java
package com.uptimecrew.expense_tracking.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

public final class TransactionDraft {

    private final String id;
    private final BigDecimal amount;
    private final String merchantName;
    private final LocalDate occurredOn;

    public TransactionDraft(String id, BigDecimal amount, String merchantName, LocalDate occurredOn) {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(amount, "amount must not be null");
        Objects.requireNonNull(merchantName, "merchantName must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("amount must not be negative");
        }

        this.id = id;
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        this.merchantName = merchantName;
        this.occurredOn = occurredOn;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public LocalDate getOccurredOn() {
        return occurredOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransactionDraft)) {
            return false;
        }
        TransactionDraft other = (TransactionDraft) o;
        return id.equals(other.id)
            && amount.equals(other.amount)
            && merchantName.equals(other.merchantName)
            && occurredOn.equals(other.occurredOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, merchantName, occurredOn);
    }
}
