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
    
    public String returnMsg(){
        return "can't add the stock";
    }
}

