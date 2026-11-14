
package com.watersupply.api.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "water_connection")
public class WaterConnection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "connection_id")
    private Integer connectionId;

    @Column(name = "consumer_id")
    private Integer consumerId;

    @Column(name = "connection_type", length = 50)
    private String connectionType;

    @Column(name = "connection_date")
    private LocalDate connectionDate;

    @Column(name = "status", length = 20)
    private String status;

    public WaterConnection() {
    }

    public Integer getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(Integer connectionId) {
        this.connectionId = connectionId;
    }

    public Integer getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public LocalDate getConnectionDate() {
        return connectionDate;
    }

    public void setConnectionDate(LocalDate connectionDate) {
        this.connectionDate = connectionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
