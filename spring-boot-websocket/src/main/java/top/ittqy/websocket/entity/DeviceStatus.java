package top.ittqy.websocket.entity;

import lombok.Data;

/**
 * @Author: 27258
 * @Date: 2025/10/10
 */
@Data
public class DeviceStatus {
    private double cpuUsage;      // CPU 使用率 %
    private double memoryUsage;   // 内存使用率 %
    private double diskUsage;     // 磁盘使用率 %
    private long timestamp;       // 时间戳

    public DeviceStatus(double cpuUsage, double memoryUsage, double diskUsage) {
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;
        this.diskUsage = diskUsage;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return String.format("""
            🖥️ 设备监控数据
            📈 CPU 使用率: %.1f%%
            💾 内存使用率: %.1f%%
            🗂️ 磁盘使用率: %.1f%%
            ⏰ %tY-%<tm-%<td %<tH:%<tM:%<tS
            """, cpuUsage, memoryUsage, diskUsage, timestamp);
    }
}