package pl.edu.ug.prog.complexnewton;

import org.apache.commons.numbers.complex.Complex;

public class UnityPolynomial implements ComplexFunction {
    private final int degree;

    public UnityPolynomial(int degree) {
        this.degree = degree;
    }

    @Override
    public Complex getFunctionValue(Complex z) {
        return z.pow(degree).subtract(1);
    }

    @Override
    public Complex getDerivativeValue(Complex z) {
        return z.pow(degree - 1).multiply(degree);
    }
}
