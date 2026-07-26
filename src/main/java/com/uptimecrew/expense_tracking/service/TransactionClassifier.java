package com.uptimecrew.expense_tracking.service;

import com.uptimecrew.expense_tracking.model.Transaction;
import com.uptimecrew.expense_tracking.model.TransactionKind;

public interface TransactionClassifier {

    TransactionKind classify(Transaction transaction);
}
