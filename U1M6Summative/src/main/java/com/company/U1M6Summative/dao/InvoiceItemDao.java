package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.InvoiceItem;

import java.util.List;

public interface InvoiceItemDao {
    InvoiceItem addInvoiceItem(InvoiceItem invoiceItem);

    InvoiceItem getInvoiceItem(int invoiceItemId);

    List<InvoiceItem> getAllInvoiceItems();

    void deleteInvoiceItem(int invoiceItemId);

    void updateInvoiceItem(InvoiceItem invoiceItem);

    List<InvoiceItem> getInvoiceItemByInvoiceId(int invoiceId);

    List<InvoiceItem> getInvoiceItemByItemId(int itemId);


}
