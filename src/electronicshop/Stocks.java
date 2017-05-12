package electronicshop;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author sanjeev kumar tamang
 */
public class Stocks implements StockLimit {

    public Electronic[] stocks;
    private int currentPhoneStock;
    private int currentTVStock;
    private int currentLaptopStock;
    Scanner s = new Scanner(System.in);
    String delims = ": ";

    public static Map<Integer, String> PRODUCT_CODE_MAP = new HashMap<Integer, String>() {
        {
            put(1, "Smartphone");
            put(2, "Television");
            put(3, "Laptop");
        }
    };

    public static Map<Integer, Integer> PRODUCT_CODE_STOCK_LIMIT_MAP = new HashMap<Integer, Integer>() {
        {
            put(1, StockLimit.MAXPHONESTOCK);
            put(2, StockLimit.MAXTVSTOCK);
            put(3, StockLimit.MAXLAPTOPSTOCK);
        }
    };

    public Stocks() {
        this.stocks = new Electronic[StockLimit.TOTALSTOCK];
        this.currentPhoneStock = 0;
        this.currentTVStock = 0;
        this.currentLaptopStock = 0;
    }

    public int totalCurrentStocks() {
        return this.currentLaptopStock + this.currentTVStock + this.currentPhoneStock;
    }


    public void populateStockFromConsole() throws StockLimitException {
        System.out.println("Enter the type of Electronic Product:");
        int type = Integer.parseInt(s.nextLine());

        System.out.println("Enter the quantity of stock:");
        int quantity = Integer.parseInt(s.nextLine());
        boolean canAdd=checkQuantity(quantity, type);

        if(!canAdd){
            int availableQuantityToStock=showAvailableNumber(quantity,type);
            System.out.println("Available stock number is: "+availableQuantityToStock);
            System.out.println("Enter the quantity of stock:");
            quantity = Integer.parseInt(s.nextLine());
        }
        System.out.println("Enter the brand of the product:");
        String brand = s.nextLine();

        System.out.println("Enter Screen Size of the product:");
        double screenSize = Double.parseDouble(s.nextLine());

        System.out.println("Enter warranty of the product:");
        int warranty = Integer.parseInt(s.nextLine());

        if (type == 1) {
            System.out.println("Enter Storage of the SmartPhone:");
            int storage = Integer.parseInt(s.nextLine());
            populatePhone(type, quantity, brand, screenSize, warranty, storage,"");
        } else if (type == 2) {
            System.out.println("Enter Eneryg rating of the Television:");
            int energyRating = Integer.parseInt(s.nextLine());

            System.out.println("Enter Weight of the Television:");
            int weight = Integer.parseInt(s.nextLine());

            populateTv(quantity, brand, screenSize, warranty, energyRating, weight,"");
        } else if (type == 3) {
            System.out.println("Enter RAM of the laptop:");
            int ram = Integer.parseInt(s.nextLine());

            System.out.println("Enter OS of the laptop:");
            String os = s.nextLine();

            populateLaptop(brand, screenSize, warranty, quantity, ram, os,"");
        }
    }

    private int showAvailableNumber(int quantity, int type) {
        if(type==1){
            return MAXPHONESTOCK-this.currentPhoneStock;
        }
        else if(type==2){
            return MAXTVSTOCK-this.currentTVStock;
        }
        else if(type==3){
            return MAXLAPTOPSTOCK-this.currentLaptopStock;
        }
        return 0;
    }

    private boolean checkQuantity(int quantity, int type) {
        boolean canAdd = false;
        try {
            if (type == 1) {
                if (this.currentPhoneStock + quantity <= MAXPHONESTOCK && this.totalCurrentStocks() + quantity <= TOTALSTOCK) {
                    canAdd = true;
                } else
                    throw new StockLimitException(this.currentPhoneStock + quantity, 1, this.totalCurrentStocks() + quantity,"");
            } else if (type == 2) {
                if (this.currentTVStock + quantity <= MAXTVSTOCK && this.totalCurrentStocks() + quantity <= TOTALSTOCK) {
                    canAdd = true;
                } else
                    throw new StockLimitException(this.currentTVStock + quantity, 2, this.totalCurrentStocks() + quantity,"");
            } else if (type == 3) {
                if (this.currentLaptopStock + quantity <= MAXLAPTOPSTOCK && this.totalCurrentStocks() + quantity <= TOTALSTOCK) {
                    canAdd = true;
                } else
                    throw new StockLimitException(this.currentLaptopStock + quantity, 3, this.totalCurrentStocks() + quantity,"");
            }
        } catch (StockLimitException ex) {
        }
        finally {
            return canAdd;
        }
    }

