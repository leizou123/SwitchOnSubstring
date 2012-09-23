package com.lei.java.regex;

import static org.junit.Assert.assertTrue;

import org.junit.Test;



public class SwitchOnSubstringTest {
	
	@Test
	public void errorTest() {
		
		String stringInput = " test case 123 FAILED! please resubmit again! ";
		String retString = SwitchOnSubstring.handleReturnString(stringInput);
		assertTrue (retString.equals("Failed case handled!"));
		
		assertTrue (retString.equals(SwitchOnSubstring.handleReturnString2(stringInput)));
	}

	@Test
	public void skippedTest() {
		
		String stringInput = " test case 456 SKIPPED! please don't do it again! ";
		String retString = SwitchOnSubstring.handleReturnString(stringInput);
		assertTrue (retString.equals("Skipped case handled!"));
		assertTrue (retString.equals(SwitchOnSubstring.handleReturnString2(stringInput)));
	}

	@Test
	public void passedTest() {
		
		String stringInput = " test case 789 PASSED! lucky! ";
		String retString = SwitchOnSubstring.handleReturnString(stringInput);
		assertTrue (retString.equals("Passed case handled!"));
		assertTrue (retString.equals(SwitchOnSubstring.handleReturnString2(stringInput)));
	}

	@Test
	public void failedTest() {
		
		String stringInput = " test case 789 FAILED! Failed, Failed, Attention please?! ";
		String retString = SwitchOnSubstring.handleReturnString(stringInput);
		assertTrue (retString.equals("Failed case handled!"));
		assertTrue (retString.equals(SwitchOnSubstring.handleReturnString2(stringInput)));
		assertTrue (retString.equals(SwitchOnSubstring.handleReturnString2(stringInput)));
	}

	
	@Test
	public void noneTest() {
		String stringInput = null;
		String retString = SwitchOnSubstring.handleReturnString(stringInput);
		assertTrue (retString.equals("No handler found!"));
		assertTrue (retString.equals(SwitchOnSubstring.handleReturnString2(stringInput)));
		
		stringInput = "abcefg";
		retString = SwitchOnSubstring.handleReturnString(stringInput);
		assertTrue (retString.equals("No handler found!"));
		assertTrue (retString.equals(SwitchOnSubstring.handleReturnString2(stringInput)));

	}

}

