package com.ef.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table
public class LogEntity extends BaseEntity {
    @Column(nullable = false)
    private Date timeStamp;

    @ManyToOne
    private AgentEntity agent;
    @ManyToOne
    private IpAddressEntity ipAddress;
    @ManyToOne
    private MethodProtocolEntity methodProtocol;
}
