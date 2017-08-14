import java.util.Scanner;

public class Caesar_Cipher {

	private int key = 0;
	private String phrase = "";
	
	private String EncryptedResult = "";
	private String DecryptedResult = "";
	
	public Caesar_Cipher(int keys, String phrases){
		if(keys < 1 || keys > 26 || phrases.isEmpty())
			throw new IllegalArgumentException();
		this.key = keys;
		this.phrase = phrases;
	}
	
	public int getKey(){
		return this.key;
	}
	
	public String getPhrase(){
		return this.phrase;
	}
	//method to encrypt the given phrase
	private String encrypt(){
		char car = ' ';
		int t = 0;
		
		//goes through phrase letter by letter 
		for(int i = 0; i < phrase.length(); i++){
			//gets set to the current letter
			car = this.getPhrase().charAt(i);
			
			//gets set to the ASCii character value + the given key
			t = (int)car + this.getKey();
			
			//checks for a space so it can add it accordingly
			if(car == ' ')
				EncryptedResult += ' ';
			//checks for uppercase characters so the returned value can be case sensitive
			else if(Character.isUpperCase(car)){
				//checks to see if it needs to wrap text back to 'A'
				if(t > 'Z'){
					int rem = t - 26;
					EncryptedResult += String.valueOf((char)rem);
				}else
					EncryptedResult += String.valueOf((char)t);
			}
			//checks to see if 
			else if(t > 'z'){
				int rem = t - 26;
				EncryptedResult += String.valueOf((char)rem);
			}
			else
				EncryptedResult += String.valueOf((char)t);
		}
		return EncryptedResult;
	}
	
	private String decrypt(){
		
		int t = 0;
		for(int i = 0; i < phrase.length(); i++){
			char temp = this.EncryptedResult.charAt(i);
			t = (int)temp - this.getKey();
			if(temp == ' '){
				DecryptedResult += ' ';
			}
			else if(Character.isUpperCase(temp)){
				if(t < 'A'){
					int rem = t + 26;
					DecryptedResult += String.valueOf((char)rem);
				}else					
					DecryptedResult += String.valueOf((char)t);
			}
			else if(t < 'a'){
				int rem = t + 26;
				DecryptedResult += String.valueOf((char)rem);
			}else
				DecryptedResult += String.valueOf((char)t);
			
		}
		EncryptedResult = null;
		return DecryptedResult;
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num = 0;
		String str = "";
		try{
			try{
				System.out.println("Enter the string to be encrypted: ");
				str = scanner.nextLine();
		}catch (Exception e){
			System.err.println("First Operation failed");
		}
			try {
				System.out.println("Enter the key: ");
				num = scanner.nextInt();
			} catch (Exception e) {
				System.err.println("Second Operation Failed");
			}
		
		}finally{
			scanner.close();
		}
		//System.out.println(str);
		Caesar_Cipher cipher = new Caesar_Cipher(num,str);
		
		System.out.println("Encrypting... ");
		System.out.println(cipher.encrypt());
		
		
		System.out.println("Decrypting... ");
		System.out.println(cipher.decrypt());
		
		

	}

}
