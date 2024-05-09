package pl.edu.ug.prog.complexnewton;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.function.Function;

public class MainFrame extends JFrame {

    private final ComplexPlanePanel complexPlanePanel;
    private final JTextField nField;
    private final JTextField toleranceField;
    private final JTextField iterationsField;
    private final Function<Parameters, Collection<BasinCoordinates>> listener;

    public MainFrame(int resolution, Function<Parameters, Collection<BasinCoordinates>> listener) throws HeadlessException {
        super("Complex Roots of z^n-1");
        this.listener = listener;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(resolution, resolution + 70));

        var layout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        setLayout(layout);

        var topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(resolution, 50));

        topPanel.add(new JLabel("n:"));

        nField = new JTextField();
        nField.setPreferredSize(new Dimension(100, 30));
        topPanel.add(nField);

        topPanel.add(new JLabel("tolerance:"));

        toleranceField = new JTextField();
        toleranceField.setPreferredSize(new Dimension(100, 30));
        toleranceField.setText("0.000001");
        topPanel.add(toleranceField);

        topPanel.add(new JLabel("iterations:"));

        iterationsField = new JTextField();
        iterationsField.setPreferredSize(new Dimension(100, 30));
        iterationsField.setText("100");
        topPanel.add(iterationsField);

        var ok = new JButton("OK");
        ok.addActionListener(e -> onOk());
        topPanel.add(ok);

        add(topPanel);

        complexPlanePanel = new ComplexPlanePanel(resolution);
        add(complexPlanePanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void onOk() {
        try {
            int n = Integer.parseInt(nField.getText());
            int iterations = Integer.parseInt(iterationsField.getText());
            double tolerance = Double.parseDouble(toleranceField.getText());

            if (n > 0 && iterations > 0 && tolerance > 0) {
                Collection<BasinCoordinates> coordinates =
                        this.listener.apply(new Parameters(n, tolerance, iterations));

                complexPlanePanel.setData(coordinates);
                complexPlanePanel.repaint();
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid numbers for n, tolerance and iterations.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
