package com.company.U1M6Summative.controller;

import com.company.U1M6Summative.dao.CustomerDao;
import com.company.U1M6Summative.dao.InvoiceDao;
import com.company.U1M6Summative.dao.InvoiceItemDao;
import com.company.U1M6Summative.dao.ItemDao;
import com.company.U1M6Summative.model.Customer;
import com.company.U1M6Summative.model.Invoice;
import com.company.U1M6Summative.model.InvoiceItem;
import com.company.U1M6Summative.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RentalController {
    @Autowired
    CustomerDao customerDao;
    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    InvoiceItemDao invoiceItemDao;
    @Autowired
    ItemDao itemDao;

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer) {
        customerDao.addCustomer(customer);
        return customer;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Customer getCustomer(@PathVariable(name = "customerId") int customerId) {
        return customerDao.getCustomer(customerId);
    }

    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCustomer(@PathVariable(name="customerId") int customerId) {
        customerDao.deleteCustomer(customerId);
    }

    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateCustomer(@RequestBody Customer customer, @PathVariable(name="customerId") int customerId) {
        customerDao.updateCustomer(customer);
    }

    @RequestMapping(value = "/items", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Item createItem(@RequestBody Item item) {
        itemDao.addItem(item);
        return item;
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }

    @RequestMapping(value = "/items/{itemId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Item getItemById(@PathVariable(name = "itemId") int itemId) {
        return itemDao.getItem(itemId);
    }

    @RequestMapping(value = "/items/{itemId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteItemById(@PathVariable(name = "itemId") int itemId) {
        itemDao.deleteItem(itemId);
    }

    @RequestMapping(value = "/items/{itemId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateItemById(@RequestBody Item item, @PathVariable(name = "itemId") int itemId) {
        itemDao.updateItem(item);
    }

    @RequestMapping(value = "/invoices", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Invoice createInvoice(Invoice invoice) {
        invoiceDao.addInvoice(invoice);
        return invoice;
    }

    @RequestMapping(value = "/invoices/{invoiceId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteInvoice(@PathVariable(name = "invoiceId") int invoiceId) {
        invoiceDao.deleteInvoice(invoiceId);
    }

    @RequestMapping(value = "/invoices/{customerId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Invoice> getInvoiceByCustomerId(@PathVariable(name = "customerId") int customerId) {
        return invoiceDao.getInvoiceByCustomerId(customerId);
    }

    @RequestMapping(value = "/invoiceItems", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem) {
        invoiceItemDao.addInvoiceItem(invoiceItem);
        return invoiceItem;
    }

    @RequestMapping(value = "/invoiceItems/{invoiceItemId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteInvoiceItem(@PathVariable(name = "invoiceItemId") int invoiceItemId) {
        invoiceItemDao.deleteInvoiceItem(invoiceItemId);
    }

}
