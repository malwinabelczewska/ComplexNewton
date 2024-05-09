package pl.edu.ug.prog.complexnewton;

import java.util.Collection;

public record BasinCoordinates(Coordinates root, Collection<Coordinates> numbers) {
}
