package com.example.demo.util;

import org.apache.log4j.Logger;

public class Reporter {

	 public static Logger getErrorLogger() {
	        return Logger.getLogger("ErrorLog");
	    }
}
