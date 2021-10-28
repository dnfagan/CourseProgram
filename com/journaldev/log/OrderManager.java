package com.journaldev.log;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class OrderManager
{
	static Logger logger = Logger.getLogger(Logger.class.getName());
	
	static String [] productsList = {"Product1", "Product2"};
    static double [] priceList = {1.75d, 5.45d};
    
    
    static void Logger()
    {
    	try
    	{
    		LogManager.getLogManager().readConfiguration(new FileInputStream("mylogging.properties"));
    	}
    	catch (SecurityException | IOException e1)
    	{
    		e1.printStackTrace();
    	}
    	logger.setLevel(Level.FINE);
    	logger.addHandler(new ConsoleHandler());
    	//adding custom handler
    	logger.addHandler(new MyHandler());
    	try
    	{
    		//FileHandler file name with max size and number of log files limit
    		Handler fileHandler = new FileHandler("C:/Projects/LoggingJavaApp/logs/logger.log", 2000, 5);
    		fileHandler.setFormatter(new MyFormatter());
    		//setting custom filter for FileHandler
    		fileHandler.setFilter(new MyFilter());
    		logger.addHandler(fileHandler);
    		
    		for(int i=0; i<1000; i++)
    		{
    			//logging messages
    			logger.log(Level.INFO, "Msg"+i);
    		}
    		logger.log(Level.CONFIG, "Config data");
    	}
    	catch (SecurityException | IOException e)
    	{
    		e.printStackTrace();
        }
    }
    
	static void getProducts()   //Print Product List
	{
	    for (int i = 0; i < productsList.length; ++i)
	    {
	        System.out.println(productsList[i] + "\t$" + priceList[i]);
	    }
	}
	
	static void getCart()        //View and Edit Cart
	{
	    int cart = 0;
	    // If statement dosn't work.. due to cart.length being nothing. :(
	    /*if (cart.length == 0)
	    {
	        System.out.println("Your cart is empty.");
	    }
	    else
	    {*/
	        System.out.println("Your cart is filled with:");
	        //for (int i = 0; i < cart.length; ++i)
	        //{
	            System.out.println(cart);//productsList(cart()));
	        //}
	    //}
	    
	    
	}
	
	static double getSub()      //Generates Subtotal
	{
	    double sub = 0;
	    
	    getCart();
	    for (int i = 0; i < priceList.length; ++i)
	    {
	        sub += 1; //priceList[cart[i]];
	    }
	    return sub;
	}
	
	static double getTax()      //Generates Subtotal
	{
	    double sub = getSub();
	    double tax = sub * .14d;
	    return tax;
	}
	
	static double getTotal()      //Generates Subtotal
	{
	    double sub = getSub();
	    double tax = getTax();
	    double total = sub + tax;
	    return total;
	}
	
	static void getPrint()
	{
	    getCart();
        System.out.println(getSub());
        System.out.println(getTax());
        System.out.println(getTotal());
	}
	
	static void getHeader()
	{
	    System.out.println("###################################");
	    System.out.println("#      Welcome to our Store!      #");
	    System.out.println("#       Take a look around.       #");
	    System.out.println("#Hope you find something you like.#");
	    System.out.println("#    I'm going to change this.    #");
	    System.out.println("###################################");
	}
	public static void main(String[] args) 
	{
        Scanner userInput = new Scanner(System.in);
        try {}
        finally {userInput.close();}
        
        String ifInput = "0";
	    
	    getHeader();
	    System.out.println("Would you like to:\n1. See Products List.\n" +
	    "2. View/Edit Items in Cart.\n3. See Subtotal\n4. See Estimated Tax.\n" +
	    "5. See Estimated Total.\n6. Check Out.\n0. Quit");
	    int pick = userInput.nextInt();
	    Logger();
	    
	    do
	    {
	        switch(pick)
	        {
	            case 1:
	                getProducts();
	                break;
	            case 2:
	                getCart();
    	            break;
	            case 3:
	                System.out.println(getSub());
	                break;
	            case 4:
	                System.out.println(getTax());
	                break;
	            case 5:
	                System.out.println(getTotal()); // outputs too many decimals.
	                break;
	            case 6:
	                getPrint();
	                System.out.println("\n\n");
	                System.out.println("Would you like to print your order?\n" +
	                "\ty for Yes, n for No");
	                ifInput = userInput.nextLine();
	                Logger();
	                pick = 0;
	                break;
	            case 0:
	                ifInput = "n";
	                break;
	            default:
	                System.out.println(userInput + " is Invaild." +
	                "Would you like to:\n1. See Products List.\n" +
	                "2. View/Edit Items in Cart.\n3. See Subtotal\n4. See Estimated Tax.\n" +
	                "5. See Estimated Total.\n6. Check Out.\n0. Quit");
	        }
	    }
	    while (pick != 0);
	    
	    if (ifInput == "y")
	    {
	        getPrint();
	        
	        System.out.println("Thank you for your Order." +
	        "Have a Wonderful Day");
	    }
	    else if (ifInput == "n")
	    {
	        System.out.println("Thank you for visiting us." +
	        "Have a Wonderful Day");
	    }
	    else
	    {
	        System.out.println("Invaild Input:\n" +
	        "Would you like to print your order?\n" +
	        "\ty for Yes, n for No");
	        ifInput = userInput.nextLine();
	        Logger();
	    }
	}
}
