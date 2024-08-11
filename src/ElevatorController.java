import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ElevatorController {
    private final ElevatorModel model;
    private final ElevatorUI view;

    public ElevatorController(ElevatorModel model, ElevatorUI view) {
        this.model = model;
        this.view = view;
        initializeController();
    }

    private void initializeController() {
        view.getFloorButtons().forEach((floor, button) -> {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    moveToFloor(floor);
                }
            });
        });

        view.getSpeedButtons().forEach((speed, button) -> {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setSpeed(speed);
                }
            });
        });

        view.getServiceToggle().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleServiceMode();
            }
        });
    }

    private void moveToFloor(int floor) {
        if (model.getLiveFloor() != floor) {
            model.setLiveFloor(floor);
            view.setLiveFloorInt(floor);
            view.setPositionY(model.getFloorPositions()[floor - 1]);
        }
    }

    private void setSpeed(int speed) {
        model.setElevatorSpeed(speed * 2000);
    }

    private void toggleServiceMode() {
        boolean isServiceMode = view.getServiceToggle().isSelected();
    }
}
