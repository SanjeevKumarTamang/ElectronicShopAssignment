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
public class Laptop extends Electronic{
    
    private int ram;
    private String operatingSystem;
    
    public Laptop(int warranty, String brandName,double screenSize,int ram,String operatingSystem,int type,int quantity){
        super(warranty,brandName,screenSize,type,quantity);
        this.ram=ram;
        this.operatingSystem=operatingSystem;
    }


    @Override
    public String toString() {
        return super.toString()+ "\tRam: "+ram+ "\tOperating System: "+operatingSystem;
    }
    
}
