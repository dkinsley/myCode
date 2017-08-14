import java.util.Arrays;
import java.util.Scanner;

public class PigLatin {

	public static String Pigtranslate(String phrase){
		String result = "";
		String temp = "";
		String[] arr = phrase.split("\\s+");
		StringBuilder sb = new StringBuilder();
		for(String a : arr){
			temp = null;
			char letter = a.charAt(0);
			char secondLetter = a.charAt(1);
			
			//check for double consanant eg glove -> oveglay
			if(isConsanant(letter) && isConsanant(secondLetter)){
				temp = a + "-" + letter + secondLetter + "ay";
				sb = new StringBuilder(temp);
				sb.deleteCharAt(0);
				sb.deleteCharAt(0);
				result += sb.toString() + " ";
			}
			//checks for single consanant eg david -> avidday
			else if(isConsanant(letter)){
				temp = a + "-" + letter + "ay";
				sb = new StringBuilder(temp);
				sb.deleteCharAt(0);
				result += sb.toString() + " ";
			}else{
				temp = a + "-" + "way" + " ";
				sb = new StringBuilder(temp);
				sb.deleteCharAt(0);
				result += sb.toString() + " ";
				
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("What phrase would you like to translate?");
		String phrase = scanner.nextLine();
		
		System.out.println(Pigtranslate(phrase));

	}

	
	private static boolean isConsanant(char arr){
		String cons = "bcdfghjklmnpqrstvwxzBCDFGHJKLMNPQRSTVWXZ";
		return cons.contains(String.valueOf(arr));
	}
	
	private static boolean isVowel(char arr){
		String vow = "aeiouyAEIOUY";
		return vow.contains(String.valueOf(arr));
	}
}
