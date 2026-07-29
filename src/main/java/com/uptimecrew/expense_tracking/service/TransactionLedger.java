package com.uptimecrew.expense_tracking.service;

import com.uptimecrew.expense_tracking.model.Transaction;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable, in-memory collection of {@link Transaction} records keyed by id.
 *
 * <p>Both constructors take a defensive copy of the supplied data, and no method
 * exposes the internal map or a mutator, so a {@code TransactionLedger} cannot be
 * altered after construction.
 */
public final class TransactionLedger {

    private final Map<String, Transaction> records;

    /**
     * Creates a ledger from a collection of transactions, keyed by each transaction's id.
     *
     * @param transactions the transactions to copy into the ledger; must not be null or contain null elements
     * @throws NullPointerException if {@code transactions} or any element is null
     */
    public TransactionLedger(Collection<Transaction> transactions) {
        Objects.requireNonNull(transactions, "transactions must not be null");
        Map<String, Transaction> copy = new HashMap<>();
        for (Transaction transaction : transactions) {
            Objects.requireNonNull(transaction, "transactions must not contain null");
            copy.put(transaction.getId(), transaction);
        }
        this.records = copy;
    }

    /**
     * Creates a ledger from a pre-keyed map of transactions.
     *
     * @param transactions the id-to-transaction map to copy into the ledger; must not be null
     * @throws NullPointerException if {@code transactions} is null
     */
    public TransactionLedger(Map<String, Transaction> transactions) {
        Objects.requireNonNull(transactions, "transactions must not be null");
        this.records = new HashMap<>(transactions);
    }

    /**
     * Returns the number of transactions held in this ledger.
     *
     * @return the transaction count
     */
    public int size() {
        return records.size();
    }

    /**
     * Looks up a transaction by id.
     *
     * @param id the transaction id to look up
     * @return an {@link Optional} containing the matching transaction, or empty if none is found
     */
    public Optional<Transaction> findById(String id) {
        return Optional.ofNullable(records.get(id));
    }

    /**
     * Finds transactions whose merchant name contains the given fragment (case-insensitive)
     * and whose amount is strictly greater than the given threshold.
     *
     * @param merchantFragment the case-insensitive substring to match against merchant names; must not be null
     * @param threshold the exclusive lower bound on amount; must not be null
     * @return an unmodifiable list of matching transactions, sorted by amount then merchant name
     * @throws NullPointerException if {@code merchantFragment} or {@code threshold} is null
     */
    public List<Transaction> findByMerchantAbove(String merchantFragment, BigDecimal threshold) {
        Objects.requireNonNull(merchantFragment, "merchantFragment must not be null");
        Objects.requireNonNull(threshold, "threshold must not be null");
        return records.values().stream()
                .filter(transaction -> transaction.getMerchantName().toLowerCase().contains(merchantFragment.toLowerCase())
                        && transaction.getAmount().compareTo(threshold) > 0)
                .sorted(Comparator.comparing(Transaction::getAmount).thenComparing(Transaction::getMerchantName))
                .toList();
    }
}
