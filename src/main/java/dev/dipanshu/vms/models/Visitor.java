package dev.dipanshu.vms.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
public class Visitor extends BaseModel{
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String companyName;

    private String purposeOfVisit;

    // @Column(unique = true)
    private String contactNumber;

    // @Column(unique = true)
    private String email;

    private Timestamp inTime;

    private Timestamp outTime;

    @Column(nullable = false)
    private Integer hostEmployeeId;

    private String photoId;

    @Enumerated(EnumType.STRING)
    private IdType idType;

    private String idNumber;

    @Lob
    private String address;

    private String city;

    private String state;

    private String country;

    @ManyToOne
    @JoinColumn(name = "visitor_type_id", nullable = false)
    private VisitorType visitorType;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public enum IdType {
        DRIVERS_LICENSE,
        PASSPORT,
        COMPANY_ID
    }
}
