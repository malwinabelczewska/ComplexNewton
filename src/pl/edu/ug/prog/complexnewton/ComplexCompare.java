package pl.edu.ug.prog.complexnewton;

import org.apache.commons.numbers.complex.Complex;

public class ComplexCompare {
    protected ComplexCompare() {
        throw new UnsupportedOperationException();
    }
    public static boolean almostEqual(Complex zFirst, Complex zSecond, double tolerance) {
        return zFirst.subtract(zSecond).norm() < tolerance;
    }
}
