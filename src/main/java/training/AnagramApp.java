package training;

import java.io.IOException;
import java.util.Arrays;

public class AnagramApp {

	int size;

	int count;

	char[] charArray;

	public AnagramApp(char[] charArray) {
		
		this.count = 0;
		this.charArray = charArray;
		this.size = this.charArray.length;
	}

	public  void doAnagram(int newSize) {
	
		if (newSize == 1) // if too small, return;
			return;
		// for each position,
		for (int i = 0; i < newSize; i++) {
			doAnagram(newSize - 1); // anagram remaining
			if (newSize == 2) // if innermost,
				display();
			rotate(newSize); // rotate word
		}
	}

	// rotate left all chars from position to end
	public void rotate(int newSize) {
		int i;
		int position = size - newSize;
		// save first letter
		char temp = charArray[position];
		// shift others left
		for (i = position + 1; i < size; i++)
			charArray[i - 1] = charArray[i];
		// put first on right
		charArray[i - 1] = temp;
		
	}

	public void display() {
		System.out.print(++count + " ");
		for (int i = 0; i < size; i++)
			System.out.print(charArray[i]);
		System.out.println();
	}

	@Override
	public String toString() {
		String result ="";
		for(char ch:charArray){
			result+=ch;
		}
		return result;
	}
	
	
}
