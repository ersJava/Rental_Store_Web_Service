package com.company.U1M6Summative.viewmodel;

import com.company.U1M6Summative.model.Customer;
import com.company.U1M6Summative.model.InvoiceItem;
import com.company.U1M6Summative.model.Item;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class RentalViewModel {

    private int invoiceId;
    private Customer customer;
    private LocalDate orderDate;
    private LocalDate pickupDate;
    private LocalDate returnDate;
    private BigDecimal lateFee;
    List<InvoiceItem> invoiceItems = new ArrayList<>();
    List<Item> items = new ArrayList<>();

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public BigDecimal getLateFee() {
        return lateFee;
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(int itemId) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalViewModel that = (RentalViewModel) o;
        return getInvoiceId() == that.getInvoiceId() &&
                getCustomer().equals(that.getCustomer()) &&
                getOrderDate().equals(that.getOrderDate()) &&
                getPickupDate().equals(that.getPickupDate()) &&
                getReturnDate().equals(that.getReturnDate()) &&
                getLateFee().equals(that.getLateFee()) &&
                getInvoiceItems().equals(that.getInvoiceItems()) &&
                getItems().equals(that.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceId(), getCustomer(), getOrderDate(), getPickupDate(), getReturnDate(), getLateFee(), getInvoiceItems(), getItems());
    }
}
