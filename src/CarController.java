import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
 * This class represents the Controller part in the MVC pattern.
 * Its responsibilities are to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // The delay (ms) corresponds to 20 updates/second (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // TODO A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    public void startTimer() {
        this.timer.start();
    }

    /* Each step, the TimerListener moves all cars in the list and tells the
     * view to update its images. TODO Change this method to your needs.
     */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                car.move();
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());

                int panelWidth = frame.drawPanel.getWidth();
                int panelHeight = frame.drawPanel.getHeight();

                int carWidth;
                int carHeight;

                if (car instanceof Volvo240) {
                    carWidth = frame.drawPanel.volvoImage.getWidth();
                    carHeight = frame.drawPanel.volvoImage.getHeight();
                }

                else if (car instanceof Saab95) {
                        carWidth = frame.drawPanel.saabImage.getWidth();
                        carHeight = frame.drawPanel.saabImage.getHeight();
                    }
                else {
                    continue;
                }

            if (x < 0 || x > panelWidth - carWidth
                    || y < 0 || y > panelHeight - carHeight) {
                car.stopEngine();
                x = Math.max(0, Math.min(x, panelWidth - carWidth));
                y = Math.max(0, Math.min(y, panelHeight - carHeight));
                car.setPosition(x, y);
                car.turnRight();
                car.turnRight();
                car.startEngine();
            }

                    frame.drawPanel.moveit(x, y, car.getModelName());
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.revalidate();
                frame.drawPanel.repaint();
            }
        }
    }

    void gas(int amount) {
        // TODO adapt to your needs
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }

    }

    void brake(int amount) {
        double brakeAmount = ((double) amount) / 100;

        for (Car car : cars) {
            car.brake(brakeAmount);
        }
    }

    void turnLeft() {
        for (Car car : cars) {
            car.turnLeft();
        }
    }

    void turnRight(){
        for (Car car : cars){
            car.turnRight();
        }
    }

    void startAllCars() {
        for (Car car : cars) {
            car.startEngine();
        }
    }

    void stopAllCars(){
        for(Car car: cars) {
            car.stopEngine();
        }
    }

    void turboOn() {
        for (Car car : cars) {
            if (car instanceof Saab95) {
                Saab95 saab = (Saab95) car;
                saab.setTurboOn();
            }
        }
    }

    void turboOff(){
        for (Car car : cars){
        if (car instanceof Saab95){
            Saab95 saab = (Saab95) car;
            saab.setTurboOff();
            }
        }
    }
}
