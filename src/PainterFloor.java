import javax.swing.*;
import java.awt.*;

public class PainterFloor extends JPanel {
    private final ElevatorUI elevatorUI;
    private int positionY;

    public PainterFloor(ElevatorUI elevatorUI) {
        this.elevatorUI = elevatorUI;
        setOpaque(false);
    }

    public void updatePosition(int positionY) {
        this.positionY = positionY;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.GRAY);
        for (int pos : elevatorUI.getFloorPositions()) {
            g2d.drawLine(100, pos, 600, pos);
        }
        g2d.drawLine(100, 775, 600, 775);

        g2d.setPaint(Color.DARK_GRAY);
        g2d.fillRect(100, positionY, 100, 75);
    }
}
