package com.company.U1M6Summative.service;

import com.company.U1M6Summative.dao.CustomerDao;
import com.company.U1M6Summative.dao.InvoiceDao;
import com.company.U1M6Summative.dao.InvoiceItemDao;
import com.company.U1M6Summative.dao.ItemDao;
import com.company.U1M6Summative.model.Customer;
import com.company.U1M6Summative.model.Invoice;
import com.company.U1M6Summative.model.InvoiceItem;
import com.company.U1M6Summative.model.Item;
import com.company.U1M6Summative.viewmodel.RentalViewModel;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ServiceLayerTest {

    ServiceLayer service;
    CustomerDao customerDao;
    InvoiceDao invoiceDao;
    InvoiceItemDao invoiceItemDao;
    ItemDao itemDao;

//    @Before
//    public void setUp() throws Exception {
//        setUpCustomerDaoMock();
//        setUpInvoiceDaoMock();
//        setUpInvoiceItemDaoMock();
//        setUpItemDaoMock();
//
//        service = new ServiceLayer(customerDao, invoiceDao, invoiceItemDao, itemDao);
//    }

    @Test
    public void saveInvoice(){
        RentalViewModel rvm = new RentalViewModel();

        // Invoice
        rvm.setOrderDate(LocalDate.of(2019, 11, 22));
        rvm.setPickupDate(LocalDate.of(2019, 11, 23));
        rvm.setReturnDate(LocalDate.of(2019, 11, 26));
        rvm.setLateFee(new BigDecimal("1.99"));

        // Customer
        Customer customer = new Customer();
        customer.setFirstName("Mario");
        customer.setLastName("Plumber");
        customer.setEmail("Mario@nintendo.com");
        customer.setCompany("Super Mario Bros");
        customer.setPhone("212-567-1234");
        customer = service.saveCustomer(customer);

        rvm.setCustomer(customer);

        //Item
        Item item = new Item();
        item.setName("Titanic");
        item.setDescription("An epic, action-packed romance set against the ill-fated maiden voyage of the R.M.S. Titanic.");
        item.setDailyRate(new BigDecimal("2.99"));
        item = service.saveItem(item);

        rvm.setItem(item);

        //Invoice Item
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setQuantity(2);
        invoiceItem.setUnitRate(new BigDecimal("10.01"));
        invoiceItem.setDiscount(new BigDecimal(".25"));
        List<InvoiceItem> invoiceItemList = new ArrayList<>();
        invoiceItemList.add(invoiceItem);

        rvm.setInvoiceItems(invoiceItemList);

        rvm = service.saveInvoice(rvm);

        RentalViewModel fromService = service.findInvoice(rvm.getInvoiceId());

        assertEquals(rvm, fromService);
    }

    @Test
    public void findInvoice() {

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(100);
        invoice.setOrderDate(LocalDate.of(2019, 11, 22));
        invoice.setPickupDate(LocalDate.of(2019, 11, 23));
        invoice.setReturnDate(LocalDate.of(2019, 11, 26));
        invoice.setLateFee(new BigDecimal("1.99"));

        RentalViewModel rvm = service.findInvoice(1);

        Invoice invoice1 = new Invoice();
        invoice1.setOrderDate(rvm.getOrderDate());
        invoice1.setPickupDate(rvm.getPickupDate());
        invoice1.setReturnDate(rvm.getReturnDate());
        invoice1.setLateFee(rvm.getLateFee());
        invoice1.setCustomerId(rvm.getCustomer().getCustomerId());
        invoice1.setInvoiceId(rvm.getInvoiceId());

        assertEquals(invoice, invoice1);
    }

    @Test
    public void findAllInvoices() {

        List<RentalViewModel> fromService = service.findAllInvoices();

        assertEquals(1, fromService.size());
    }

    @Test
    public void updateInvoice() {
        // create an Invoice to update
        RentalViewModel rvmUpdate = new RentalViewModel();

        // new dummy data
        Invoice invoiceUpdate = new Invoice();
        invoiceUpdate.setInvoiceId(2);
        invoiceUpdate.setCustomerId(200);
        invoiceUpdate.setOrderDate(LocalDate.of(2019, 7, 4));
        invoiceUpdate.setPickupDate(LocalDate.of(2019, 7, 5));
        invoiceUpdate.setReturnDate(LocalDate.of(2019, 7, 6));
        invoiceUpdate.setLateFee(new BigDecimal("1.99"));

        // same data used in previously created Rental View Model
        // Invoice
        rvmUpdate.setInvoiceId(invoiceUpdate.getInvoiceId());
        rvmUpdate.setOrderDate(invoiceUpdate.getOrderDate());
        rvmUpdate.setPickupDate(invoiceUpdate.getPickupDate());
        rvmUpdate.setReturnDate(invoiceUpdate.getReturnDate());
        rvmUpdate.setLateFee(invoiceUpdate.getLateFee());

        // Customer
        Customer customerUpdate = new Customer();
        customerUpdate.setCustomerId(100);
        customerUpdate.setFirstName("Mario");
        customerUpdate.setLastName("Plumber");
        customerUpdate.setEmail("Mario@nintendo.com");
        customerUpdate.setCompany("Super Mario Bros");
        customerUpdate.setPhone("212-567-1234");

        // Add customer to view model
        rvmUpdate.setCustomer(customerUpdate);

        //Item
        Item itemUpdate = new Item();
        itemUpdate.setItemId(11);
        itemUpdate.setName("Titanic");
        itemUpdate.setDescription("An epic, action-packed romance set against the ill-fated maiden voyage of the R.M.S. Titanic.");
        itemUpdate.setDailyRate(new BigDecimal("2.99"));

        rvmUpdate.setItem(itemUpdate);

        //Invoice Item
        InvoiceItem invoiceItemUpdate = new InvoiceItem();
        invoiceItemUpdate.setInvoiceItemId(22);
        invoiceItemUpdate.setInvoiceId(2);
        invoiceItemUpdate.setItemId(11);
        invoiceItemUpdate.setQuantity(2);
        invoiceItemUpdate.setUnitRate(new BigDecimal("10.01"));
        invoiceItemUpdate.setDiscount(new BigDecimal(".25"));
        List<InvoiceItem> invoiceItemListUpdate = new ArrayList<>();
        invoiceItemListUpdate.add(invoiceItemUpdate);

        rvmUpdate.setInvoiceItems(invoiceItemListUpdate);

        service.updateInvoice(rvmUpdate);

        RentalViewModel rvm2 = service.findInvoice(2);

        Invoice invoiceAfterUpdate = new Invoice();
        invoiceAfterUpdate.setInvoiceId(rvm2.getInvoiceId());
        invoiceAfterUpdate.setCustomerId(rvm2.getCustomer().getCustomerId());
        invoiceAfterUpdate.setOrderDate(rvm2.getOrderDate());
        invoiceAfterUpdate.setPickupDate(rvm2.getPickupDate());
        invoiceAfterUpdate.setReturnDate(rvm2.getReturnDate());
        invoiceAfterUpdate.setLateFee(rvm2.getLateFee());

        assertEquals(invoiceAfterUpdate, invoiceUpdate);
    }

    @Test
    public void removeInvoice() {

        service.removeInvoice(3);

        RentalViewModel rvm = service.findInvoice(3);

        assertNull(rvm);

    }

    @Test
    public void saveFindFindAllCustomer() {

        Customer customer = new Customer();
        customer.setFirstName("Mario");
        customer.setLastName("Plumber");
        customer.setEmail("Mario@nintendo.com");
        customer.setCompany("Super Mario Bros");
        customer.setPhone("212-567-1234");

        customer = service.saveCustomer(customer);
        Customer fromService = service.findCustomer(customer.getCustomerId());
        assertEquals(customer, fromService);

        List<Customer> customerList = service.findAllCustomers();
        assertEquals(1, customerList.size());
        assertEquals(customer, customerList.get(0));
    }

    // more tests and mocks to read

}
