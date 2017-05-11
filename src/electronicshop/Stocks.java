package electronicshop;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.util.Scanner;

/**
 * @author rajkumar
 */
public class Stocks implements StockLimit {

    public Electronic[] stocks;
    private int currentPhoneStock;
    private int currentTVStock;
    private int currentLaptopStock;
    Scanner s = new Scanner(System.in);

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
        System.out.println("Enter the brand of the product:");
        String brand = s.nextLine();
        System.out.println("Enter Screen Size of the product:");
        double screenSize = Double.parseDouble(s.nextLine());

        if (type == 1) {
            System.out.println("Enter Storage of the SmartPhone:");
            int storage = Integer.parseInt(s.nextLine());

            if (this.currentPhoneStock + quantity <= MAXPHONESTOCK) {
                for (int j = this.totalCurrentStocks(); j < this.totalCurrentStocks() + quantity; j++) {
                    this.stocks[j] = new SmartPhone(brand, screenSize, storage, 1, type,1);
                }
                this.currentPhoneStock += quantity;
                if (this.totalCurrentStocks() >= TOTALSTOCK) throw new StockLimitException();
            }
        }

        System.out.println("" + this.stocks.length);
    }


    public void populateStockFromFile(String fileName) throws StockLimitException {
        String delims = "[: ]+";
        String[] inputs = new String[200];
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
             inputs=line.split("\\t");
                String[] tokens = inputs[i].split(delims);
                if (tokens[0].equals("Type") & tokens[1].equals("1")) { // This is smartphone type

                    int quantity = Integer.parseInt(inputs[i + 4].split(delims)[1]);
                    int warranty = Integer.parseInt(inputs[i + 1].split(delims)[1]);
                    String brand = inputs[i + 1].split(delims)[1];
                    double screenSize = Double.parseDouble(inputs[i + 3].split(delims)[1]);
                    int storage = Integer.parseInt(inputs[i + 5].split(delims)[1]);

                    if (this.currentPhoneStock + quantity <= MAXPHONESTOCK) {
                        if (this.totalCurrentStocks() >= TOTALSTOCK) throw new StockLimitException();
                        for (int j = this.totalCurrentStocks(); j < this.totalCurrentStocks() + quantity; j++) {
                            this.stocks[j] = new SmartPhone(brand, screenSize, storage,warranty , 1, quantity); // Assuming two years of warranty
                        }
                        this.currentPhoneStock += quantity;
                    }
                }


                if (tokens[0].equals("Type") & tokens[1].equals("2")) { // This is smartphone type

                    int quantity = Integer.parseInt(inputs[i + 4].split(delims)[1]);
                    int warranty = Integer.parseInt(inputs[i + 1].split(delims)[1]);
                    String brand = inputs[i + 1].split(delims)[1];
                    double screenSize = Double.parseDouble(inputs[i + 3].split(delims)[1]);
                    int energyRating = Integer.parseInt(inputs[i + 5].split(delims)[1]);
                    int weight = Integer.parseInt(inputs[i + 6].split(delims)[1]);

                    if (this.currentTVStock + quantity <= MAXTVSTOCK) {
                        if (this.totalCurrentStocks() >= TOTALSTOCK) throw new StockLimitException();
                        for (int j = this.totalCurrentStocks(); j < this.totalCurrentStocks() + quantity; j++) {
                            this.stocks[j] =  new Television(warranty, brand, screenSize, energyRating, weight, 2,quantity);// Assuming two years of warranty
                        }
                        this.currentTVStock += quantity;
                    }
                }

                if (tokens[0].equals("Type") & tokens[1].equals("3")) { // This is smartphone type

                    int quantity = Integer.parseInt(inputs[i + 4].split(delims)[1]);
                    int warranty = Integer.parseInt(inputs[i + 1].split(delims)[1]);
                    String brand = inputs[i + 1].split(delims)[1];
                    double screenSize = Double.parseDouble(inputs[i + 3].split(delims)[1]);
                    int ram = Integer.parseInt(inputs[i + 5].split(delims)[1]);
                    String os= inputs[i + 6].split(delims)[1];

                    if (this.currentLaptopStock + quantity <= MAXLAPTOPSTOCK) {
                        if (this.totalCurrentStocks() >= TOTALSTOCK) throw new StockLimitException();
                        for (int j = this.totalCurrentStocks(); j < this.totalCurrentStocks() + quantity; j++) {
                            this.stocks[j] = new Laptop(warranty, brand, screenSize, ram, os, 3,quantity);// Assuming two years of warranty
                        }
                        this.currentLaptopStock += quantity;
                    }
                }

            }
        } catch (IOException ex) {
            System.out.println("File not found" + ex.getMessage());
        }
    }

    public void writeIntoRepository()  {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            fw = new FileWriter("Stocks.txt", true);
            bw=new BufferedWriter(fw);

            for(int i=0;i<totalCurrentStocks();i++){
                    bw.write(this.stocks[i].toString());
                    bw.newLine();
            }
//            for (Electronic electronic : this.stocks) {
//                if (electronic != null) {
//                            bw.write(electronic.toString());
//                            bw.newLine();
//                }
//            }
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
        finally {
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
}
