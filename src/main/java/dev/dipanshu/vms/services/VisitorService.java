package dev.dipanshu.vms.services;

import dev.dipanshu.vms.dtos.CreateVisitorDto;
import dev.dipanshu.vms.models.Company;
import dev.dipanshu.vms.models.Visitor;
import dev.dipanshu.vms.models.VisitorType;
import dev.dipanshu.vms.repositories.CompanyRepository;
import dev.dipanshu.vms.repositories.VisitorRepository;
import dev.dipanshu.vms.repositories.VisitorTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing visitors.
 */
@Service
public class VisitorService {

    private final VisitorRepository visitorRepository;
    private final CompanyRepository companyRepository;
    private final VisitorTypeRepository visitorTypeRepository;

    @Autowired
    public VisitorService(VisitorRepository visitorRepository, CompanyRepository companyRepository, VisitorTypeRepository visitorTypeRepository) {
        this.visitorRepository = visitorRepository;
        this.companyRepository = companyRepository;
        this.visitorTypeRepository = visitorTypeRepository;
    }

    /**
     * Get all visitors.
     *
     * @return List of all visitors.
     */
    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }

    /**
     * Get a visitor by ID.
     *
     * @param id The ID of the visitor.
     * @return The visitor if found, otherwise an empty Optional.
     */
    public Optional<Visitor> getVisitorById(Long id) {
        return visitorRepository.findById(id);
    }

    /**
     * Save a new visitor.
     *
     * @param createVisitorDto The DTO containing visitor details.
     * @return The saved visitor.
     */
    public Visitor saveVisitor(CreateVisitorDto createVisitorDto) {
        validateVisitor(createVisitorDto);
        Visitor visitor = new Visitor();
        return populateVisitor(createVisitorDto, visitor);
    }

    /**
     * Update an existing visitor by ID.
     *
     * @param createVisitorDto The DTO containing updated visitor details.
     * @param id               The ID of the visitor to update.
     * @return The updated visitor.
     */
    public Visitor saveVisitorById(CreateVisitorDto createVisitorDto, Long id) {
        validateVisitor(createVisitorDto);
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Visitor not found"));
        return populateVisitor(createVisitorDto, visitor);
    }

    /**
     * Delete a visitor by ID.
     *
     * @param id The ID of the visitor to delete.
     */
    public void deleteVisitor(Long id) {
        visitorRepository.deleteById(id);
    }

    /**
     * Validate the visitor details.
     *
     * @param visitor The visitor DTO to validate.
     */
    private void validateVisitor(CreateVisitorDto visitor) {
        if (visitor.getFirstName() == null || visitor.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name is required");
        }
        if (visitor.getLastName() == null || visitor.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last name is required");
        }
        if (visitor.getContactNumber() == null || visitor.getContactNumber().isEmpty()) {
            throw new IllegalArgumentException("Contact number is required");
        }
        if (visitor.getEmail() == null || visitor.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (visitor.getHostEmployeeId() == null) {
            throw new IllegalArgumentException("Host employee id is required");
        }
        if (visitor.getVisitorType() == null) {
            throw new IllegalArgumentException("Visitor type is required");
        }
    }

    /**
     * Populate the visitor entity with details from the DTO.
     *
     * @param createVisitorDto The DTO containing visitor details.
     * @param visitor          The visitor entity to populate.
     * @return The populated visitor entity.
     */
    private Visitor populateVisitor(CreateVisitorDto createVisitorDto, Visitor visitor) {
        visitor.setFirstName(createVisitorDto.getFirstName());
        visitor.setLastName(createVisitorDto.getLastName());
        visitor.setCompanyName(createVisitorDto.getCompanyName());
        visitor.setPurposeOfVisit(createVisitorDto.getPurposeOfVisit());
        visitor.setContactNumber(createVisitorDto.getContactNumber());
        visitor.setEmail(createVisitorDto.getEmail());
        visitor.setInTime(createVisitorDto.getInTime());
        visitor.setOutTime(createVisitorDto.getOutTime());
        visitor.setHostEmployeeId(createVisitorDto.getHostEmployeeId());
        visitor.setPhotoId(createVisitorDto.getPhotoId());
        visitor.setIdType(createVisitorDto.getIdType());
        visitor.setIdNumber(createVisitorDto.getIdNumber());
        visitor.setAddress(createVisitorDto.getAddress());
        visitor.setCity(createVisitorDto.getCity());
        visitor.setState(createVisitorDto.getState());
        visitor.setCountry(createVisitorDto.getCountry());

        Long visitorTypeId = createVisitorDto.getVisitorType() != null ? createVisitorDto.getVisitorType().getId() : null;
        if (visitorTypeId != null) {
            VisitorType visitorType = visitorTypeRepository.findById(visitorTypeId)
                    .orElseThrow(() -> new IllegalArgumentException("Visitor Type not found"));
            visitor.setVisitorType(visitorType);
        } else {
            visitor.setVisitorType(createVisitorDto.getVisitorType());
        }

        Long companyId = createVisitorDto.getCompany() != null ? createVisitorDto.getCompany().getId() : null;
        if (companyId != null) {
            Company company = companyRepository.findById(companyId)
                    .orElseThrow(() -> new IllegalArgumentException("Company not found"));
            visitor.setCompany(company);
        } else if (createVisitorDto.getCompanyName() != null) {
            Company newCompany = new Company();
            newCompany.setCompanyName(createVisitorDto.getCompanyName());
            companyRepository.save(newCompany);
            visitor.setCompany(newCompany);
        } else {
            visitor.setCompany(createVisitorDto.getCompany());
        }

        return visitorRepository.save(visitor);
    }
}