package com.ef.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Log")
public class LogEntity extends BaseEntity {
    @Column(nullable = false)
    private Date timeStamp;
    @Column(nullable = false)
    private Integer statusCode;

    @ManyToOne
    private AgentEntity agent;
    @ManyToOne
    private IpAddressEntity ipAddress;
    @ManyToOne
    private MethodProtocolEntity methodProtocol;

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public AgentEntity getAgent() {
        return agent;
    }

    public void setAgent(AgentEntity agent) {
        this.agent = agent;
    }

    public IpAddressEntity getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(IpAddressEntity ipAddress) {
        this.ipAddress = ipAddress;
    }

    public MethodProtocolEntity getMethodProtocol() {
        return methodProtocol;
    }

    public void setMethodProtocol(MethodProtocolEntity methodProtocol) {
        this.methodProtocol = methodProtocol;
    }
}
