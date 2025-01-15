package dev.dipanshu.vms.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
public class VisitorLog extends BaseModel{
    @ManyToOne
    @JoinColumn(name = "visitor_id", nullable = false)
    private Visitor visitor;

    @Column(nullable = false)
    private Timestamp inTime;

    private Timestamp outTime;

    @Lob
    private String notes;

    private String visitedLocation;
}
