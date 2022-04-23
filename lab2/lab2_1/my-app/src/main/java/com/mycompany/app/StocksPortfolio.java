package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {

    private List<Stock> stocks = new ArrayList<>();
    private IStockmarketService stockmarket;

    public StocksPortfolio(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double getTotalValue() {
        double val = 0;

        for (Stock s : stocks) {
            val += stockmarket.lookUpPrice(s.getLabel()) * s.getQuantity();
        }

        return val;
    }
}
