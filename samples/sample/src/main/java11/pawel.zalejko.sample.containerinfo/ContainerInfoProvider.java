package pawel.zalejko.sample.containerinfo;

import jdk.internal.platform.Container;
import jdk.internal.platform.Metrics;

import java.util.Optional;

// https://github.com/spring-projects/spring-boot/issues/12523

// add as compiler args and as launch args:
// -add-exports=java.base/jdk.internal.platform=ALL-UNNAMED

public class ContainerInfoProvider {

    public Optional<ContainerInfo> getInfo() {
        Metrics metrics = Container.metrics();
        if (metrics != null) {
            return Optional.of(create(metrics));
        }

        return Optional.empty();
    }

    private ContainerInfo create(Metrics metrics) {
        ContainerInfo info = new ContainerInfo(
                metrics.getMemoryAndSwapLimit(),
                metrics.getMemoryUsage(),
                metrics.getEffectiveCpuCount(),
                metrics.getCpuUsage(),
                metrics.getProvider()
        );
        return info;
    }
}
