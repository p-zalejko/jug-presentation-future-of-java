package pawel.zalejko.sample;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.reactive.context.ReactiveWebServerApplicationContext;
import org.springframework.boot.web.reactive.server.ReactiveWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import pawel.zalejko.sample.hello.HelloHandler;
import pawel.zalejko.sample.sample.RandomValueGenerator;
import pawel.zalejko.sample.sample.SampleHandler;

import java.util.ArrayList;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DemoApplication.class) {
            @Override
            protected void load(ApplicationContext context, Object[] sources) {
                // Disable the annotation bean definition reader because
                // of https://github.com/oracle/graal/issues/630
            }
        };

        application.setWebApplicationType(WebApplicationType.REACTIVE);
        application.setApplicationContextClass(
                ReactiveWebServerApplicationContext.class);

        // Only functional registration is supported by now because of
        // https://github.com/oracle/graal/issues/630
        application.addInitializers((GenericApplicationContext context) -> {
            context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
            context.registerBean(HttpHandler.class, () -> createHttpHandler());
            context.registerBean(
                    ReactiveWebServerFactory.class,
                    () -> new NettyReactiveWebServerFactory());
        });

        application.run(args);
    }

    private static HttpHandler createHttpHandler() {
        RouterFunction<ServerResponse> routerFunction = createSamplesController();
        HandlerStrategies strategies = HandlerStrategies.withDefaults();
        return RouterFunctions.toHttpHandler(routerFunction, strategies);
    }

    private static RouterFunction<ServerResponse> createSamplesController() {
        HelloHandler helloHandler = new HelloHandler();
        SampleHandler sampleHandler = new SampleHandler(new RandomValueGenerator());

        return RouterFunctions.route(GET("/")
                .and(accept(APPLICATION_JSON)), helloHandler::get)
                .andRoute(GET("/samples")
                        .and(accept(APPLICATION_JSON)), sampleHandler::getSamples);
    }
}
