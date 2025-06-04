package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping
    public List<Venda> getAllSales() {
        return saleService.getAllSales();
    }

    @PostMapping
    public Venda addSale(@RequestBody Venda sale) {
        return saleService.addSale(sale);
    }

    @DeleteMapping("/{id}")
    public void deleteSale(@PathVariable Integer id) {
        saleService.deleteSale(id);
    }
}
