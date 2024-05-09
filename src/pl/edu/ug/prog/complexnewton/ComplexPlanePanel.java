package pl.edu.ug.prog.complexnewton;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class ComplexPlanePanel extends JPanel {
    private Collection<BasinCoordinates> basins;

    public ComplexPlanePanel(int resolution) {
        super();
        setPreferredSize(new Dimension(resolution, resolution));
        setBackground(Color.BLACK);
    }

    public void setData(Collection<BasinCoordinates> basins) {
        this.basins = basins;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBuckets((Graphics2D) g);
        drawAxis((Graphics2D) g);
    }

    public void drawBuckets(Graphics2D g) {
        if (basins == null) {
            return;
        }
        int i = 0;
        for (var basin : basins) {
            g.setColor(Color.getHSBColor((float) i / basins.size(), 1, 1));
            for (var coordinates : basin.numbers()) {
                g.drawLine(coordinates.x(), coordinates.y(), coordinates.x(), coordinates.y());
            }
            g.setColor(Color.BLACK);
            g.drawOval(basin.root().x(), basin.root().y(), 10, 10);
            i++;
        }
    }

    private void drawAxis(Graphics2D g) {
        g.setColor(Color.WHITE);

        int xCenter = getWidth() / 2;
        int yCenter = getHeight() / 2;

        // Real Axis
        g.drawLine(0, yCenter, getWidth(), yCenter);

        // Imaginary axis
        g.drawLine(xCenter, 0, xCenter, getHeight());
    }
}
