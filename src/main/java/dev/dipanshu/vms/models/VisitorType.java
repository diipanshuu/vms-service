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
public class VisitorType extends BaseModel{
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeName typeName;

    public enum TypeName {
        CLIENT,
        VENDOR,
        CONTRACTOR,
        JOB_APPLICANT
    }
}
