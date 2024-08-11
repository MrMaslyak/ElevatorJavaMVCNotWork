import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ActionListenerButtons implements ActionListener {
    private final ElevatorController controller;
    private final Map<Integer, JButton> floorButtons;
    private final Map<Integer, JButton> floorOnButtons;
    private final Map<Integer, JButton> speedButtons;
    private final ElevatorUI.ToggleSwitch serviceToggle;
    private int speedForElevator;
    private final ElevatorUI elevatorUI;
    private ElevatorUI.ArrowPanel arrowPanelUp;
    private ElevatorUI.ArrowPanel arrowPanelDown;

    public ActionListenerButtons(ElevatorController controller, ElevatorUI elevatorUI,
                                 Map<Integer, JButton> floorButtons, Map<Integer, JButton> floorOnButtons,
                                 Map<Integer, JButton> speedButtons, ElevatorUI.ToggleSwitch serviceToggle) {
        this.controller = controller;
        this.elevatorUI = elevatorUI;
        this.floorButtons = floorButtons;
        this.floorOnButtons = floorOnButtons;
        this.speedButtons = speedButtons;
        this.serviceToggle = serviceToggle;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int targetFloor = -1;
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Floor 1":
            case "<1>":
                targetFloor = 1;
                break;
            case "Floor 2":
            case "<2>":
                targetFloor = 2;
                break;
            case "Floor 3":
            case "<3>":
                targetFloor = 3;
                break;
            case "Floor 4":
            case "<4>":
                targetFloor = 4;
                break;
            case "Floor 5":
            case "<5>":
                targetFloor = 5;
                break;
            case "Speed low":
                speedForElevator = 3000;
                break;
            case "Speed Middle":
                speedForElevator = 2000;
                break;
            case "Speed Fast":
                speedForElevator = 1000;
                break;
        }

        if (targetFloor != -1) {
            if (arrowPanelUp == null || arrowPanelDown == null) {
                arrowPanelUp = elevatorUI.getArrowPanelUp();
                arrowPanelDown = elevatorUI.getArrowPanelDown();
            }
            floorButtons.get(targetFloor).setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
            floorOnButtons.get(targetFloor).setForeground(Color.GREEN);
            ElevatorMoverThread moverThread = new ElevatorMoverThread(elevatorUI,
                    arrowPanelUp, arrowPanelDown,
                    targetFloor, speedForElevator, elevatorUI.getFloorPositions(),
                    floorButtons, floorOnButtons, serviceToggle);
            moverThread.start();
        }
    }
}
