package com.uptimecrew.expense_tracking.service;

import com.uptimecrew.expense_tracking.model.Transaction;
import com.uptimecrew.expense_tracking.model.TransactionKind;

/**
 * Strategy for classifying a {@link Transaction} by tax deductibility.
 */
public interface TransactionClassifier {

    /**
     * Classifies the given transaction.
     *
     * @param transaction the transaction to classify; must not be null
     * @return the resulting classification
     */
    TransactionKind classify(Transaction transaction);
}
