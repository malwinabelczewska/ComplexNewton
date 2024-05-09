package pl.edu.ug.prog.complexnewton;

import org.apache.commons.numbers.complex.Complex;

import java.util.Optional;

public class Newton {
    private final ComplexFunction function;
    private final double tolerance;
    private final int maxIterations;

    public Newton(ComplexFunction function, double tolerance, int maxIterations) {
        this.function = function;
        this.tolerance = tolerance;
        this.maxIterations = maxIterations;
    }

    public Optional<Complex> calculate(Complex zStart) {
        var z = zStart;
        for (var i = 0; i < maxIterations; i++) {
            var zFunction = function.getFunctionValue(z);
            var zDerivative = function.getDerivativeValue(z);
            var zNext = z.subtract(zFunction.divide(zDerivative));

            if (zNext.isNaN()) {
                break;
            }

            if (ComplexCompare.almostEqual(zNext, z, tolerance)) {
                return Optional.of(zNext);
            }

            z = zNext;
        }
        return Optional.empty();
    }
}
