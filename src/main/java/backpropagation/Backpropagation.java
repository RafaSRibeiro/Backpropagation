package backpropagation;

import java.util.ArrayList;
import java.util.Random;

public class Backpropagation {

    ArrayList<int[]> letters = new ArrayList();

    public static final int X_SIZE = 63;
    public static final int Z_SIZE = 63;

    public static final int T_SIZE = 7;
    public static final int Y_SIZE = 7;

    private double x[] = new double[X_SIZE];
    private double z[] = new double[Z_SIZE];
    private double z_in[] = new double[Z_SIZE];
    private double y_in[] = new double[Y_SIZE];
    private double y[] = new double[Y_SIZE];
    private double t[] = new double[T_SIZE];
    private double littleDeltaJ[] = new double[X_SIZE];
    private double littleDeltaK[] = new double[Y_SIZE];
    private double littleDelta_in[] = new double[X_SIZE];
    private double learningRate = 0.2;


    private double v[][] = new double[X_SIZE][Z_SIZE];
    private double w[][] = new double[Z_SIZE][Y_SIZE];
    private double deltaW[][] = new double[Z_SIZE][Y_SIZE];
    private double deltaV[][] = new double[X_SIZE][Z_SIZE];

    public Backpropagation(ArrayList<int[]> letters) {
        this.letters = letters;
    }

    public double[] recognition(int[] letter) {
        step3(letter);
        step4();
        step5();
        return y;
    }

    public void training() {
        step0();

        for (int[] letter : letters) {
            step3(letter);
            step4();
            step5();
            step6();
            step7();
            step8();
        }
    }

    private void step0() {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < z.length; j++) {
                v[i][j] = 2 * new Random().nextDouble() - 1;
            }
        }

        for (int j = 0; j < z.length; j++) {
            for (int k = 0; k < y.length; k++) {
                w[j][k] = 2 * new Random().nextDouble() - 1;
            }
        }
    }

    private void step3(int[] letter) {
        for (int i = 0; i < X_SIZE; i++) {
            x[i] = letter[i];
        }
        for (int i = X_SIZE; i < X_SIZE + T_SIZE; i++) {
            t[i - X_SIZE] = letter[i];
        }
    }

    private void step4() {
        for (int j = 0; j < z.length; j++) {
            z_in[j] = 0;
            for (int i = 0; i < x.length; i++) {
                z_in[j] += x[i] * v[i][j];
            }
        }

        for (int j = 0; j < z.length; j++) {
            z[j] = sigmoideBipolar(z_in[j]);
        }
    }

    private void step5() {
        for (int k = 0; k < y.length; k++) {
            y_in[k] = 0;
            for (int j = 0; j < z.length; j++) {
                y_in[k] += z[j] * w[j][k];
            }
        }

        for (int k = 0; k < y.length; k++) {
            y[k] = sigmoideBipolar(y_in[k]);
        }
    }

    private void step6() {
        for (int k = 0; k < y.length; k++) {
            littleDeltaK[k] = (t[k] - y[k]) * sigmoidBipolarDerived(y_in[k]);
        }

        for (int k = 0; k < y.length; k++) {
            for (int j = 0; j < z.length; j++) {
                deltaW[j][k] = learningRate * littleDeltaK[k] * z[j];
            }
        }
    }


    private void step7() {
        for (int j = 0; j < z.length; j++) {
            littleDelta_in[j] = 0;
            for (int k = 0; k < y.length; k++) {
                littleDelta_in[j] += littleDeltaK[k] * w[j][k];
            }
        }

        for (int j = 0; j < z.length; j++) {
            littleDeltaJ[j] = littleDelta_in[j] * sigmoidBipolarDerived(z_in[j]);
        }

        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < z.length; j++) {
                deltaV[i][j] = learningRate * littleDeltaJ[j] * x[i];
            }
        }
    }

    private void step8() {
        for (int k = 0; k < y.length; k++) {
            for (int j = 0; j < z.length; j++) {
                w[j][k] = w[j][k] + deltaW[j][k];
            }
        }

        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < z.length; j++) {
                v[i][j] = v[i][j] + deltaV[i][j];
            }
        }
    }

    private static double sigmoideBinaria(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    private static double sigmoideBipolar(double x) {
        return (2 / (1 + Math.exp(-x))) - 1;
    }

    private static double sigmoidBipolarDerived(double x) {
        return (0.5 * (1 + sigmoideBipolar(x)) * (1 - sigmoideBipolar(x)));
    }

}
