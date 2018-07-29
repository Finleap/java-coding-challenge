package exercise;

import com.google.common.collect.EvictingQueue;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PeriodicLoadHandler extends LoadHandler {

    public PeriodicLoadHandler(Consumer consumer) {
        super(consumer);
        consume();

    }

    private final ScheduledExecutorService executorService = Executors
            .newScheduledThreadPool(1);

    private final static Map<String,Queue<PriceUpdate>> queueMap = new ConcurrentHashMap<>();

    public void receive(PriceUpdate priceUpdate) {
        if(!queueMap.containsKey(priceUpdate.getCompanyName())) {
            queueMap.putIfAbsent(priceUpdate.getCompanyName(), EvictingQueue.create(MAX_PRICE_UPDATES));
        }
        queueMap.get(priceUpdate.getCompanyName()).add(priceUpdate);
    }

    public static List<PriceUpdate> getAllData() {
        List<PriceUpdate> result = new ArrayList<>();
        queueMap.keySet().forEach(key -> {
            Queue<PriceUpdate> queue = queueMap.get(key);
            while(!queue.isEmpty()) {
                result.add(queue.poll());
            }
        });
        return result;
    }

    private void consume() {
        ScheduledTask scheduledTask = new ScheduledTask(consumer);
        executorService.scheduleAtFixedRate(scheduledTask, 0, 1, TimeUnit.SECONDS);
    }

}