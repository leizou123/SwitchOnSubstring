package com.lei.java.regex;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// object oriented way to handle if then else  
public class SwitchOnSubstring {

	// define all ReturnCode enum
	public static enum ReturnCode {
		PASSED, SKIPPED, FAILED, ERROR; 
		
		// build pattern string, for string contains  
		static String getAllStringPattern() {
			StringBuilder sb = new StringBuilder(16);
			boolean firstone = true;
			for (ReturnCode p : ReturnCode.values()) {
				if (firstone) {
					firstone = false;
				} else {
					sb.append("|");
				}
				sb.append(p.name());
			}
			return sb.toString();
		}
	}
	
	// define handler interface 
	static private interface CaseHandler {
		public String handleReturn ();
	}

	static private class PassedCaseHandler implements CaseHandler {
		public String handleReturn () {
			return "Passed case handled!";
		}
	}


	// default handler
	final private static CaseHandler HandlerNone = new CaseHandler() {
		public String handleReturn () {
			return "No handler found!";
		}
	};
	
	final private static Pattern ReturnCodePattern = Pattern.compile( ReturnCode.getAllStringPattern());
	
	// use java regex to match string containing "PASSED" or "SKIPPED" "FAILED" "ERROR" to enum PASSED, SKIPPED, FAILED, ERROR;
	static private ReturnCode matchStringToReturnCode (String stringData)  {
		String strRequest = null;
		Matcher matcher = ReturnCodePattern.matcher(stringData);
		if (matcher.find()) {
			if (matcher.start()>=0 && matcher.end()>matcher.start()) {
				strRequest = stringData.substring(matcher.start(), matcher.end());
			}
		}
		return  strRequest==null? null : ReturnCode.valueOf(strRequest);
	}

	// setup the map structure to map enum PASSED, SKIPPED, FAILED, ERROR to their corresponding handlers 
	static private Map<ReturnCode, CaseHandler> mapHandlers = new HashMap<ReturnCode, CaseHandler> () {
		private static final long serialVersionUID = -80L;
		{
			// ErrorCaseHandler for string contains "ERROR"
            put(ReturnCode.ERROR , new CaseHandler() {
        		public String handleReturn () {
        			return "Error case handled!";
        		}
        	});

			// FailedCaseHandler for string contains "FAILED"
            put(ReturnCode.FAILED , new CaseHandler() {
        		public String handleReturn () {
        			return "Failed case handled!";
        		}
        	});

			// SkippedCaseHandler for string contains "SKIPPED"

            put(ReturnCode.SKIPPED , new CaseHandler() {
        		public String handleReturn () {
        			return "Skipped case handled!";
        		}
        	});
            
			// PassedCaseHandler for string contains "PASSED"
            put(ReturnCode.PASSED, new CaseHandler() {
        		public String handleReturn () {
        			return "Passed case handled!";
        		}
        	});
           
        }
    };


    // main method to handle the string 
    // if string contains PASSED, return PassedCaseHandler's result
    // if string contains ERROR, return ErrorCaseHandler's result
    // if string contains FAILED, return FailedCaseHandler's result
    // if string contains SKIPPED, return SkippedCaseHandler's result
    static public String handleReturnString (String stringData) {
    	ReturnCode code = stringData==null ? null : matchStringToReturnCode (stringData);
    	CaseHandler handle = ( code==null ) ? HandlerNone : mapHandlers.get(code);
    	return handle.handleReturn() ;
    }
    
    static public String handleReturnString2 (String stringData) {
    	
    	if (stringData==null) {
    		return "No handler found!";
    	} else if (stringData.contains("ERROR") ) {
    		return "Error case handled!";
    	} else if (stringData.contains("FAILED") ) {
    		return "Failed case handled!";
    	} else if (stringData.contains("SKIPPED") ) {
    		return "Skipped case handled!";
    	} else if (stringData.contains("PASSED") ) {
    		return "Passed case handled!";
    	}
    	
    	return "No handler found!";
    }

}

