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
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
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

        List<Item> itemList = itemDao.getAllItems();
        itemList.stream().forEach(item -> itemDao.deleteItem(item.getItemId()));

        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
        invoiceList.stream().forEach(invoice -> invoiceDao.deleteInvoice(invoice.getInvoiceId()));

        List<Customer> customerList = customerDao.getAllCustomers();
        customerList.stream().forEach(customer -> customerDao.deleteCustomer(customer.getCustomerId()));
    }

    @Test
    public void addGetDeleteInvoice() {
        Customer customer = new Customer();
        customer.setFirstName("Mario");
        customer.setLastName("Plumber");
        customer.setEmail("Mario@nintendo.com");
        customer.setCompany("Super Mario Bros");
        customer.setPhone("212-567-1234");
        customer = customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 11, 22));
        invoice.setPickupDate(LocalDate.of(2019, 11, 23));
        invoice.setReturnDate(LocalDate.of(2019, 11, 26));
        invoice.setLateFee(new BigDecimal("1.99"));
        invoice = invoiceDao.addInvoice(invoice);

        Invoice invoice2 = invoiceDao.getInvoiceById(invoice.getInvoiceId());
        assertEquals(invoice, invoice2);

        invoiceDao.deleteInvoice(invoice.getInvoiceId());
        invoice2 = invoiceDao.getInvoiceById(invoice.getInvoiceId());

        assertNull(invoice2);
    }

    @Test
    public void getAllInvoices() {
        Customer customer = new Customer();
        customer.setFirstName("Mario");
        customer.setLastName("Plumber");
        customer.setEmail("Mario@nintendo.com");
        customer.setCompany("Super Mario Bros");
        customer.setPhone("212-567-1234");
        customer = customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 11, 22));
        invoice.setPickupDate(LocalDate.of(2019, 11, 23));
        invoice.setReturnDate(LocalDate.of(2019, 11, 26));
        invoice.setLateFee(new BigDecimal("1.99"));
        invoiceDao.addInvoice(invoice);

        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 03, 31));
        invoice.setPickupDate(LocalDate.of(2019, 04, 01));
        invoice.setReturnDate(LocalDate.of(2019, 04, 03));
        invoice.setLateFee(new BigDecimal("1.99"));
        invoiceDao.addInvoice(invoice);

        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
        assertEquals(invoiceList.size(), 2);
    }

    @Test
    public void updateInvoice() {
        Customer customer = new Customer();
        customer.setFirstName("Mario");
        customer.setLastName("Plumber");
        customer.setEmail("Mario@nintendo.com");
        customer.setCompany("Super Mario Bros");
        customer.setPhone("212-567-1234");
        customer = customerDao.addCustomer(customer);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 11, 22));
        invoice.setPickupDate(LocalDate.of(2019, 11, 23));
        invoice.setReturnDate(LocalDate.of(2019, 11, 26));
        invoice.setLateFee(new BigDecimal("1.99"));
        invoice = invoiceDao.addInvoice(invoice);

        invoice.setOrderDate(LocalDate.of(2019, 03, 31));
        invoice.setPickupDate(LocalDate.of(2019, 04, 01));
        invoice.setReturnDate(LocalDate.of(2019, 04, 03));
        invoiceDao.addInvoice(invoice);

        Invoice invoice2 = invoiceDao.getInvoiceById(invoice.getInvoiceId());
        assertEquals(invoice, invoice2);
    }

}