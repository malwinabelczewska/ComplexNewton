package pl.edu.ug.prog.complexnewton;

import org.apache.commons.numbers.complex.Complex;

import java.util.*;

public class ComplexPlane implements Iterable<Complex> {
    private record BasinOfAttraction(Complex root, List<Complex> numbers) {
    }

    private final int resolution;
    private final double tolerance;
    private final double planeRange;
    private final List<Complex> numbers = new ArrayList<>();
    private final List<BasinOfAttraction> basins = new ArrayList<>();

    public ComplexPlane(double min, double max, int resolution, double tolerance) {
        this.resolution = resolution;
        this.tolerance = tolerance;
        this.planeRange = max - min;
        double step = this.planeRange / (this.resolution - 1);

        for (double r = min; r <= max; r += step) {
            for (double i = min; i <= max; i += step) {
                numbers.add(Complex.ofCartesian(r, i));
            }
        }
    }

    @Override
    public Iterator<Complex> iterator() {
        return Collections.unmodifiableCollection(numbers).iterator();
    }

    public Collection<BasinCoordinates> getBasinsCoordinates() {
        return basins.stream().map(basin ->
                        new BasinCoordinates(
                                toCoordinates(basin.root()),
                                basin.numbers().stream().map(this::toCoordinates).toList()))
                .toList();
    }

    public void placeInBasin(Complex z, Complex newRoot) {
        for (var basin : basins) {
            Complex knownRoot = basin.root();
            if (ComplexCompare.almostEqual(newRoot, knownRoot, tolerance)) {
                basin.numbers.add(z);
                return;
            }
        }
        basins.add(new BasinOfAttraction(newRoot, new ArrayList<>()));
    }

    public void clearBasins() {
        basins.clear();
    }

    private Coordinates toCoordinates(Complex z) {
        double x = z.getReal() * ((resolution - 1)  / planeRange) + resolution / 2.0;
        double y = z.getImaginary() * ((resolution - 1) / planeRange) * -1 + resolution / 2.0;
        return new Coordinates((int) Math.round(x), (int) Math.round(y));
    }
}
