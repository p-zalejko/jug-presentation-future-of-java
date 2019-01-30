package pawel.zalejko.sample.containerinfo;


public class ContainerInfo {

    private long memoryAndSwapLimit;
    private long memoryUsage;
    private long effectiveCpuCount;
    private long cpuUsage;
    private String provider;

    public ContainerInfo(long memoryAndSwapLimit, long memoryUsage, long effectiveCpuCount, long cpuUsage, String provider) {
        this.memoryAndSwapLimit = memoryAndSwapLimit;
        this.memoryUsage = memoryUsage;
        this.effectiveCpuCount = effectiveCpuCount;
        this.cpuUsage = cpuUsage;
        this.provider = provider;
    }
}