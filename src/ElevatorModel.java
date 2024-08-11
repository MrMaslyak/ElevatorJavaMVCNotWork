import java.util.Map;

public class ElevatorModel {
    private int liveFloor = 1;
    private int elevatorSpeed = 2000;
    private final int[] floorPositions = {400, 475, 550, 625, 700};

    public int getLiveFloor() {
        return liveFloor;
    }

    public void setLiveFloor(int liveFloor) {
        this.liveFloor = liveFloor;
    }

    public int getElevatorSpeed() {
        return elevatorSpeed;
    }

    public void setElevatorSpeed(int elevatorSpeed) {
        this.elevatorSpeed = elevatorSpeed;
    }

    public int[] getFloorPositions() {
        return floorPositions;
    }
}
