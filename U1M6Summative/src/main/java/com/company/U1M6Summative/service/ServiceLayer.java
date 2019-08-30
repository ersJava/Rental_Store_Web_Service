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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {

    private CustomerDao customerDao;
    private InvoiceDao invoiceDao;
    private InvoiceItemDao invoiceItemDao;
    private ItemDao itemDao;

    @Autowired
    public ServiceLayer(CustomerDao customerDao, InvoiceDao invoiceDao, InvoiceItem invoiceItem, ItemDao itemDao) {
        this.customerDao = customerDao;
        this.invoiceDao = invoiceDao;
        this.invoiceItemDao = invoiceItemDao;
        this.itemDao = itemDao;
    }

    //Invoice API

    @Transactional
    public RentalViewModel saveInvoice(RentalViewModel viewModel) {
        // Persist Invoice

        Invoice i = new Invoice();
        i.setOrderDate(viewModel.getOrderDate());
        i.setPickupDate(viewModel.getPickupDate());
        i.setReturnDate(viewModel.getReturnDate());
        i.setLateFee(viewModel.getLateFee());
        i.setCustomerId(viewModel.getCustomer().getCustomerId());
        i = invoiceDao.addInvoice(i);
        viewModel.setInvoiceId(i.getInvoiceId());

        List<InvoiceItem> invoiceItemList = viewModel.getInvoiceItems();
        invoiceItemList.stream()
                .forEach(invoiceItem ->
                {
                    invoiceItem.setInvoiceId(viewModel.getInvoiceId());
                    invoiceItemDao.addInvoiceItem(invoiceItem);
                });
        invoiceItemList = invoiceItemDao.getInvoiceItemByInvoiceId(viewModel.getInvoiceId());
        viewModel.setInvoiceItems(invoiceItemList);
        return viewModel;
    }

    public RentalViewModel findInvoice(int invoiceId) {
        Invoice invoice = invoiceDao.getInvoiceById(invoiceId);
        return buildRentalViewModel(invoice);
    }

    public List<RentalViewModel>findAllInvoices() {

        List<Invoice> invoiceList = invoiceDao.getAllInvoices();

        List<RentalViewModel> rvmList = new ArrayList<>();

        for (Invoice invoice : invoiceList) {
            RentalViewModel rvm = buildRentalViewModel(invoice);
            rvmList.add(rvm);
        }
        return rvmList;
    }

    @Transactional
    public void updateInvoice(RentalViewModel viewModel) {

        //Update the Inventory information
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(viewModel.getInvoiceId());
        invoice.setCustomerId(viewModel.getCustomer().getCustomerId());
        invoice.setPickupDate(viewModel.getPickupDate());
        invoice.setReturnDate(viewModel.getReturnDate());
        invoice.setLateFee(viewModel.getLateFee());

        invoiceDao.updateInvoice(invoice);

        // We don't know if any track have been removed so delete all associated tracks
        // and then associate the tracks in the viewModel with the album

        List<InvoiceItem> invoiceItemList = invoiceItemDao.getInvoiceItemByInvoiceId(invoice.getInvoiceId());
        invoiceItemList.stream()
                .forEach(invoiceItem -> invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId()));

        List<InvoiceItem> invoiceItems = viewModel.getInvoiceItems();
        invoiceItems.stream()
                .forEach(i ->
                {
                    i.setInvoiceId(viewModel.getInvoiceId());
                    i = invoiceItemDao.addInvoiceItem(i);
                });
    }

    @Transactional
    public void removeInvoice(int id) {

        List<InvoiceItem> invoiceItemList = invoiceItemDao.getInvoiceItemByInvoiceId(id);

        invoiceItemList.stream()
                .forEach(invoiceItem -> invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId()));

        invoiceDao.deleteInvoice(id);
    }

    // Customer API

    public Customer saveCustomer(Customer customer) {
        return customerDao.addCustomer(customer);
    }

    public Customer findCustomer(int id) {
        return customerDao.getCustomer(id);
    }

    public List<Customer> findAllCustomers() {
        return customerDao.getAllCustomers();
    }
    public void updateCustomer(Customer customer){
        customerDao.updateCustomer(customer);
    }
    public void removeCustomer(int id){
        customerDao.deleteCustomer(id);
    }

    // Item API

    public Item saveItem(Item item){
        return itemDao.addItem(item);
    }
    public Item findItem(int id){
        return itemDao.getItem(id);
    }
    public List <Item> findAllItems(){
        return itemDao.getAllItems();
    }
    public void updateItem (Item item){
        itemDao.updateItem(item);
    }
    public void removeItem(int id){
        itemDao.deleteItem(id);
    }

    // Invoice Item API

    public InvoiceItem saveInvoiceItem(InvoiceItem invoiceItem){
        return invoiceItemDao.addInvoiceItem(invoiceItem);
    }

    public InvoiceItem findInvoiceItem(int id){
        return invoiceItemDao.getInvoiceItem(id);
    }

    public List<InvoiceItem> findAllInvoiceItem(){
        return invoiceItemDao.getAllInvoiceItems();
    }

    public void updateInvoiceItem(InvoiceItem invoiceItem){
        invoiceItemDao.updateInvoiceItem(invoiceItem);
    }

    public void removeInvoiceItem(int id){
        invoiceItemDao.deleteInvoiceItem(id);
    }

    // Helper Methods
    private RentalViewModel buildRentalViewModel(Invoice invoice) {

        // Get the associated Customer
        Customer customer = customerDao.getCustomer(invoice.getCustomerId());
        // Get the associated list invoiceItems
        List<InvoiceItem> invoiceItemListInvoice = invoiceItemDao.getInvoiceItemByInvoiceId(invoice.getInvoiceId());

        // Assemble the RentalViewModel
        RentalViewModel rvm = new RentalViewModel();
        rvm.setInvoiceId(invoice.getInvoiceId());
        rvm.setOrderDate(invoice.getOrderDate());
        rvm.setPickupDate(invoice.getPickupDate());
        rvm.setReturnDate(invoice.getReturnDate());
        rvm.setLateFee(invoice.getLateFee());
        rvm.setCustomer(customer);
        rvm.setInvoiceItems(invoiceItemListInvoice);

        return rvm;
    }
}
