package pawel.zalejko.sample.hello;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

public class HelloHandler {

    public Mono<ServerResponse> get(ServerRequest request) {
        Mono<String> message = Mono.just("Hello!");
        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON)
                .body(message, String.class);
    }
}
