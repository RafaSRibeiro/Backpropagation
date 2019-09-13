package backpropagation;

public class backpropagation {

    private int x[] = new int[63];
    private double z[] = new double[63];
    private double z_in[] = new double[63];
    private double y_in[] = new double[7];
    private double y[] = new double[7];
    private double t[] = new double[7];
    private double littleDelta[] = new double[63];
    private double littleDelta_in[] = new double[63];
    private double learningRate = 0.2;


    private double v[][] = new double[63][63];
    private double w[][] = new double[63][7];
    private double deltaW[][] = new double[63][7];
    private double deltaV[][] = new double[63][63];


    private void initializeWeights() {
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; i++) {
                v[i][j] = Math.random();
            }
        }
    }

    public static double sigmoideBinaria(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public static double sigmoideBipolar(double x) {
        return (2 / (1 + Math.exp(-x))) * -1;
    }

    public void step4() {
        for (int j = 0; j < z.length; j++) {
            z_in[j] = 0;
            for (int i = 0; i < x.length; i++) {
                z_in[j] += x[i] * v[i][j];
            }
        }

        for (int j = 0; j < z.length; j++) {
            z[j] = sigmoideBinaria(z_in[j]);
        }
    }

    public void step5() {
        for (int k = 0; k < y.length; k++) {
            y_in[k] = 0;
            for (int j = 0; j < z.length; j++) {
                y_in[k] += x[j] * v[j][k];
            }
        }

        for (int k = 0; k < y.length; k++) {
            y[k] = sigmoideBinaria(y_in[k]);
        }
    }

    public void step6() {
        for (int k = 0; k < y.length; k++) {
            littleDelta[k] = (t[k] - y[k]) * y_in[k] * (1 - y_in[k]);
        }

        for (int k = 0; k < y.length; k++) {
            for (int j = 0; j < z.length; j++) {
                deltaW[j][k] = learningRate * littleDelta[k] * z[j];
            }
        }
    }


    public void step7() {
        for (int j = 0; j < z.length; j++) {
            littleDelta_in[j] = 0;
            for (int k = 0; k < y.length; k++) {
                littleDelta_in[k] += littleDelta[k] * w[j][k];
            }
        }

        for (int j = 0; j < z.length; j++) {
            littleDelta[j] = littleDelta_in[j] * z_in[j] * (1- z_in[j]);
        }

        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < z.length; j++) {
                deltaV[i][j] = learningRate * littleDelta[j] * x[i];
            }
        }
    }

    public void step8() {
        for (int k = 0; k < y.length; k++) {
            for (int j = 0; j < z.length; j++) {
                w[j][k] = w[j][k] + deltaW[j][k];
            }
        }

        for (int i = 0; i < x.length; k++) {
            for (int j = 0; j < z.length; j++) {
                v[i][j] = v[i][j] + deltaV[i][j];
            }
        }
    }

}
