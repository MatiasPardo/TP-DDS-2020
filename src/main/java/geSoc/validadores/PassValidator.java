package geSoc.validadores;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

import geSoc.excepcion.*;

public class PassValidator {
	public static void isStrong(String pass) {
		if (identicallCharacters(pass)) throw new ExceptionSecurityLow("Caracteres iguales");	
		if (isCommonPassword(pass)) throw new ExceptionSecurityLow("La contrasñea es muy comun");	
		if (!containsEnoughComplexity(pass)) throw new ExceptionSecurityLow("La contraseña debe tener minusculas, mayusculas, numeros y carracteres especiales.");
	}
	
	public static boolean identicallCharacters(String pass) { //detecta si tiene 3 o mas caracteres iguales 
		return Pattern.matches(".*(\\w)\\1\\1.*", pass);
	}
	
	public static boolean lessThan8characters(String pass) { //Recomendacion 2
		return pass.length() < 8;
	}
	
	public static boolean containsConsecutiveCharacters(String pass) { //Recomendacion 1
	    char passCharArray[] = pass.toCharArray();
	    boolean isConSeq = false;
	    int previousAsciiCode = 0;
	    int numSeqcount = 0;
	    for(char asciiCod: passCharArray){
	    	if(previousAsciiCode++ == asciiCod){
	    		numSeqcount++;
	    		if (numSeqcount >= 2) {
	                isConSeq = true;
	                break;
	            }
	    	}else numSeqcount = 0;
	    }
	    return isConSeq;
	}
	
	public static boolean isCommonPassword(String pass) {
		pass = pass.trim();
		File file = new File(System.getProperty("user.dir") + "/src/main/java/geSoc/model/commonpass.txt");
		try {
		    Scanner scanner = new Scanner(file);
		    while (scanner.hasNextLine()) {
		        String passFromFile = scanner.nextLine().trim();
		        if(pass.contentEquals(passFromFile)) {
		            return true;
		        }
		    }
		    return false;
		} catch(FileNotFoundException e) {
			System.out.println("File not found");
			return false;
		}
	}
	
	public static boolean hasCapitalLetters(String pass) {
		return Pattern.matches(".*[A-Z].*", pass);
	}
	
	public static boolean hasLowerCaseLetters(String pass) {
		return Pattern.matches(".*[a-z].*", pass);
	}
	
	public static boolean hasNumbers(String pass) {
		return Pattern.matches(".*[0-9].*", pass);
	}
	
	public static boolean hasSpecialCharacters(String pass) {
		return Pattern.matches(".*[$&+,:;=\\\\\\\\?@#|/'<>.^*()%!-].*", pass);
	}

	public static boolean containsEnoughComplexity(String pass){ //Recomendacion 3
		return hasCapitalLetters(pass) &&
				hasLowerCaseLetters(pass) && 
				hasNumbers(pass) &&
				hasSpecialCharacters(pass);
	}
	
}
