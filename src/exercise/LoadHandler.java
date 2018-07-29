package exercise;

import java.util.Arrays;

public class LoadHandler {

	protected static final int MAX_PRICE_UPDATES = 2;
	protected final Consumer consumer;

	public LoadHandler (Consumer consumer) {
		this.consumer = consumer;
	}

	public void receive(PriceUpdate priceUpdate) {

		consumer.send (Arrays.asList(priceUpdate));
	}

}
