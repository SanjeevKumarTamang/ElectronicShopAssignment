package electronicshop;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;
/**
 * @author sanjeev kumar tamang
 */
public class TEST {

    public static void main(String args[]) throws StockLimitException {
        Scanner scanner = new Scanner(System.in);

        Stocks stock = new Stocks();
        stock.populateStockFromFile("Stocks.txt");

        populateFromConsole(scanner, stock);
        System.exit(0);
    }

    private static void populateFromConsole(Scanner scanner, Stocks stock) {

        System.out.println("\n\n***************************************************************************");
        System.out.println("Type yes to add more electronics in the stock else no to exit the program?");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            try {
                stock.populateStockFromConsole();
            } catch (StockLimitException ex) {
                System.out.println(ex.returnMsg());
            } finally {
                stock.writeIntoRepository();
                System.out.println("Completed addition of the stocks. Please see Repository.txt file to see the product list.");
                System.out.println("Exitting from program");
            }
        }
    }
}