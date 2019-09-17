package program;

import backpropagation.Backpropagation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends JFrame {
    public static ArrayList<int[]> letters = new ArrayList<int[]>();

    private String fileName;

    Backpropagation backpropagation = new Backpropagation(letters);

    public Main() {

        setTitle("Lexic Analizer");
        setResizable(true);
        this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );

        JPanel panel = (JPanel) this.getContentPane();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton learnerButton = new JButton("Learn");
        learnerButton.addActionListener(new ActionListener() {
            //ação ao clica no botão
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton recognizerButton = new JButton("Recognition");
        recognizerButton.addActionListener(new ActionListener() {
            //ação ao clica no botão
            public void actionPerformed(ActionEvent e) {
                selectFile();
            }
        });

        panel.add(learnerButton);
        panel.add(recognizerButton);

        letterReader();

        backpropagation.training();

//        int[] letter = recognitionLetterReader();


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    //metodo para ler os arquivos da pasta /training set
    public static void letterReader() {

        File files[];
        File folder = new File("training_set");
        files = folder.listFiles();

        for (int i = 0; i < files.length; i++) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(files[i].getPath()));
                String row = new String();
                while (br.ready()) { //lê o arquivo enquanto houver linhas
                    row += br.readLine().trim();
                }
                br.close();

                int[] letter = new int[Backpropagation.X_SIZE + Backpropagation.Y_SIZE];
                for (int j = 0; j < Backpropagation.X_SIZE; j++) {
                    Character currentChar = row.charAt(j);
                    if (currentChar.equals('.')) {
                        letter[j] = -1;
                    } else if (currentChar.equals('#')) {
                        letter[j] = 1;
                    } else {
                        throw new Exception("Caracter não reconhecido no método de aprendizagem");
                    }
                }

                for (int j = Backpropagation.X_SIZE; j < Backpropagation.X_SIZE + Backpropagation.Y_SIZE; j++) {
                    Character currentChar = row.charAt(j);
                    if (currentChar.equals('.'))
                        letter[j] = 0;
                    else
                        letter[j] = 1;
                }
                letters.add(letter);
            } catch (IOException ioe) {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private int[] recognitionLetterReader(File recognitionFile) {
//        File recognitionFile = new File("recognition/recognition.txt");

        String row = new String();
        try {
            BufferedReader br = new BufferedReader(new FileReader(recognitionFile));
            while (br.ready()) { //lê o arquivo enquanto houver linhas
                row += br.readLine().trim();
            }
            br.close();

        } catch (IOException ioe) {
        }

        int[] letter = new int[Backpropagation.X_SIZE + Backpropagation.Y_SIZE];
        for (int j = 0; j < Backpropagation.X_SIZE; j++) {
            Character currentChar = row.charAt(j);
            if (currentChar.equals('.')) {
                letter[j] = -1;
            } else if (currentChar.equals('#')) {
                letter[j] = 1;
            } else {
                letter[j] = 0;
            }
        }

        return letter;
    }

    public static void main(String args[]) {
        for (int i = 0; i < 20; i++)
        new Main();
    }

    private static void translateResult(double[] result) {
        double max = result[0];
        int indexMax = 0;
        for (int i = 1; i < result.length; i++) {
            if (result[i] > max) {
                max = result[i];
                indexMax = i;
            }
        }
        switch (indexMax) {
            case 0:
                System.out.println("A");
                break;
            case 1:
                System.out.println("B");
                break;
            case 2:
                System.out.println("C");
                break;
            case 3:
                System.out.println("D");
                break;
            case 4:
                System.out.println("E");
                break;
            case 5:
                System.out.println("J");
                break;
            case 6:
                System.out.println("K");
                break;
        }

        System.out.println("com " + max + "% de chance");
    }

    public void selectFile() {
        JFileChooser chooser = new JFileChooser();
        // optionally set chooser options ...
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File recognitionFile = chooser.getSelectedFile();
            int[] letter = recognitionLetterReader(recognitionFile);
            double[] result = backpropagation.recognition(letter);
//        System.out.println(Arrays.toString(result));
            translateResult(result);
        } else {
            // user changed their mind
        }
    }
}
