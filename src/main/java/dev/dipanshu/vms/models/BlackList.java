package dev.dipanshu.vms.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
public class BlackList extends BaseModel{

    @ManyToOne
    @JoinColumn(name = "visitor_id", nullable = false)
    private Visitor visitor;

    @Lob
    @Column(nullable = false)
    private String reason;

    @ManyToOne
    @JoinColumn(name = "blacklisted_by", nullable = false)
    private Employee blacklistedBy;

    @Column(nullable = false)
    private Timestamp blacklistDate;
}
