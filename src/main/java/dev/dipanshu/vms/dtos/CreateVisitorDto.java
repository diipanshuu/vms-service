package dev.dipanshu.vms.dtos;

import dev.dipanshu.vms.models.Company;
import dev.dipanshu.vms.models.Visitor;
import dev.dipanshu.vms.models.VisitorType;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CreateVisitorDto {
    private String firstName;
    private String lastName;
    private String companyName;
    private String purposeOfVisit;
    private String contactNumber;
    private String email;
    private Timestamp inTime;
    private Timestamp outTime;
    private Integer hostEmployeeId;
    private String photoId;
    private Visitor.IdType idType;
    private String idNumber;
    private String address;
    private String city;
    private String state;
    private String country;
    private VisitorType visitorType;
    private Company company;
}
