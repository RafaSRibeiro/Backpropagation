package program;

import backpropagation.Backpropagation;
import sun.rmi.runtime.Log;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends JFrame{
    public static ArrayList<int[]> letters = new ArrayList<int[]>();
    public static ArrayList<int[]> tLetters = new ArrayList<int[]>();

    public Main() {

        int[] letterA1 = new int[]{-1, -1, 1, 1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, 1, -1, 1, -1, -1, -1, -1, 1, -1, 1, -1, -1, -1, 1, 1, 1, 1, 1, -1, -1, 1, -1, -1, -1, 1, -1, -1, 1, -1, -1, -1, 1, -1, 1, 1, 1, -1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0};


//        setTitle("Backpropagation");
//        setResizable(true);
//        this.setExtendedState( this.getExtendedState()| JFrame.MAXIMIZED_BOTH );


//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setSize(800, 465);
//        setVisible(true);

        letters.add(letterA1);

        Backpropagation backpropagation = new Backpropagation(letters);
        backpropagation.training();
        System.out.println(Arrays.toString(backpropagation.recognition(letterA1)));
    }

    //metodo para ler os arquivos da pasta /training set
    public static void LetterReader(){
        int [] letter = new int[63];
        int [] tLetter = new int[7];
        File files[];
        File folder = new File("/training set");
        files = folder.listFiles();

        for(int i = 0; i < files.length; i++){
            try {
                int rowCount = 0;
                BufferedReader br = new BufferedReader(new FileReader(files[i].getPath()));
                while (br.ready()) { //lê o arquivo enquanto houver linhas
                    rowCount++;
                    String row = br.readLine().trim();
                    if(rowCount<9) {
                        for (int j = 0; j < row.length(); j++) {
                            Character currentChar = row.charAt(j);
                            if (currentChar.equals("."))
                                letter[j] = -1;
                            else if (currentChar.equals("#"))
                                letter[j] = 1;
                            else
                                letter[j] = 0;
                        }
                    }else{
                        for (int j = 0; j < row.length(); j++) {
                            Character currentChar = row.charAt(j);
                            if (currentChar.equals("."))
                                tLetter[j] = 0;
                            else
                                tLetter[j] = 1;
                        }
                    }
                }
                br.close();
                letters.add(letter);
                tLetters.add(tLetter);
            } catch (IOException ioe) {
            }
        }
    }

    public static void main(String args[]) {
        new Main();
    }
}
