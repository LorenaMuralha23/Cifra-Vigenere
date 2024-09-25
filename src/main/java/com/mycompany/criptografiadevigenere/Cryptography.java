package com.mycompany.criptografiadevigenere;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cryptography {

    String upperCaseAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String lowerCaseAlphabet = "abcdefghijklmnopqrstuvwxyz";

    public void encrypt(File fileToEncrypt, String key) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToEncrypt));
            StringBuilder builder = new StringBuilder();
            String lineReadString;
            key = key.toUpperCase();
            while ((lineReadString = bufferedReader.readLine()) != null) {
                builder.append(lineReadString);
//                builder.append("\n");
            }
            String message = builder.toString();
            builder.setLength(0);
            char messageChar;
            char keyChar;
            char encryptedChar;

            for (int i = 0; i < message.length(); i++) {
                String alphabetToUse = lowerCaseAlphabet;
                messageChar = message.charAt(i);
                keyChar = key.charAt(i);
                if (Character.isUpperCase(message.charAt(i))) {
                    alphabetToUse = upperCaseAlphabet;
                }

                int clearLetterIndex = alphabetToUse.indexOf(messageChar);
                int keyLetterIndex = upperCaseAlphabet.indexOf(keyChar);

                int encryptedLetterIndex = clearLetterIndex + keyLetterIndex;
                if (encryptedLetterIndex > 25) {
                    encryptedLetterIndex = (encryptedLetterIndex - 25) -1;
                }
                encryptedChar = alphabetToUse.charAt(encryptedLetterIndex);
                builder.append(encryptedChar);
            }

            String encryptedMsg = builder.toString();
            saveEncryptedFile(fileToEncrypt.getName(), encryptedMsg);
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Cryptography.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cryptography.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void decrypted(File fileToDecrypt, String key) {
        try {
            key = key.toUpperCase();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToDecrypt));
            StringBuilder builder = new StringBuilder();

            String readLine;
            while ((readLine = bufferedReader.readLine()) != null) {
                builder.append(readLine);
            }

            String encryptedMessage = builder.toString();
            builder.setLength(0);
            String alphabetToUse;
            char encryptedMsgChar;
            char keyChar;
            char decryptedChar;
            int clearLetterIndex;
            int keyLetterIndex;
            int ecptCharIndex;

            for (int i = 0; i < encryptedMessage.length(); i++) {
                alphabetToUse = lowerCaseAlphabet;
                encryptedMsgChar = encryptedMessage.charAt(i);
                keyChar = key.charAt(i);
                
                if (Character.isUpperCase(encryptedMsgChar)) {
                    alphabetToUse = upperCaseAlphabet;
                }
                
                keyLetterIndex = upperCaseAlphabet.indexOf(keyChar);
                ecptCharIndex = alphabetToUse.indexOf(encryptedMsgChar);
                clearLetterIndex = 25 - keyLetterIndex + (ecptCharIndex + 1);
                
                if(clearLetterIndex > 25) {
                    clearLetterIndex = (clearLetterIndex - 25) - 1;
                }
                
                decryptedChar = alphabetToUse.charAt(clearLetterIndex);
                builder.append(decryptedChar);
            }
            
            String decryptedMsg = builder.toString();
            saveDescryptFile(fileToDecrypt.getName(), decryptedMsg);
            bufferedReader.close();
        } catch (IOException e) {
        }
    }

    public void saveEncryptedFile(String encryptedFileName, String encryptedMessage) {
        FileWriter fileWriter = null;
        String currentDir = System.getProperty("user.dir");
        try {
            String encryptedMsgPath = currentDir + "\\encryptedMessages\\"
                    + encryptedFileName;
            fileWriter = new FileWriter(encryptedMsgPath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(encryptedMessage);

            System.out.println("File encrypted and saved successfully!\n");

            //closing resources
            bufferedWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Cryptography.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(Cryptography.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void saveDescryptFile(String descryptedFileName, String descryptedMessage) {
        String currentDir = System.getProperty("user.dir");
        try {
            String descryptedMsgPath = currentDir + "\\decryptedMessages\\"
                    + descryptedFileName;
            FileWriter fileWriter = new FileWriter(descryptedMsgPath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(descryptedMessage);
            
            System.out.println("File decrypted and saved successfully!\n");
            
            //closing resources
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Cryptography.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}
