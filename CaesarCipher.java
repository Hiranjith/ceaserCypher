import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;
public class CaesarCipher {
    public String encrypt(String input, int key1, int key2) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String capAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String smallAlphabet = "abcdefghijklmnopqrstuvwxyz";
        int idx=-1;
        char newChar = '^';
        int count =1;
        //Compute the shifted alphabet
        String capshiftedAlphabet1 = capAlphabet.substring(key1)+
            capAlphabet.substring(0,key1);
        String smallshiftedAlphabet1 = smallAlphabet.substring(key1)+
            smallAlphabet.substring(0,key1);

        String capshiftedAlphabet2 = capAlphabet.substring(key2)+
            capAlphabet.substring(0,key2);
        String smallshiftedAlphabet2 = smallAlphabet.substring(key2)+
            smallAlphabet.substring(0,key2);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            if(Character.isLowerCase(currChar)){
                idx = smallAlphabet.indexOf(currChar);
            }else{
                idx = capAlphabet.indexOf(currChar);
            }

            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                if (count%2!=0){
                    if(Character.isLowerCase(currChar)){

                        newChar = smallshiftedAlphabet1.charAt(idx);
                    }else{
                        newChar = capshiftedAlphabet1.charAt(idx);
                    }
                }else{
                    if(Character.isLowerCase(currChar)){

                        newChar = smallshiftedAlphabet2.charAt(idx);
                    }else{
                        newChar = capshiftedAlphabet2.charAt(idx);
                    }
                }

                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
            //Otherwise: do nothing
            count++;
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }

    public void testCaesar() {
        int key1 = 8;
        int key2 = 21;

        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypted = encrypt(message,key1 ,key2);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key1,26-key2 );
        System.out.println(decrypted);
    }
}

