package com.zc.model;

public class EmpInfoPo {

    private String personId;
    private String businessGroupId;
    private String staffCode;
    private String orgCode;
    private String employeeNumber;
    private String nationalIdentifier;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getBusinessGroupId() {
        return businessGroupId;
    }

    public void setBusinessGroupId(String businessGroupId) {
        this.businessGroupId = businessGroupId;
    }

    public String getNationalIdentifier() {
        return nationalIdentifier;
    }

    public void setNationalIdentifier(String nationalIdentifier) {
        this.nationalIdentifier = nationalIdentifier;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    @Override
    public String toString() {
        return "EmpInfoPo{" +
                "personId='" + personId + '\'' +
                ", businessGroupId='" + businessGroupId + '\'' +
                ", staffCode='" + staffCode + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", employeeNumber='" + employeeNumber + '\'' +
                ", nationalIdentifier='" + nationalIdentifier + '\'' +
                '}';
    }
}
