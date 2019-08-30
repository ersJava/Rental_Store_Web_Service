package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.Customer;
import com.company.U1M6Summative.model.Invoice;
import com.company.U1M6Summative.model.InvoiceItem;
import com.company.U1M6Summative.model.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ItemDaoTest {

    @Autowired
    protected InvoiceItemDao invoiceItemDao;

    @Autowired
    protected InvoiceDao invoiceDao;

    @Autowired
    protected ItemDao itemDao;

    @Autowired
    protected CustomerDao customerDao;

    @Before
    public void setUp() throws Exception {

        List<InvoiceItem> invoiceItemList = invoiceItemDao.getAllInvoiceItems();
        invoiceItemList.stream().forEach(invoiceItem -> invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId()));

        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
        invoiceList.stream().forEach(invoice -> invoiceDao.deleteInvoice(invoice.getInvoiceId()));

        List<Item> itemList = itemDao.getAllItems();
        itemList.stream().forEach(item -> itemDao.deleteItem(item.getItemId()));

        List<Customer> customerList = customerDao.getAllCustomers();
        customerList.stream().forEach(customer -> customerDao.deleteCustomer(customer.getCustomerId()));

    }

    @Test
    public void addGetDeleteItem() {
        Item item = new Item();
        item.setName("Titanic");
        item.setDescription("An epic, action-packed romance set against the ill-fated maiden voyage of the R.M.S. Titanic.");
        item.setDailyRate(new BigDecimal("2.99"));
        item = itemDao.addItem(item);

        Item item1 = itemDao.getItem(item.getItemId());

        assertEquals(item, item1);

        itemDao.deleteItem(item.getItemId());

        item1 = itemDao.getItem(item.getItemId());

        assertNull(item1);
    }

    @Test
    public void getAllItems() {
        Item item = new Item();
        item.setName("Titanic");
        item.setDescription("An epic, action-packed romance set against the ill-fated maiden voyage of the R.M.S. Titanic.");
        item.setDailyRate(new BigDecimal("2.99"));
        itemDao.addItem(item);

        item = new Item();
        item.setName("The Proposal");
        item.setDescription("Faced with deportation to her native Canada, high-powered book editor Margaret Tate says she's engaged to marry Andrew Paxton, her hapless assistant.");
        item.setDailyRate(new BigDecimal("2.99"));
        itemDao.addItem(item);

        List<Item> itemList = itemDao.getAllItems();

        assertEquals(itemList.size(), 2);

    }

    @Test
    public void updateItem() {
        Item item = new Item();
        item.setName("Titanic");
        item.setDescription("An epic, action-packed romance set against the ill-fated maiden voyage of the R.M.S. Titanic.");
        item.setDailyRate(new BigDecimal("2.99"));
        itemDao.addItem(item);

        item.setName("TITANIC 1997");
        itemDao.updateItem(item);

        Item item1 = itemDao.getItem(item.getItemId());

        assertEquals(item, item1);
    }



}