package electronicshop;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rajkumar
 */
public class TEST {
    public static void main(String args []){        
        Stocks stock = new Stocks();
//        try{
//            stock.populateStockFromFile("Stocks.txt");
//        }
//        catch(StockLimitException ex){
//            System.out.println(ex.returnMsg());
//        }
        try{
            stock.populateStockFromConsole();
            stock.writeIntoRepository();
        }
        catch(StockLimitException ex){
            System.out.println(ex.returnMsg());
        }
        
        System.out.println("In the main Function");
        System.out.println(stock.totalCurrentStocks());
        for (int i = 0; i < stock.totalCurrentStocks(); i++){
            System.out.println(stock.stocks[i].toString());
        }
    }
}