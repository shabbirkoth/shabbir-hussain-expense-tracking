package com.uptimecrew.expense_tracking.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

/**
 * An immutable record of a single account transaction.
 *
 * <p>Amounts are always stored as a {@link BigDecimal} with scale 2, rounded
 * {@link RoundingMode#HALF_UP}, and must be strictly positive.
 */
public final class Transaction {

    private final String id;
    private final String accountId;
    private final BigDecimal amount;
    private final String merchantName;
    private final LocalDate occurredOn;

    /**
     * Creates a transaction.
     *
     * @param id the unique transaction id; must not be null or blank
     * @param accountId the id of the account the transaction belongs to; must not be null or blank
     * @param amount the transaction amount; must not be null and must be strictly positive
     * @param merchantName the name of the merchant; must not be null or blank
     * @param occurredOn the calendar date the transaction occurred on; must not be null
     * @throws NullPointerException if any argument is null
     * @throws IllegalArgumentException if a string argument is blank or {@code amount} is not positive
     */
    public Transaction(String id, String accountId, BigDecimal amount, String merchantName, LocalDate occurredOn) {
        this.id = requireNonBlank(id, "id");
        this.accountId = requireNonBlank(accountId, "accountId");
        Objects.requireNonNull(amount, "amount must not be null");
        if (amount.signum() <= 0) {
            throw new IllegalArgumentException("amount must be positive: " + amount);
        }
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        this.merchantName = requireNonBlank(merchantName, "merchantName");
        this.occurredOn = Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }

    private static String requireNonBlank(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " must not be null");
        if (value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be empty");
        }
        return value;
    }

    /**
     * @return the unique transaction id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the id of the account the transaction belongs to
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * @return the transaction amount, scale 2, rounded {@link RoundingMode#HALF_UP}
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
     * Compares this transaction to another for equality based on all fields.
     *
     * @param o the object to compare against
     * @return true if {@code o} is a {@code Transaction} with the same field values
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transaction)) {
            return false;
        }
        Transaction that = (Transaction) o;
        return id.equals(that.id)
                && accountId.equals(that.accountId)
                && amount.equals(that.amount)
                && merchantName.equals(that.merchantName)
                && occurredOn.equals(that.occurredOn);
    }

    /**
     * @return a hash code consistent with {@link #equals(Object)}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, amount, merchantName, occurredOn);
    }

    /**
     * @return a diagnostic string representation of this transaction
     */
    @Override
    public String toString() {
        return "Transaction{"
                + "id='" + id + '\''
                + ", accountId='" + accountId + '\''
                + ", amount=" + amount
                + ", merchantName='" + merchantName + '\''
                + ", occurredOn=" + occurredOn
                + '}';
    }
}
