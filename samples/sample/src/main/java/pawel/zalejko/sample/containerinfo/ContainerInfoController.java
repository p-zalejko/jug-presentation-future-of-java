package pawel.zalejko.sample.containerinfo;

import reactor.core.publisher.Mono;

import java.util.Optional;

public class ContainerInfoController {

    private final ContainerInfoProvider containerInfoProvider;

    public ContainerInfoController(ContainerInfoProvider containerInfoProvider) {
        this.containerInfoProvider = containerInfoProvider;
    }

    public Mono<ContainerInfo> getContainerInfo() {
        Optional<ContainerInfo> info = containerInfoProvider.getInfo();
        return info
                .map(Mono::just)
                .orElse(Mono.empty());
    }
}
