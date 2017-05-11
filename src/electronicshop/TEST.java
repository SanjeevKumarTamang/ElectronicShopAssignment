package electronicshop;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author rajkumar
 */
public class TEST {

    public static void main(String args[]) throws StockLimitException {

        Stocks stock = new Stocks();
        stock.populateStockFromFile("Stocks.txt");

        try {
            stock.populateStockFromConsole();
            stock.writeIntoRepository();
        } catch (StockLimitException ex) {
            System.out.println(ex.returnMsg());
        }
    }
}