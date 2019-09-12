package backpropagation;

public class backpropagation {

    private double v[][] = new double[7][9];
    private double w[][] = new double[7][9];

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

}
