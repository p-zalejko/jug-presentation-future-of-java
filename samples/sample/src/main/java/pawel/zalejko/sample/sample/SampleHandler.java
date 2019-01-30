package pawel.zalejko.sample.sample;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

import java.util.function.Consumer;

import static org.springframework.http.MediaType.APPLICATION_JSON;

public class SampleHandler {

    private static final int SIZE = 5_000;

    private final RandomValueGenerator randomValueGenerator;

    public SampleHandler(RandomValueGenerator randomValueGenerator) {
        this.randomValueGenerator = randomValueGenerator;
    }

    public Mono<ServerResponse> getSamples(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON)
                .body(generateSamples(), Object[].class);
    }

    private Mono<Object[]> generateSamples() {
        // return Mono.create(callback -> callback.success(getValues()));

        return Mono.create(new Consumer<MonoSink<Object[]>>() {
            @Override
            public void accept(MonoSink<Object[]> callback) {
                Object[] values = getValues();
                callback.success(values);
            }
        });
    }

    private Object[] getValues() {
        return randomValueGenerator.getRandomValues(SIZE)
                .boxed()
                .toArray();
    }

//    FIXME: it does not work on graalvm RC-5
//    private Flux<Integer> generateSamples() {
//        Stream<Integer> stream = randomValueGenerator
//                                 .getRandomValues(SIZE).boxed();
//        return Flux.fromStream(stream);
//    }

}
