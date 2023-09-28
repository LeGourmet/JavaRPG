//DELON ARTHUR
package Frontend;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public class Waiter extends Task<Void> {
    private Thread thread;
    private Object toWaitfor;

    protected Waiter(Object toWaitfor) {
        super();
        this.toWaitfor = toWaitfor;
    }

    @Override
    protected Void call() {
        synchronized (toWaitfor) {
            try {
                toWaitfor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected void whenDone(EventHandler<WorkerStateEvent> event) {
        this.setOnSucceeded(event);
    }

    protected void start() {
        thread = new Thread(this);
        thread.start();
    }
};