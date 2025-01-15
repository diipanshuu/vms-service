package dev.dipanshu.vms.repositories;

import dev.dipanshu.vms.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
