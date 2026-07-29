package com.uptimecrew.expense_tracking.model;

import java.time.Instant;
import java.util.Objects;

/**
 * An immutable record of a receipt image captured for a specific transaction.
 */
public final class Receipt {

    private final String id;
    private final String transactionId;
    private final String imageRef;
    private final Instant capturedAt;

    /**
     * Creates a receipt.
     *
     * @param id the unique receipt id; must not be null or blank
     * @param transactionId the id of the transaction this receipt belongs to; must not be null or blank
     * @param imageRef a reference (e.g. storage key or URL) to the receipt image; must not be null or blank
     * @param capturedAt the instant the receipt was captured; must not be null
     * @throws NullPointerException if any argument is null
     * @throws IllegalArgumentException if a string argument is blank
     */
    public Receipt(String id, String transactionId, String imageRef, Instant capturedAt) {
        this.id = requireNonBlank(id, "id");
        this.transactionId = requireNonBlank(transactionId, "transactionId");
        this.imageRef = requireNonBlank(imageRef, "imageRef");
        this.capturedAt = Objects.requireNonNull(capturedAt, "capturedAt must not be null");
    }

    private static String requireNonBlank(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " must not be null");
        if (value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be empty");
        }
        return value;
    }

    /**
     * @return the unique receipt id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the id of the transaction this receipt belongs to
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @return a reference to the receipt image
     */
    public String getImageRef() {
        return imageRef;
    }

    /**
     * @return the instant the receipt was captured
     */
    public Instant getCapturedAt() {
        return capturedAt;
    }

    /**
     * Compares this receipt to another for equality based on all fields.
     *
     * @param o the object to compare against
     * @return true if {@code o} is a {@code Receipt} with the same field values
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Receipt)) {
            return false;
        }
        Receipt that = (Receipt) o;
        return id.equals(that.id)
                && transactionId.equals(that.transactionId)
                && imageRef.equals(that.imageRef)
                && capturedAt.equals(that.capturedAt);
    }

    /**
     * @return a hash code consistent with {@link #equals(Object)}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, transactionId, imageRef, capturedAt);
    }

    /**
     * @return a diagnostic string representation of this receipt
     */
    @Override
    public String toString() {
        return "Receipt{"
                + "id='" + id + '\''
                + ", transactionId='" + transactionId + '\''
                + ", imageRef='" + imageRef + '\''
                + ", capturedAt=" + capturedAt
                + '}';
    }
}
