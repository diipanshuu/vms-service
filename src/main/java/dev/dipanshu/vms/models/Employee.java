package dev.dipanshu.vms.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Employee extends BaseModel{

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String department;

    @Column(unique = true)
    private String contactNumber;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String photoId;

    public enum Role {
        MANAGER,
        RECEPTIONIST,
        SECURITY
    }
}
