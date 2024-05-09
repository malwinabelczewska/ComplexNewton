package pl.edu.ug.prog.complexnewton;

import org.apache.commons.numbers.complex.Complex;

public interface ComplexFunction {
    Complex getFunctionValue(Complex z);

    Complex getDerivativeValue(Complex z);
}
