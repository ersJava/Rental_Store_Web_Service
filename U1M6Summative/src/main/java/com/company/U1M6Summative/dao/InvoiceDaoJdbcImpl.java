package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class InvoiceDaoJdbcImpl implements InvoiceDao {

    private JdbcTemplate jdbcTemplate;

    //prepared statements
    private static final String INSERT_INVOICE_SQL =
            "insert into invoice (customer_id, order_date, pickup_date, return_date, late_fee) values (?, ?, ?, ?, ?)";

    private static final String DELETE_INVOICE_SQL =
            "delete from invoice where invoice_id = ?";

    private static final String SELECT_INVOICES_BY_CUSTOMER_SQL =
            "select * from invoice where customer_id = ?";

    private static final String SELECT_INVOICE_SQL =
            "select * from invoice where invoice_id =?";

    private static final String SELECT_ALL_INVOICES_SQL =
            "select * from invoice";

    private static final String UPDATE_INVOICE_SQL =
            "update invoice set customer_id = ?, order_date = ?, pickup_date = ?, return_date = ?, late_fee = ? where invoice_id = ?";

    @Autowired
    public InvoiceDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Invoice mapRowToInvoice(ResultSet rs, int rowNum) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(rs.getInt("invoice_id"));
        invoice.setCustomerId(rs.getInt("customer_id"));
        invoice.setOrderDate(rs.getObject("order_date", LocalDate.class));
        invoice.setPickupDate(rs.getObject("pickup_date", LocalDate.class));
        invoice.setReturnDate(rs.getObject("return_date", LocalDate.class));
        invoice.setLateFee(rs.getBigDecimal("late_fee"));

        return invoice;
    }

    @Override
    public Invoice addInvoice(Invoice invoice) {
        jdbcTemplate.update(INSERT_INVOICE_SQL, invoice.getCustomerId(), invoice.getOrderDate(), invoice.getPickupDate(), invoice.getReturnDate()
                , invoice.getLateFee());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        invoice.setInvoiceId(id);
        return invoice;
    }

    @Override
    public Invoice getInvoiceById(int invoiceId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_INVOICE_SQL, this::mapRowToInvoice, invoiceId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return jdbcTemplate.query(SELECT_ALL_INVOICES_SQL, this::mapRowToInvoice);
    }

    @Override
    public void updateInvoice(Invoice invoice) {
        jdbcTemplate.update(UPDATE_INVOICE_SQL, invoice.getCustomerId(), invoice.getOrderDate(), invoice.getPickupDate(), invoice.getReturnDate()
                , invoice.getLateFee(), invoice.getInvoiceId());
    }

    @Override
    public void deleteInvoice(int invoiceId) {
        jdbcTemplate.update(DELETE_INVOICE_SQL, invoiceId);
    }

    @Override
    public List<Invoice> getInvoiceByCustomerId(int customerId) {
        return jdbcTemplate.query(SELECT_INVOICES_BY_CUSTOMER_SQL, this::mapRowToInvoice, customerId);
    }
}
