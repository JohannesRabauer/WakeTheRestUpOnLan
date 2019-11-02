package de.johannes_rabauer.WakeTheRestUpOnLan;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	try {
			JavaNativeLogger.setup();
		} catch (IOException e) {
	        System.err.println("Could not initialize logger!");
			e.printStackTrace();
		}
    }
}
