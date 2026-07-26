package com.uptimecrew.expense_tracking.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class ExpenseCategory {

    private static final BigDecimal MAX_PERCENT = new BigDecimal("100");

    private final String id;
    private final String name;
    private final BigDecimal deductiblePercent;

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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getDeductiblePercent() {
        return deductiblePercent;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(id, name, deductiblePercent);
    }

    @Override
    public String toString() {
        return "ExpenseCategory{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", deductiblePercent=" + deductiblePercent
                + '}';
    }
}
