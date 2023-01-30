package com.afroze.projectmanagement.company.api.exception;

public class CompanyNotFoundException extends Throwable{
    public CompanyNotFoundException(long id) {
        super("Company with the id: " + id + " not found");
    }
}
