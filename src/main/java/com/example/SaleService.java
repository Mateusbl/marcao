package com.example;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {
    private List<Venda> sales = new ArrayList<>();

    public List<Venda> getAllSales() {
        return sales;
    }

    public Venda addSale(Venda sale) {
        sales.add(sale);
        return sale;
    }

    public void deleteSale(Integer idVenda) {
        sales.removeIf(sale -> sale.getIdVenda().equals(idVenda));
    }
}
