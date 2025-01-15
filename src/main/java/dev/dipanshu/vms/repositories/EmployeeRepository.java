package dev.dipanshu.vms.repositories;

import dev.dipanshu.vms.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}