package edu.wit.cs.comp5900.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	PA5aTestCase.class,
})

public class TestSuite {
	
	static String stringOutput(String[] lines, Object[] values) {
		return String.format(String.join("", lines), values);
	}
	
}
