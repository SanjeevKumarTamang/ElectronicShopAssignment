package electronicshop;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author sanjeev kumar tamang
 */
public class SmartPhone extends Electronic {
    private int storage;
    
    public SmartPhone(String brandName, double screenSize, int storage, int warranty,int type,int quantity){
        super(warranty,brandName,screenSize,type,quantity);
        this.storage = storage;      
    }

    @Override
    public String toString() {
        return super.toString() + "\tStorage: "+storage;
    }
}
