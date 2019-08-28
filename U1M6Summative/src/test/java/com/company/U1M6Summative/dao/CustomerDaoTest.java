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

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerDaoTest {

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
    public void addGetDeleteCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Mario");
        customer.setLastName("Plumber");
        customer.setEmail("Mario@nintendo.com");
        customer.setCompany("Super Mario Bros");
        customer.setPhone("212-567-1234");
        customer = customerDao.addCustomer(customer);

        Customer customer1 = customerDao.getCustomer(customer.getCustomerId());

        assertEquals(customer, customer1);

        customerDao.deleteCustomer(customer.getCustomerId());

        customer1 = customerDao.getCustomer(customer.getCustomerId());

        assertNull(customer1);
    }

    @Test
    public void getAllCustomers() {
        Customer customer = new Customer();
        customer.setFirstName("Mario");
        customer.setLastName("Plumber");
        customer.setEmail("Mario@nintendo.com");
        customer.setCompany("Super Mario Bros");
        customer.setPhone("212-567-1234");
        customerDao.addCustomer(customer);

        customer = new Customer();
        customer.setFirstName("Princess");
        customer.setLastName("Peach");
        customer.setEmail("princessp@MushroomCastle.com");
        customer.setCompany("Toadsworth");
        customer.setPhone("212-567-1234");
        customerDao.addCustomer(customer);

        List<Customer>customerList = customerDao.getAllCustomers();

        assertEquals(customerList.size(), 2);
    }

    @Test
    public void updateCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Mario");
        customer.setLastName("Plumber");
        customer.setEmail("Mario@nintendo.com");
        customer.setCompany("Super Mario Bros");
        customer.setPhone("212-567-1234");
        customerDao.addCustomer(customer);

        customer.setCompany("SUPER MARIO BROS COMPANY");
        customer.setPhone("212-111-1111");
        customerDao.updateCustomer(customer);

        Customer customer1 = customerDao.getCustomer(customer.getCustomerId());

        assertEquals(customer, customer1);


    }


}