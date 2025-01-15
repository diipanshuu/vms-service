package dev.dipanshu.vms.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Company extends BaseModel{
    // @Column(unique = true, nullable = false)
    private String companyName;

    private String contactNumber;

    private String email;

    @Lob
    private String address;
}
