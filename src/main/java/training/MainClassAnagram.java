package training;

public class MainClassAnagram {

	public static void main(String[] args) {
		char[] arguments = { 'a', 'b', 'c' };
		AnagramApp anagram = new AnagramApp(arguments);

		anagram.doAnagram(arguments.length);
	}

}
