package com.uptimecrew.expense_tracking.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * An immutable category that transactions can be assigned to, carrying the percentage
 * of an expense in this category that is tax-deductible.
 */
public final class ExpenseCategory {

    private static final BigDecimal MAX_PERCENT = new BigDecimal("100");

    private final String id;
    private final String name;
    private final BigDecimal deductiblePercent;

    /**
     * Creates an expense category.
     *
     * @param id the unique category id; must not be null or blank
     * @param name the category name; must not be null or blank
     * @param deductiblePercent the deductible percentage, between 0 and 100 inclusive; must not be null
     * @throws NullPointerException if any argument is null
     * @throws IllegalArgumentException if a string argument is blank, or {@code deductiblePercent} is out of range
     */
    public ExpenseCategory(String id, String name, BigDecimal deductiblePercent) {
        this.id = requireNonBlank(id, "id");
        this.name = requireNonBlank(name, "name");
        Objects.requireNonNull(deductiblePercent, "deductiblePercent must not be null");
        if (deductiblePercent.signum() < 0 || deductiblePercent.compareTo(MAX_PERCENT) > 0) {
            throw new IllegalArgumentException("deductiblePercent must be between 0 and 100: " + deductiblePercent);
        }
        this.deductiblePercent = deductiblePercent.setScale(2, RoundingMode.HALF_UP);
    }

    private static String requireNonBlank(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " must not be null");
        if (value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be empty");
        }
        return value;
    }

    /**
     * @return the unique category id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the category name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the deductible percentage, scale 2, rounded {@link RoundingMode#HALF_UP}
     */
    public BigDecimal getDeductiblePercent() {
        return deductiblePercent;
    }

    /**
     * Compares this category to another for equality based on all fields.
     *
     * @param o the object to compare against
     * @return true if {@code o} is an {@code ExpenseCategory} with the same field values
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExpenseCategory)) {
            return false;
        }
        ExpenseCategory that = (ExpenseCategory) o;
        return id.equals(that.id)
                && name.equals(that.name)
                && deductiblePercent.equals(that.deductiblePercent);
    }

    /**
     * @return a hash code consistent with {@link #equals(Object)}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, deductiblePercent);
    }

    /**
     * @return a diagnostic string representation of this category
     */
    @Override
    public String toString() {
        return "ExpenseCategory{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", deductiblePercent=" + deductiblePercent
                + '}';
    }
}
