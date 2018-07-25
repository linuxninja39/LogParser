package com.ef.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "BlockLog")
public class BlockLogEntity extends BaseEntity {

    @Column
    private int count;
    @Column
    private String reason;
    @Column
    private Date timeStamp;

    @ManyToOne
    private IpAddressEntity ipAddress;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public IpAddressEntity getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(IpAddressEntity ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
