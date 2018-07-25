package com.ef.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MethodProtocol")
public class MethodProtocolEntity extends BaseTypeEntity {
    @OneToMany(mappedBy = "methodProtocol")
    private List<LogEntity> logs = new ArrayList<>();

    public List<LogEntity> getLogs() {
        return logs;
    }

    public void setLogs(List<LogEntity> logs) {
        this.logs = logs;
    }
}
