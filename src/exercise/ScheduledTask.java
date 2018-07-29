package exercise;

public class ScheduledTask implements Runnable {

    private final Consumer consumer;

    public ScheduledTask(final Consumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        System.out.println("### Running scheduler task ####");
        consumer.send(PeriodicLoadHandler.getAllData());
    }
}
