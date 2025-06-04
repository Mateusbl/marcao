package com.example;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {
    private List<Sale> sales = new ArrayList<>();

    public List<Sale> getAllSales() {
        return sales;
    }

    public Sale addSale(Sale sale) {
        sales.add(sale);
        return sale;
    }

    public void deleteSale(String id) {
        sales.removeIf(sale -> sale.getId().equals(id));
    }
}
