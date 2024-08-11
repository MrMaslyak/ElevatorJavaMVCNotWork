import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ElevatorUI extends JFrame {
    private JLabel liveFloorLabel;
    private final int[] floorPositions = {400, 475, 550, 625, 700};
    private Map<Integer, JButton> floorButtons;
    private Map<Integer, JButton> floorOnButtons;
    private Map<Integer, JButton> speedButtons;
    private ToggleSwitch serviceToggle;
    private ArrowPanel arrowPanelUp;
    private ArrowPanel arrowPanelDown;
    private PainterFloor painterFloor;
    private int positionY;

    public ElevatorUI() {
        setTitle("Elevator Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);
        setLocationRelativeTo(null);
        setLayout(null);
        initializeComponents();
        setVisible(true);
    }

    private void initializeComponents() {
        JLabel titleLabel = new JLabel("Elevator Simulator");
        titleLabel.setBounds(185, 25, 300, 25);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        add(titleLabel);

        floorButtons = new HashMap<>();
        floorOnButtons = new HashMap<>();
        speedButtons = new HashMap<>();

        initializeFloorButtons();
        initializeLiveFloorPanel();
        initializeServiceToggle();
        initializeSpeedButtons();
        initializeArrowPanels();

        painterFloor = new PainterFloor(this);
        painterFloor.setBounds(0, -25, 600, 800);
        add(painterFloor);
    }

    private void initializeFloorButtons() {
        floorButtons.put(1, createFloorButton(450, 100, "Floor 1"));
        floorButtons.put(2, createFloorButton(450, 140, "Floor 2"));
        floorButtons.put(3, createFloorButton(450, 180, "Floor 3"));
        floorButtons.put(4, createFloorButton(450, 220, "Floor 4"));
        floorButtons.put(5, createFloorButton(450, 260, "Floor 5"));

        floorOnButtons.put(1, createFloorOnButton(500, 685, "<1>"));
        floorOnButtons.put(2, createFloorOnButton(500, 610, "<2>"));
        floorOnButtons.put(3, createFloorOnButton(500, 535, "<3>"));
        floorOnButtons.put(4, createFloorOnButton(500, 460, "<4>"));
        floorOnButtons.put(5, createFloorOnButton(500, 385, "<5>"));
    }

    private void initializeLiveFloorPanel() {
        JPanel liveFloorPanel = new JPanel();
        liveFloorPanel.setBounds(100, 118, 250, 150);
        liveFloorPanel.setBackground(new Color(193, 192, 192));
        add(liveFloorPanel);

        liveFloorLabel = new JLabel("Live Floor: *");
        liveFloorLabel.setFont(new Font("Arial", Font.BOLD, 30));
        liveFloorPanel.add(liveFloorLabel);
    }

    private void initializeServiceToggle() {
        serviceToggle = new ToggleSwitch();
        serviceToggle.setBounds(480, 300, 25, 15);
        add(serviceToggle);

        JLabel serviceLabel = new JLabel("Service");
        serviceLabel.setBounds(468, 323, 60, 15);
        serviceLabel.setFont(new Font("Arial", Font.ITALIC, 15));
        add(serviceLabel);
    }

    private void initializeSpeedButtons() {
        JButton slowButton = new JButton("Slow");
        slowButton.setBounds(480, 340, 80, 30);
        slowButton.addActionListener(e -> setSpeed(2000));
        add(slowButton);

        JButton mediumButton = new JButton("Medium");
        mediumButton.setBounds(480, 380, 80, 30);
        mediumButton.addActionListener(e -> setSpeed(1000));
        add(mediumButton);

        JButton fastButton = new JButton("Fast");
        fastButton.setBounds(480, 420, 80, 30);
        fastButton.addActionListener(e -> setSpeed(500));
        add(fastButton);
    }

    private void setSpeed(int speed) {
        // Реализуйте метод установки скорости
    }

    private void initializeArrowPanels() {
        arrowPanelUp = new ArrowPanel(true);
        arrowPanelUp.setBounds(150, 350, 50, 50);
        add(arrowPanelUp);

        arrowPanelDown = new ArrowPanel(false);
        arrowPanelDown.setBounds(150, 420, 50, 50);
        add(arrowPanelDown);
    }

    private JButton createFloorButton(int x, int y, String text) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 100, 30);
        button.setFocusable(false);
        add(button);
        return button;
    }

    private JButton createFloorOnButton(int x, int y, String text) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 30, 30);
        button.setForeground(Color.BLACK);
        button.setFocusable(false);
        add(button);
        return button;
    }

    public int[] getFloorPositions() {
        return floorPositions;
    }

    public int getLiveFloorInt() {
        return 1;
    }

    public void setLiveFloorInt(int floor) {
    }

    public void updateLiveFloorLabel() {
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
        painterFloor.updatePosition(positionY);
    }

    public JLabel getLiveFloorLabel() {
        return liveFloorLabel;
    }

    public Map<Integer, JButton> getFloorButtons() {
        return floorButtons;
    }

    public Map<Integer, JButton> getSpeedButtons() {
        return speedButtons;
    }

    public ToggleSwitch getServiceToggle() {
        return serviceToggle;
    }

    public ArrowPanel getArrowPanelUp() {
        return arrowPanelUp;
    }

    public ArrowPanel getArrowPanelDown() {
        return arrowPanelDown;
    }

    public PainterFloor getPainterFloor() {
        return painterFloor;
    }

    public int getPositionY() {
        return positionY;
    }

    public static class ToggleSwitch extends JToggleButton {
        public ToggleSwitch() {
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
    }

    public static class ArrowPanel extends JPanel {
        private final boolean isUp;

        public ArrowPanel(boolean isUp) {
            this.isUp = isUp;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setPaint(Color.BLACK);
            int[] xPoints = {10, 30, 50};
            int[] yPoints = isUp ? new int[]{40, 10, 40} : new int[]{10, 40, 10};
            g2d.fillPolygon(xPoints, yPoints, 3);
        }
    }
}
