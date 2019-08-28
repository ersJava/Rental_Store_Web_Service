package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.InvoiceItem;

public interface InvoiceItemDao {
    InvoiceItem addInvoiceItem(InvoiceItem invoiceItem);

    void deleteInvoiceItem(int invoiceItemId);
}
