package com.ef.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Agent")
public class AgentEntity extends BaseTypeEntity {
    @OneToMany(mappedBy = "agent")
    private List<LogEntity> logs = new ArrayList<>();

    public List<LogEntity> getLogs() {
        return logs;
    }

    public void setLogs(List<LogEntity> logs) {
        this.logs = logs;
    }
}
