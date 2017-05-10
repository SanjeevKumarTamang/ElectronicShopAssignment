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
public class Television extends Electronic {
    
    private double energyRating;
    private double weight;
    
    public Television(int warranty,String brandName,double screenSize,double energyRating,double weight,int type,int quantity){
        super(warranty,brandName,screenSize,type,quantity);
        this.energyRating=energyRating;
        this.weight=weight;
    }

    @Override
    public String toString() {
        return super.toString() + "\tEnergy Rating: "+energyRating+ "\tWeight: "+weight;
    }
}
