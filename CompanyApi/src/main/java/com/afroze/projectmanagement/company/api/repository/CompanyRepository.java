package com.afroze.projectmanagement.company.api.repository;

import com.afroze.projectmanagement.company.api.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByName(String name);
    List<Company> findCompaniesByTagsContainsIgnoreCase(String tag);
}
