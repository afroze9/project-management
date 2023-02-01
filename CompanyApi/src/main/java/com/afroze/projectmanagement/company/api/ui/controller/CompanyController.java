package com.afroze.projectmanagement.company.api.ui.controller;

import com.afroze.projectmanagement.company.api.dto.CompanyDto;
import com.afroze.projectmanagement.company.api.exception.CompanyAlreadyExistsException;
import com.afroze.projectmanagement.company.api.exception.CompanyNotFoundException;
import com.afroze.projectmanagement.company.api.security.Permissions;
import com.afroze.projectmanagement.company.api.service.CompanyService;
import com.afroze.projectmanagement.company.api.ui.model.CompanyRequestModel;
import com.afroze.projectmanagement.company.api.ui.model.CompanyResponseModel;
import com.afroze.projectmanagement.company.api.ui.model.HttpResponseModel;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;

    private final ModelMapper mapper;

    public CompanyController(CompanyService companyService, ModelMapper mapper) {
        this.companyService = companyService;
        this.mapper = mapper;
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('" + Permissions.READ_COMPANY + "')")
    public ResponseEntity<HttpResponseModel<List<CompanyResponseModel>>> getAll() {
        List<CompanyDto> companies = companyService.getCompanies();
        if(companies.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<CompanyResponseModel> response = mapper.map(companies, new TypeToken<List<CompanyResponseModel>>(){}.getType());
        return ResponseEntity.status(HttpStatus.OK).body(HttpResponseModel.Success(response));
    }

    @GetMapping("/{companyId}/")
    @PreAuthorize("hasAuthority('" + Permissions.READ_COMPANY + "')")
    public ResponseEntity<HttpResponseModel<CompanyResponseModel>> getById(@PathVariable("companyId") int companyId) {
        try {
            CompanyDto company = companyService.getCompanyById(companyId);
            CompanyResponseModel response = mapper.map(company, CompanyResponseModel.class);
            return ResponseEntity.status(HttpStatus.OK).body(HttpResponseModel.Success(response));
        } catch (CompanyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpResponseModel.Failure(null, e.getLocalizedMessage()));
        }
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('" + Permissions.WRITE_COMPANY + "')")
    public ResponseEntity<HttpResponseModel<CompanyResponseModel>> create(@RequestBody @Valid CompanyRequestModel company) {
        CompanyDto dto = mapper.map(company, CompanyDto.class);
        try {
            CompanyDto createdCompany = companyService.createCompany(dto);
            CompanyResponseModel response = mapper.map(createdCompany, CompanyResponseModel.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(HttpResponseModel.Success(response));
        } catch (CompanyAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpResponseModel.Failure(null, e.getLocalizedMessage()));
        }
    }
}
