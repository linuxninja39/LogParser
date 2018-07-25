package com.ef.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "IpAddress")
public class IpAddressEntity extends BaseTypeEntity {
    @OneToMany(mappedBy = "ipAddress")
    private List<LogEntity> logs = new ArrayList<>();
    @OneToMany(mappedBy = "ipAddress")
    private List<BlockLogEntity> blockLogs = new ArrayList<>();

    public List<LogEntity> getLogs() {
        return logs;
    }

    public void setLogs(List<LogEntity> logs) {
        this.logs = logs;
    }

    public List<BlockLogEntity> getBlockLogs() {
        return blockLogs;
    }

    public void setBlockLogs(List<BlockLogEntity> blockLogs) {
        this.blockLogs = blockLogs;
    }
}
