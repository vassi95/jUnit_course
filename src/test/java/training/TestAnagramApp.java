package training;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.internal.matchers.Null;

public class TestAnagramApp {

	private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));

	}

	@Test
	public void testDisplay() {
		char[] arguments = { 'a', 'b', 'c' };
		AnagramApp anagram = new AnagramApp(arguments);
		anagram.display();
		assertEquals("1 abc" + System.lineSeparator(), outContent.toString());

	}

	@Test
	public void testRotate() {

		char[] arguments = { 'a', 'b', 'c', 'd' };
		String expected = "bcda";
		AnagramApp anagram = new AnagramApp(arguments);
		anagram.rotate(arguments.length);
		assertEquals(anagram.toString(), expected);
	}

	@Test
	public void testDoAnagram() {
		char[] arguments = { 'a', 'b', 'c' };
		String expected = "1 abc" + LINE_SEPARATOR + "2 acb" + LINE_SEPARATOR + "3 bca" + LINE_SEPARATOR + "4 bac"
				+ LINE_SEPARATOR + "5 cab" + LINE_SEPARATOR + "6 cba" + LINE_SEPARATOR;
		AnagramApp anagram = new AnagramApp(arguments);
		anagram.doAnagram(arguments.length);
		assertEquals(expected, outContent.toString());
	}
	
	@Test
	public void testAnagramConstructor(){
		char[] arguments = null;
		exception.expect(NullPointerException.class);
		AnagramApp anagram = new AnagramApp(arguments);
		
		
	}
}
