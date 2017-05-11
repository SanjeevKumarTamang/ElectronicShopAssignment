/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electronicshop;

/**
 *
 * @author sanjeev
 */
public class StockLimitException extends Exception{

    public StockLimitException(int currentStock,int type,int currentTotalStock){
        returnMsg(currentStock,type,currentTotalStock);
    }
    
    public void returnMsg(int currentStock,int type,int currentTotalStock){
        System.out.println("currentStock = " + currentStock+"------"+currentTotalStock);
        String msg=null;
        if(currentTotalStock > StockLimit.TOTALSTOCK) msg= "total maximum stock limit reached. "+ "Maximum stocks available: "+StockLimit.TOTALSTOCK;
        if(currentStock > Stocks.PRODUCT_CODE_STOCK_LIMIT_MAP.get(type)) msg= "the maximum stock limit reached for "+ Stocks.PRODUCT_CODE_MAP.get(type)
                + ". Maximum stocks available : "+Stocks.PRODUCT_CODE_STOCK_LIMIT_MAP.get(type);
        System.out.println(msg);;
    }


    public String returnMsg() {
        return "error";
    }
}

