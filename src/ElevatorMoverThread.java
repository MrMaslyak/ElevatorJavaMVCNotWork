import javax.swing.*;
import java.awt.*;
import java.util.Map;
import sounds.ElevatorSound;
import Interface.ThreadElevator;

public class ElevatorMoverThread extends Thread implements ThreadElevator {
    private final ElevatorUI elevatorUI;
    private final int targetFloor;
    private final int speedForElevator;
    private final int[] floorPositions;
    private final Map<Integer, JButton> floorButtons;
    private final Map<Integer, JButton> floorOnButtons;
    private final ElevatorUI.ToggleSwitch isServiceToggle;
    private final ElevatorUI.ArrowPanel arrowPanelUp;
    private final ElevatorUI.ArrowPanel arrowPanelDown;
    private final ElevatorSound elevatorSound;

    public ElevatorMoverThread(ElevatorUI elevatorUI, ElevatorUI.ArrowPanel arrowPanelUp, ElevatorUI.ArrowPanel arrowPanelDown,
                               int targetFloor, int speedForElevator, int[] floorPositions,
                               Map<Integer, JButton> floorButtons, Map<Integer, JButton> floorOnButtons,
                               ElevatorUI.ToggleSwitch isServiceToggle) {
        this.elevatorUI = elevatorUI;
        this.targetFloor = targetFloor;
        this.speedForElevator = speedForElevator;
        this.floorPositions = floorPositions;
        this.floorButtons = floorButtons;
        this.floorOnButtons = floorOnButtons;
        this.isServiceToggle = isServiceToggle;
        this.arrowPanelUp = arrowPanelUp;
        this.arrowPanelDown = arrowPanelDown;
        this.elevatorSound = new ElevatorSound();
    }

    @Override
    public void run() {
        while (elevatorUI.getLiveFloorInt() != targetFloor && !isServiceToggle.isSelected()) {
            moveElevator();
        }

        if (isServiceToggle.isSelected()) {
            moveToFirstFloor();
        }
        resetArrowPanels();
    }

    private void moveElevator() {
        int currentFloor = elevatorUI.getLiveFloorInt();
        int floorDifference = targetFloor - currentFloor;

        if (floorDifference != 0) {
            int step = (int) Math.signum(floorDifference);
            int newFloor = currentFloor + step;
            int startY = floorPositions[5 - currentFloor];
            int endY = floorPositions[5 - newFloor];
            int steps = Math.abs(floorDifference);
            int delay = speedForElevator / steps;

            for (int i = 0; i < steps; i++) {
                int intermediateY = startY + i * (endY - startY) / steps;
                elevatorUI.setLiveFloorInt(newFloor);
                elevatorUI.setPositionY(intermediateY);
                updateFloorButtons();
                elevatorUI.updateLiveFloorLabel();
                elevatorUI.repaint();

                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            elevatorUI.setLiveFloorInt(targetFloor);
            elevatorUI.setPositionY(floorPositions[5 - targetFloor]);
            updateFloorButtons();
            elevatorUI.updateLiveFloorLabel();
            elevatorUI.repaint();
        }
    }

    public void moveToFirstFloor() {
        int currentFloor = elevatorUI.getLiveFloorInt();
        while (currentFloor > 1) {
            int step = -1;
            int newFloor = currentFloor + step;
            int startY = floorPositions[5 - currentFloor];
            int endY = floorPositions[5 - newFloor];
            int steps = currentFloor - 1;
            int delay = speedForElevator / steps;

            for (int i = 0; i < steps; i++) {
                int intermediateY = startY + i * (endY - startY) / steps;
                elevatorUI.setLiveFloorInt(newFloor);
                elevatorUI.setPositionY(intermediateY);
                updateFloorButtons();
                elevatorUI.updateLiveFloorLabel();
                elevatorUI.repaint();

                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            elevatorUI.setLiveFloorInt(1);
            elevatorUI.setPositionY(floorPositions[4]);
            updateFloorButtons();
            elevatorUI.updateLiveFloorLabel();
            elevatorUI.repaint();
        }
        elevatorSound.playSound("/sounds/floor1.wav");
    }

    public void updateFloorButtons() {
        for (int i = 1; i <= 5; i++) {
            if (elevatorUI.getLiveFloorInt() == i) {
                floorButtons.get(i).setBorder(null);
                floorOnButtons.get(i).setForeground(Color.BLUE);
                elevatorSound.playSound("/sounds/floor" + i + ".wav");
            } else {
                floorButtons.get(i).setBorder(BorderFactory.createLineBorder(Color.GRAY));
                floorOnButtons.get(i).setForeground(Color.BLACK);
            }
        }
    }

    private void resetArrowPanels() {
        arrowPanelUp.setBackground(new Color(237, 235, 235));
        arrowPanelDown.setBackground(new Color(237, 235, 235));
    }
}
