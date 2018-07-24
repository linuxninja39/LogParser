package com.ef.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class IpAddressEntity extends BaseTypeEntity {
    @OneToMany(mappedBy = "ipAddress")
    private List<LogEntity> logs = new ArrayList<>();
}
