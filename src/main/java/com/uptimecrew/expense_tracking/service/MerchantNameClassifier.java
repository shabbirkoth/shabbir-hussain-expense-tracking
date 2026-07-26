package com.uptimecrew.expense_tracking.service;

import com.uptimecrew.expense_tracking.model.Transaction;
import com.uptimecrew.expense_tracking.model.TransactionKind;

import java.util.Locale;
import java.util.Objects;
import java.util.Set;

/**
 * Naive placeholder strategy: flags a transaction as deductible when its merchant name
 * contains a known business-expense keyword. Day 3 replaces this with real strategies.
 */
public final class MerchantNameClassifier implements TransactionClassifier {

    private static final Set<String> DEDUCTIBLE_MERCHANT_KEYWORDS = Set.of(
            "OFFICE", "STAPLES", "UBER", "LYFT", "DELTA", "MARRIOTT", "AWS", "ADOBE"
    );

    @Override
    public TransactionKind classify(Transaction transaction) {
        Objects.requireNonNull(transaction, "transaction must not be null");
        String merchantName = transaction.getMerchantName().toUpperCase(Locale.ROOT);
        for (String keyword : DEDUCTIBLE_MERCHANT_KEYWORDS) {
            if (merchantName.contains(keyword)) {
                return TransactionKind.DEDUCTIBLE;
            }
        }
        return TransactionKind.NON_DEDUCTIBLE;
    }
}
