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

    Backpropagation backpropagation = new Backpropagation();

    JTextArea textArea = new JTextArea();

    public static void main(String args[]) {
        new Main();
    }

    public Main() {

        setTitle("Lexic Analizer");
        setResizable(true);
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        JPanel panel = (JPanel) this.getContentPane();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JButton learnerButton = new JButton("Learn");
        learnerButton.addActionListener(new ActionListener() {
            //ação ao clica no botão
            public void actionPerformed(ActionEvent e) {
                backpropagation.training(letterReader());
                textArea.append("Treinamento finalizado\n");
                textArea.append("----------------------\n");
            }
        });

        JButton recognizerButton = new JButton("Recognition");
        recognizerButton.addActionListener(new ActionListener() {
            //ação ao clica no botão
            public void actionPerformed(ActionEvent e) {
                selectFile();
                textArea.append("Reconhecimento finalizado\n");
                textArea.append("-------------------------\n");
            }
        });


        panel.add(learnerButton);
        panel.add(recognizerButton);
        panel.add(textArea);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    //metodo para ler os arquivos da pasta /training set

    public ArrayList<int[]> letterReader() {

        File files[];
        File folder = new File("training_set");
        files = folder.listFiles();
        ArrayList<int[]> letters = new ArrayList<int[]>();

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
                        textArea.append("Caracter não reconhecido no método de aprendizagem\n");
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

        return letters;
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

    private void translateResult(double[] result) {
        double max = result[0];
        int indexMax = 0;
        for (int i = 1; i < result.length; i++) {
            if (result[i] > max) {
                max = result[i];
                indexMax = i;
            }
        }

        String letter = new String();
        switch (indexMax) {
            case 0:
                letter = "A";
                break;
            case 1:
                letter = "B";
                break;
            case 2:
                letter = "C";
                break;
            case 3:
                letter = "D";
                break;
            case 4:
                letter = "E";
                break;
            case 5:
                letter = "J";
                break;
            case 6:
                letter = "K";
                break;
        }

        textArea.append(letter + " com " + max + "% de chance.\n");
    }

    public void selectFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File recognitionFile = chooser.getSelectedFile();
            int[] letter = recognitionLetterReader(recognitionFile);
            double[] result = backpropagation.recognition(letter);
            translateResult(result);
        } else {
        }
    }
}
