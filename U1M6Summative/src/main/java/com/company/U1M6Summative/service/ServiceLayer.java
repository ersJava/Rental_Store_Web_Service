//package com.company.U1M6Summative.service;
//
//import com.company.U1M6Summative.dao.CustomerDao;
//import com.company.U1M6Summative.dao.InvoiceDao;
//import com.company.U1M6Summative.dao.InvoiceItemDao;
//import com.company.U1M6Summative.dao.ItemDao;
//import com.company.U1M6Summative.model.Customer;
//import com.company.U1M6Summative.model.Invoice;
//import com.company.U1M6Summative.model.InvoiceItem;
//import com.company.U1M6Summative.model.Item;
//import com.company.U1M6Summative.viewmodel.RentalViewModel;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class ServiceLayer {
//
//    private CustomerDao customerDao;
//    private InvoiceDao invoiceDao;
//    private InvoiceItemDao invoiceItemDao;
//    private ItemDao itemDao;
//
//    @Autowired
//    public ServiceLayer(CustomerDao customerDao, InvoiceDao invoiceDao, InvoiceItem invoiceItem, ItemDao itemDao) {
//        this.customerDao = customerDao;
//        this.invoiceDao = invoiceDao;
//        this.invoiceItemDao = invoiceItemDao;
//        this.itemDao = itemDao;
//    }
//
//    //Rental API
//
//    @Transactional
//    public RentalViewModel saveInvoice(RentalViewModel viewModel) {
//        // Persist Invoice
//
//        Invoice i = new Invoice();
//        i.setOrderDate(viewModel.getOrderDate());
//        i.setPickupDate(viewModel.getPickupDate());
//        i.setReturnDate(viewModel.getReturnDate());
//        i.setLateFee(viewModel.getLateFee());
//        i.setCustomerId(viewModel.getCustomer().getCustomerId());
//        i = invoiceDao.addInvoice(i);
//        viewModel.setInvoiceId(i.getInvoiceId());
//
//
////        invoiceItems.stream()
////                .forEach(t ->
////                {
////                    t.setInvoiceId(viewModel.getInvoiceId());
////                    invoiceItemDao.addInvoiceItem(t);
////                });
////        invoiceItems = invoiceItemDao.getInvoiceItemByInvoiceId(viewModel.getInvoiceId());
////        viewModel.setInvoiceItems(invoiceItems);
//
//
//        return viewModel;
//    }
//
//    public RentalViewModel findInvoice(int invoiceId) {
//        Invoice invoice = invoiceDao.getInvoiceById(invoiceId);
//
//        return buildRentalViewModel(invoice);
//    }
//
//    public RentalViewModel findAllInvoices(int invoiceId) {
//
//        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
//
//        List<RentalViewModel> rvmList = new ArrayList<>();
//
//        for (Invoice invoice : invoiceList) {
//            RentalViewModel rvm = buildRentalViewModel(invoice);
//            rvmList.add(rvm);
//        }
//        return rvmList;
//    }
//
//    @Transactional
//    public void updateInvoice(RentalViewModel viewModel) {
//
//        //Update the Inventory information
//        Invoice invoice = new Invoice();
//        invoice.setInvoiceId(viewModel.getInvoiceId());
//        invoice.setCustomerId(viewModel.getCustomer().getCustomerId());
//        invoice.setPickupDate(viewModel.getPickupDate());
//        invoice.setReturnDate(viewModel.getReturnDate());
//        invoice.setLateFee(viewModel.getLateFee());
//
//        invoiceDao.updateInvoice(invoice);
//
//        // We don't know if any track have been removed so delete all associated tracks
//        // and then associate the tracks in the viewModel with the album
//
//        List<InvoiceItem> invoiceItemList = invoiceItemDao.getInvoiceItemByInvoiceId(invoice.getInvoiceId());
//        invoiceItemList.stream()
//                .forEach(invoiceItem -> invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId()));
//
//        List<InvoiceItem> invoiceItems = viewModel.getInvoiceItems();
//        invoiceItems.stream()
//                .forEach(i ->
//                {
//                    i.setInvoiceId(viewModel.getInvoiceId());
//                    i = invoiceItemDao.addInvoiceItem(i);
//                });
//    }
////
////    InvoiceItem invoiceItem = new InvoiceItem();
////        invoiceItem.setItemId(viewModel.getItemId());
////    List<InvoiceItem> invoiceItems = viewModel.getInvoiceItems();
////
////        List<Item> items = viewModel.getItems();
////
////        items.stream()
////                .forEach(a ->
////                {
////                    a.setItemId(viewModel.getInvoiceItems());
////                    itemDao.addItem(a);
////                })
////        items = invoiceItemDao.getItemsByInvoiceItemId(viewModel.getInvoiceItemId());
////        viewModel.setItems(items);
////
////
////        return viewModel;
//
//
//    public RentalViewModel find
//
//    // Helper Methods
//    private RentalViewModel buildRentalViewModel(Invoice invoice) {
//
//        // Get the associated Customer
//        Customer customer = customerDao.getCustomer(invoice.getCustomerId());
//        // Get the associated Items
//        List <Item> itemsList = itemDao.getItem(invoiceItemDao.getItem.());
//        // Get the associated list invoiceItems
//        List<InvoiceItem> invoiceItemList = invoiceItemDao.getInvoiceItemByInvoiceId(invoice.getInvoiceId());
//
//        // Assemble the RentalViewModel
//
//        RentalViewModel rvm = new RentalViewModel();
//        rvm.setInvoiceId(invoice.getInvoiceId());
//        rvm.setOrderDate(invoice.getOrderDate());
//        rvm.setPickupDate(invoice.getPickupDate());
//        rvm.setReturnDate(invoice.getReturnDate());
//        rvm.setLateFee(invoice.getLateFee());
//        rvm.setCustomer(customer);
//rvm.setInvoiceItems(invoiceItemList);
//rvm.setItems(items);
//
//    }
//
//    List<InvoiceItem> invoiceItems = new ArrayList<>();
//    List<Item> items = new ArrayList<>();
//}
