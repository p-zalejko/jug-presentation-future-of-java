package pawel.zalejko.sample.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.stream.IntStream;

public class RandomValueGenerator {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(RandomValueGenerator.class);

    private static final Random RANDOM = new Random();
    private static final int LOW = 0;
    private static final int HIGH = 9_000;

    public IntStream getRandomValues(int size) {
        return RANDOM.ints(size, LOW, HIGH)
                .peek(i -> LOGGER.info("Generated: " + i));
    }
}
