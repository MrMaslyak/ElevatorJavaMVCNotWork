import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ElevatorModel model = new ElevatorModel();
            ElevatorUI view = new ElevatorUI();
            ElevatorController controller = new ElevatorController(model, view);
            view.setVisible(true);
        });
    }
}
