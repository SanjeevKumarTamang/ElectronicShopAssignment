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

    public StockLimitException(int currentStock,int type,int currentTotalStock,String line){
        returnMsg(currentStock,type,currentTotalStock,line);
    }
    
    public void returnMsg(int currentStock,int type,int currentTotalStock,String lineValue){
        System.out.println("\n**********************************************************************************");
        System.out.print("EXCEPTION  : ");
        String msg=null;
        if(currentTotalStock > StockLimit.TOTALSTOCK) msg= "Total maximum stock limit reached. "+ "Maximum stocks available: "+StockLimit.TOTALSTOCK;
        if(currentStock > Stocks.PRODUCT_CODE_STOCK_LIMIT_MAP.get(type)) {
            String additionalMessage="";
            if(lineValue.length()>0) {additionalMessage="\nline info : "+lineValue + " \nSkipping this line";}
            msg = "Maximum stock limit reached for " + Stocks.PRODUCT_CODE_MAP.get(type) + "  " + additionalMessage;
        }System.out.println(msg);;
        System.out.println("**********************************************************************************\n");
    }


    public String returnMsg() {
        return "error";
    }
}

