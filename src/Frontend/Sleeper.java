//DELON ARTHUR
package Frontend;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public class Sleeper extends Task<Void> {
    private Thread thread;
    private int time;

    public Sleeper(int timeout) {
        super();
        time = timeout;
    }

    @Override
    protected Void call() {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void whenDone(EventHandler<WorkerStateEvent> event) {
        this.setOnSucceeded(event);
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }
};
