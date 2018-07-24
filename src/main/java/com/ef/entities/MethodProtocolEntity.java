package com.ef.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class MethodProtocolEntity extends BaseTypeEntity {
    @OneToMany(mappedBy = "methodProtocol")
    private List<LogEntity> logs = new ArrayList<>();

}
