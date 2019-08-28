package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.InvoiceItem;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class InvoiceDaoTest {

    @Autowired
    protected InvoiceItemDao invoiceItemDao;
    @Autowired
    protected ItemDao itemDao;
    @Autowired
    protected InvoiceDao invoiceDao;
    @Autowired
    protected CustomerDao customerDao;

    @Before
    public void setUp() throws Exception {
        List<InvoiceItem> invoiceItemList = invoiceItemDao.getAllInvoiceItems();
        invoiceItemList.stream().forEach(invoiceItem -> invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId()));


    }

    @Test
    public void addInvoice() {
    }

    @Test
    public void deleteInvoice() {
    }

    @Test
    public void getInvoiceByCustomerId() {
    }
}