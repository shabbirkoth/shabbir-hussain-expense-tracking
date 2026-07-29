package com.uptimecrew.expense_tracking.model;

/**
 * Classification of a transaction with respect to tax deductibility.
 */
public enum TransactionKind {
    /** The transaction is a tax-deductible business expense. */
    DEDUCTIBLE,
    /** The transaction is not a tax-deductible business expense. */
    NON_DEDUCTIBLE,
    /** The transaction's deductibility could not be determined automatically and needs manual review. */
    NEEDS_REVIEW
}