    private void populateTv(int quantity, String brand, double screenSize, int warranty, int energyRating, int weight,String line) throws StockLimitException {
        try {
            if (this.currentTVStock + quantity <= MAXTVSTOCK && this.totalCurrentStocks() + quantity <= TOTALSTOCK) {
                for (int j = this.totalCurrentStocks(); j < this.totalCurrentStocks() + quantity; j++) {
                    this.stocks[j] = new Television(warranty, brand, screenSize, energyRating, weight, 2, 1);// Assuming two years of warranty

                }
                this.currentTVStock += quantity;
            } else
                throw new StockLimitException(this.currentTVStock + quantity, 2, this.totalCurrentStocks() + quantity,line);
        } catch (StockLimitException ex) {
        }
    }

    private void populatePhone(int type, int quantity, String brand, double screenSize, int warranty, int storage,String line) {
        try {
            if (this.currentPhoneStock + quantity <= MAXPHONESTOCK && this.totalCurrentStocks() + quantity <= TOTALSTOCK) {
                for (int j = this.totalCurrentStocks(); j < this.totalCurrentStocks() + quantity; j++) {
                    this.stocks[j] = new SmartPhone(brand, screenSize, storage, warranty, type, 1);
                }
                this.currentPhoneStock += quantity;
            } else
                throw new StockLimitException(this.currentPhoneStock + quantity, 1, this.totalCurrentStocks() + quantity,line);
        } catch (StockLimitException ex) {
        }
    }

    private void populateLaptop(String brand, double screenSize, int warranty, int quantity, int ram, String os,String line) throws StockLimitException {
        try {
            if (this.currentLaptopStock + quantity <= MAXLAPTOPSTOCK && this.totalCurrentStocks() + quantity <= TOTALSTOCK) {
                for (int j = this.totalCurrentStocks(); j < this.totalCurrentStocks() + quantity; j++) {
                    this.stocks[j] = new Laptop(warranty, brand, screenSize, ram, os, 3, 1);// Assuming two years of warranty
                }
                this.currentLaptopStock += quantity;
            } else
                throw new StockLimitException(this.currentLaptopStock + quantity, 3, this.totalCurrentStocks() + quantity,line);
        } catch (StockLimitException ex) {
        }
    }


    public void populateStockFromFile(String fileName) throws StockLimitException {

        System.out.println("******************************************************");
        System.out.println("going to populate stock from file " + fileName);
        System.out.println("******************************************************\n");
        String[] inputs;

        System.out.println("Reading file............");
        int lineNumber=1;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int i = 0;
            String line;
//            System.out.println("Line No. "+lineNumber+" with record : "+br.readLine());
            while ((line = br.readLine()) != null) {
                lineNumber++;
                inputs = line.split(",");
                String[] tokens = inputs[i].split(delims);

                String brand = getBrand(inputs, 2);
                double screenSize = Double.parseDouble(getBrand(inputs, 3));
                int warranty = Integer.parseInt(getBrand(inputs, 1));
                int quantity = Integer.parseInt(getBrand(inputs, 4));

                if (tokens[0].equals("Type") & tokens[1].equals("1")) { // This is smartphone type
                    int storage = Integer.parseInt(getBrand(inputs, 5));
                    populatePhone(1, quantity, brand, screenSize, warranty, storage,line);
                } else if (tokens[0].equals("Type") & tokens[1].equals("2")) { // This is smartphone type
                    int energyRating = Integer.parseInt(getBrand(inputs, 5));
                    int weight = Integer.parseInt(getBrand(inputs, 6));
                    populateTv(quantity, brand, screenSize, warranty, energyRating, weight,line);
                } else if (tokens[0].equals("Type") & tokens[1].equals("3")) { // This is smartphone type
                    int ram = Integer.parseInt(getBrand(inputs, 5));
                    String os = getBrand(inputs, 6);
                    populateLaptop(brand, screenSize, warranty, quantity, ram, os,line);
                }
            }
            System.out.println("Number of Smartphones added in the stock : " + this.currentPhoneStock);
            System.out.println("Number of TVs added in the stock : " + this.currentTVStock);
            System.out.println("Number of Laptops added in the stock : " + this.currentLaptopStock);
            System.out.println("Completed Reading file.");


        } catch (IOException ex) {
            System.out.println("File not found" + ex.getMessage());
        }
    }


    public void writeIntoRepository() {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            fw = new FileWriter("Repository.txt", true);
            bw = new BufferedWriter(fw);

            for (int i = 0; i < totalCurrentStocks(); i++) {
                bw.write(this.stocks[i].toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }
    }


    private String getBrand(String[] inputs, int index) {
        return inputs[index].split(delims)[1];
    }
}
