// path: src/main/java/com/uptimecrew/expense_tracking/model/TransactionDraft.java
package com.uptimecrew.expense_tracking.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

/**
 * An immutable, not-yet-finalized transaction, e.g. one pending user confirmation
 * before being promoted to a {@link Transaction}.
 *
 * <p>Unlike {@link Transaction}, a draft's amount may be zero (but never negative).
 */
public final class TransactionDraft {

    private final String id;
    private final BigDecimal amount;
    private final String merchantName;
    private final LocalDate occurredOn;

    /**
     * Creates a transaction draft.
     *
     * @param id the draft's id; must not be null
     * @param amount the draft amount; must not be null and must not be negative
     * @param merchantName the merchant name; must not be null
     * @param occurredOn the calendar date the transaction occurred on; must not be null
     * @throws NullPointerException if any argument is null
     * @throws IllegalArgumentException if {@code amount} is negative
     */
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

    /**
     * @return the draft's id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the draft amount, scale 2, rounded {@link RoundingMode#HALF_UP}
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @return the merchant name
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * @return the calendar date the transaction occurred on
     */
    public LocalDate getOccurredOn() {
        return occurredOn;
    }

    /**
     * Compares this draft to another for equality based on all fields.
     *
     * @param o the object to compare against
     * @return true if {@code o} is a {@code TransactionDraft} with the same field values
     */
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

    /**
     * @return a hash code consistent with {@link #equals(Object)}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, amount, merchantName, occurredOn);
    }
}
