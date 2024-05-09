package pl.edu.ug.prog.complexnewton;

import java.awt.*;
import java.util.Collection;

public class Main {

    public static final int RESOLUTION = 800;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new MainFrame(RESOLUTION, Main::processPlane));
    }

    private static Collection<BasinCoordinates> processPlane(Parameters params) {
        var plane = new ComplexPlane(-2, 2, RESOLUTION, params.tolerance());

        var newton = new Newton(new UnityPolynomial(params.n()), params.tolerance(), params.iterations());

        plane.clearBasins();

        for (var z : plane) {
            newton.calculate(z).ifPresent(root -> plane.placeInBasin(z, root));
        }

        return plane.getBasinsCoordinates();
    }
}
