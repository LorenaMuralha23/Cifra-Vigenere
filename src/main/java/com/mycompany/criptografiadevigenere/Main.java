package com.mycompany.criptografiadevigenere;

import java.io.File;
import javax.swing.JFileChooser;

public class Main {
    public static void main(String[] args) {
        Cryptography cryptography = new Cryptography();

        // Cria um JFileChooser
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setDialogTitle("Selecione um arquivo para encriptografar");

        // Define que pode selecionar apenas arquivos (pode ser modificado para pastas)
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Abre o FileChooser e captura a resposta do usuário (se clicou em "Abrir" ou "Cancelar")
        int result = fileChooser.showOpenDialog(null);

        // Se o usuário clicou em "Abrir", pegamos o arquivo selecionado
        if (result == JFileChooser.APPROVE_OPTION) {
            File fileToEncrypt = fileChooser.getSelectedFile();
            String key = "limaolimaolimlimaolimaolimlimaolimaolimlimaolimaolim";
            cryptography.encrypt(fileToEncrypt, key);
        } else {
            System.out.println("Nenhum arquivo foi selecionado.");
        }
        
        fileChooser.setDialogTitle("Selecione um arquivo para decriptografar");

        // Define que pode selecionar apenas arquivos (pode ser modificado para pastas)
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Abre o FileChooser e captura a resposta do usuário (se clicou em "Abrir" ou "Cancelar")
        result = fileChooser.showOpenDialog(null);

        // Se o usuário clicou em "Abrir", pegamos o arquivo selecionado
        if (result == JFileChooser.APPROVE_OPTION) {
            File fileToDecrypt = fileChooser.getSelectedFile();
            String key = "limaolimaolimlimaolimaolimlimaolimaolimlimaolimaolim";
            cryptography.decrypted(fileToDecrypt, key);
        } else {
            System.out.println("Nenhum arquivo foi selecionado.");
        }
    }
    
}
