package com.company.U1M6Summative.controller;

import com.company.U1M6Summative.dao.CustomerDao;
import com.company.U1M6Summative.dao.InvoiceDao;
import com.company.U1M6Summative.dao.InvoiceItemDao;
import com.company.U1M6Summative.dao.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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
//
//    @RequestMapping(value = "/customers", method = RequestMethod.POST)
//    public Customer createCustomer(@RequestBody Customer customer) {
//        customerDao.addCustomer(customer);
//        return customer;
//    }
//
//    @RequestMapping(value = "/items", method = RequestMethod.POST)
//    public Item createItem(@RequestBody Item item) {
//        itemDao.addItem(item);
//        return item;
//    }
//
//    @RequestMapping(value = "/items/item_id", method = RequestMethod.GET)
//    public Item getItemById(@PathVariable(name="item_id") int item_id) {
//        return itemDao.getItem(item_id);
//    }
//
//    @RequestMapping(value = "/items/item_id", method = RequestMethod.DELETE)
//    public void deleteItemById(@PathVariable(name="item_id") int item_id) {
//        itemDao.deleteItem(item_id);
//    }
//
//    @RequestMapping(value = "/items/item_id", method= RequestMethod.PUT)
//    public void updateItemById(@RequestBody Item item, @PathVariable(name="item_id") int item_id) {
//        itemDao.updateItem(item);
//    }



}
