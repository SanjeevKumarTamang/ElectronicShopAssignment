package electronicshop;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;

/**
 *
 * @author rajkumar
 */
public class Electronic implements Serializable{
    private static final long serialVersionUID = 1L;

    private int warranty;

    public String getBrandName() {
        return brandName;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public int getType() {
        return type;
    }

    private String brandName;
    private double screenSize;
    private int type;

    public double getQuantity() {
        return quantity;
    }

    private int quantity;
    
    public Electronic(){
        this.warranty = 2;
    }
    public Electronic(int warranty, String brandName,double screenSize,int type,int quantity){
        this.warranty = warranty;
        this.brandName=brandName;
        this.screenSize=screenSize;
        this.type=type;
        this.quantity=quantity;
    }
    
    public int getWarranty(){
        return this.warranty;
    }
    public void setWarranty(int wa){
        this.warranty = wa;
    }

    @Override
    public String toString() {
        return "Type: " + getType()+ "\tWarranty: " + getWarranty()+ "\tBrand Name: " + getBrandName() + "\tScreenSize: "+getScreenSize()+ "\tQuantity: "+getQuantity();
    }
}
