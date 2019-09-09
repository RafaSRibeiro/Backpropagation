package backpropagation;

public class backpropagation {

    public static double sigmoideBinaria(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public static double sigmoideBipolar(double x) {
        return (2 / (1 + Math.exp(-x)))*-1;
    }
}
