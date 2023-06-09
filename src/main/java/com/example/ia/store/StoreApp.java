package com.example.ia.store;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class StoreApp {

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
        loadInventory();
        showMainScreen();
    }

    // Displays the initial set of menu choices
    private static void showMainScreen() {
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
            String input = promptForInput("Your choice:  ");

            switch (input) {
                case "1" -> showProductsScreen();
                case "2" -> showCartScreen();
                case "3" -> screen_done = true;
                default -> badInput();
            }
        }

    }

    // Displays the screen with the Products list
    private static void showProductsScreen() {
        boolean screen_done = false;
        while (!screen_done) {

            String heading = """
                    Choose from these Wonderful Products!
                    """;
            String menu = """
                                        
                    1) Add a product to your shopping cart
                    2) View your shopping cart
                    3) Return to main menu
                                    
                    """;
            System.out.println(heading);
            printProductList();
            System.out.print(menu);

            String input = promptForInput("Your choice: ");

            switch (input) {
                case "1" -> {
                    String sku = promptForInput("Enter the Product SKU: ");
                    Product p = inventory.get(sku.toUpperCase());
                    if (p != null)
                        addToCart(p);
                    else
                        badInput();
                }
                case "2" -> showCartScreen();
                case "3" -> screen_done = true;
                default -> badInput();
            }
        }

    }

    // Displays the screen with the Cart contents
    private static void showCartScreen() {
        boolean screen_done = false;
        while (!screen_done) {

            String heading = """
                    Your Shopping Cart:
                    """;
            String menu = """
                    
                    1) Remove a product from your shopping cart
                    2) Return to previous menu
                                    
                    """;
            System.out.println(heading);
            printCart();
            System.out.print(menu);

            String input = promptForInput("Your choice: ");

            switch (input) {
                case "1" -> {
                    String sku = promptForInput("Enter the Product SKU: ");
                    Product p = inventory.get(sku.toUpperCase());
                    if (p != null)
                        removeFromCart(p);
                    else
                        badInput();
                }
                case "2" -> screen_done = true;
                default -> badInput();
            }
        }


    }

    // Utility method to remove item from Cart
    // and notify the user
    private static void removeFromCart(Product p){
        cart.remove(p);
        printProduct(p);
        System.out.println("   was removed from your cart");
    }

    // Utility method to add item to cart
    // and notify the user
    private static void addToCart(Product p){
        cart.add(p);
        printProduct(p);
        System.out.println("   was added to your cart!");
    }

    // Print all products in the inventory to the CLI
    private static void printProductList() {
        for (Product p: inventory.values()) {
            printProduct(p);
        }
    }

    // Print all products in the cart to the CLI
    private static void printCart() {
        if (cart.size() == 0){
            System.out.println("Your cart is empty...");
        }
        else {
            for (Product p : cart) {
                printProduct(p);
            }
        }
    }

// Print a single Product to the CLI
    private static void printProduct(Product p){
        System.out.printf("%6s  %-40s  %8.2f\n", p.getSku(), p.getName(), p.getPrice());
    }

    // Read Products from file and insert them
    // (as Product objects) into the inventory collection
    private static void loadInventory() {

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
    }

    // Utility method to prompt the user for a string
    private static String promptForInput(String prompt){
        System.out.print(prompt);
        return cliScanner.nextLine();
    }

    // Utility method to report bad input
    private static void badInput() {
        System.out.println("""
                I'm sorry, I didn't understand that.  Try again...
                """);
    }
}
