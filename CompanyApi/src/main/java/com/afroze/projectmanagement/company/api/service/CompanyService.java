package com.afroze.projectmanagement.company.api.service;

import com.afroze.projectmanagement.company.api.dto.CompanyDto;
import com.afroze.projectmanagement.company.api.exception.CompanyAlreadyExistsException;
import com.afroze.projectmanagement.company.api.exception.CompanyNotFoundException;

import java.util.List;

public interface CompanyService {
    List<CompanyDto> getCompanies();
    List<CompanyDto> getCompaniesWithTag(String tag);
    CompanyDto getCompanyById(long companyId) throws CompanyNotFoundException;
    CompanyDto createCompany(CompanyDto company) throws CompanyAlreadyExistsException;
}
