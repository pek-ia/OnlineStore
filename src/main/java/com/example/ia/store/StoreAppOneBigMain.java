package com.example.ia.store;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


// Here is an example of what NOT TO DO...
// This is one great big main program with nested logic,
// Lots of places where input is requested and output is produced,
// And very little to distinguish what is really going on.

public class StoreAppOneBigMain {

    // The collection of Products that are available for sale
    // I assume that that supply of any particular item is endless
    //
    private static final HashMap<String, Product> inventory = new HashMap<>();

    // The collection of Products that are in the user's shopping cart
    // Only a single shopping cart is available
    private static final ArrayList<Product> cart = new ArrayList<>();

    // System.in is the primary input stream
    private static final Scanner cliScanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Open products file
        FileInputStream productFile = null;
        try {
            productFile = new FileInputStream("products.csv");
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Products file not found in directory:");
            System.out.println(System.getProperty("user.dir"));
            System.exit(-1);
        }
        Scanner s = new Scanner (productFile);

        // Skip the first line
        s.nextLine();

        // Read in products, one line at a time
        while (s.hasNextLine()){
            String input = s.nextLine();

            // Split the line into components
            String[] components = input.split("\\|");
            String sku = components[0];
            String name = components[1];
            String price = components[2];
            String dept = components[3];

            // Convert price to double
            double priced = Double.parseDouble(price);

            // Create a new product object
            Product p = new Product(sku, name, priced, dept);

            // Add to the hash map
            inventory.put(sku, p);

        }
        boolean screen_done = false;
        while (!screen_done) {

            String heading = """
                    Welcome to Our Online Store
                    """;
            String menu = """
                    1) Shop from our fine products
                    2) See your shopping cart
                    3) Exit
                                    
                    """;

            System.out.println(heading);
            System.out.print(menu);
            System.out.print("Your choice:  ");
            String input = cliScanner.nextLine();

            switch (input) {
                case "1" -> {
                    boolean screen_done11 = false;
                    while (!screen_done11) {

                        String heading11 = """
                                Choose from these Wonderful Products!
                                """;
                        String menu11 = """
                                                    
                                1) Add a product to your shopping cart
                                2) View your shopping cart
                                3) Return to main menu
                                                
                                """;
                        System.out.println(heading11);
                        for (Product p1 : inventory.values()) {
                            System.out.printf("%6s  %-40s  %8.2f\n", p1.getSku(), p1.getName(), p1.getPrice());
                        }
                        System.out.print(menu11);

                        System.out.print("Your choice: ");
                        String input11 = cliScanner.nextLine();

                        switch (input11) {
                            case "1" -> {
                                System.out.print("Enter the Product SKU: ");
                                String sku = cliScanner.nextLine();
                                Product p = inventory.get(sku.toUpperCase());
                                if (p != null) {
                                    cart.add(p);
                                    System.out.printf("%6s  %-40s  %8.2f\n", p.getSku(), p.getName(), p.getPrice());
                                    System.out.println("   was added to your cart!");
                                }
                                else
                                    System.out.println("""
                                            I'm sorry, I didn't understand that.  Try again...
                                            """);
                            }
                            case "2" -> {
                                boolean screen_done1 = false;
                                while (!screen_done1) {

                                    String heading1 = """
                                            Your Shopping Cart:
                                            """;
                                    String menu1 = """
                                            
                                            1) Remove a product from your shopping cart
                                            2) Return to previous menu
                                                            
                                            """;
                                    System.out.println(heading1);
                                    if (cart.size() == 0){
                                        System.out.println("Your cart is empty...");
                                    }
                                    else {
                                        for (Product p1 : cart) {
                                            System.out.printf("%6s  %-40s  %8.2f\n", p1.getSku(), p1.getName(), p1.getPrice());
                                        }
                                    }
                                    System.out.print(menu1);

                                    System.out.print("Your choice: ");
                                    String input1 = cliScanner.nextLine();

                                    switch (input1) {
                                        case "1" -> {
                                            System.out.print("Enter the Product SKU: ");
                                            String sku = cliScanner.nextLine();
                                            Product p = inventory.get(sku.toUpperCase());
                                            if (p != null) {
                                                cart.remove(p);
                                                System.out.printf("%6s  %-40s  %8.2f\n", p.getSku(), p.getName(), p.getPrice());
                                                System.out.println("   was removed from your cart");
                                            }
                                            else
                                                System.out.println("""
                                                        I'm sorry, I didn't understand that.  Try again...
                                                        """);
                                        }
                                        case "2" -> screen_done1 = true;
                                        default -> System.out.println("""
                                                I'm sorry, I didn't understand that.  Try again...
                                                """);
                                    }
                                }


                            }
                            case "3" -> screen_done11 = true;
                            default -> System.out.println("""
                                    I'm sorry, I didn't understand that.  Try again...
                                    """);
                        }
                    }

                }
                case "2" -> {
                    boolean screen_done1 = false;
                    while (!screen_done1) {

                        String heading1 = """
                                Your Shopping Cart:
                                """;
                        String menu1 = """
                                
                                1) Remove a product from your shopping cart
                                2) Return to previous menu
                                                
                                """;
                        System.out.println(heading1);
                        if (cart.size() == 0){
                            System.out.println("Your cart is empty...");
                        }
                        else {
                            for (Product p1 : cart) {
                                System.out.printf("%6s  %-40s  %8.2f\n", p1.getSku(), p1.getName(), p1.getPrice());
                            }
                        }
                        System.out.print(menu1);

                        System.out.print("Your choice: ");
                        String input1 = cliScanner.nextLine();

                        switch (input1) {
                            case "1" -> {
                                System.out.print("Enter the Product SKU: ");
                                String sku = cliScanner.nextLine();
                                Product p = inventory.get(sku.toUpperCase());
                                if (p != null) {
                                    cart.remove(p);
                                    System.out.printf("%6s  %-40s  %8.2f\n", p.getSku(), p.getName(), p.getPrice());
                                    System.out.println("   was removed from your cart");
                                }
                                else
                                    System.out.println("""
                                            I'm sorry, I didn't understand that.  Try again...
                                            """);
                            }
                            case "2" -> screen_done1 = true;
                            default -> System.out.println("""
                                    I'm sorry, I didn't understand that.  Try again...
                                    """);
                        }
                    }


                }
                case "3" -> screen_done = true;
                default -> System.out.println("""
                        I'm sorry, I didn't understand that.  Try again...
                        """);
            }
        }

    }

    // Displays the initial set of menu choices

    // Displays the screen with the Products list

    // Displays the screen with the Cart contents

    // Utility method to remove item from Cart
    // and notify the user

    // Utility method to add item to cart
    // and notify the user

    // Print all products in the inventory to the CLI

    // Print all products in the cart to the CLI

    // Print a single Product to the CLI

    // Read Products from file and insert them
    // (as Product objects) into the inventory collection

    // Utility method to prompt the user for a string

    // Utility method to report bad input
}
