package com.example.covidvaccinetracker.model;

public class VaccineModel {
    private String vaccineCenter;
    private String vaccinationCharges;
    private String vaccinationAge;
    private String vaccinationTimings;
    private String vaccineName;
    private String vaccineCenterTime;
    private String vaccinationCenterAddress;
    private String vaccinationAvailability;


    public VaccineModel()
    {

    }

    public String getVaccineCenter() {
        return vaccineCenter;
    }

    public void setVaccineCenter(String vaccineCenter) {
        this.vaccineCenter = vaccineCenter;
    }

    public String getVaccinationCharges() {
        return vaccinationCharges;
    }

    public void setVaccinationCharges(String vaccinationCharges) {
        this.vaccinationCharges = vaccinationCharges;
    }

    public String getVaccinationAge() {
        return vaccinationAge;
    }

    public void setVaccinationAge(String vaccinationAge) {
        this.vaccinationAge = vaccinationAge;
    }

    public String getVaccinationTimings() {
        return vaccinationTimings;
    }

    public void setVaccinationTimings(String vaccinationTimings) {
        this.vaccinationTimings = vaccinationTimings;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getVaccineCenterTime() {
        return vaccineCenterTime;
    }

    public void setVaccineCenterTime(String vaccineCenterTime) {
        this.vaccineCenterTime = vaccineCenterTime;
    }

    public String getVaccinationCenterAddress() {
        return vaccinationCenterAddress;
    }

    public void setVaccinationCenterAddress(String vaccinationCenterAddress) {
        this.vaccinationCenterAddress = vaccinationCenterAddress;
    }

    public String getVaccinationAvailability() {
        return vaccinationAvailability;
    }

    public void setVaccinationAvailability(String vaccinationAvailability) {
        this.vaccinationAvailability = vaccinationAvailability;
    }

    public VaccineModel(String vaccineCenter, String vaccinationCharges, String vaccinationAge, String vaccinationTimings, String vaccineName, String vaccineCenterTime, String vaccinationCenterAddress, String vaccinationAvailability) {
        this.vaccineCenter = vaccineCenter;
        this.vaccinationCharges = vaccinationCharges;
        this.vaccinationAge = vaccinationAge;
        this.vaccinationTimings = vaccinationTimings;
        this.vaccineName = vaccineName;
        this.vaccineCenterTime = vaccineCenterTime;
        this.vaccinationCenterAddress = vaccinationCenterAddress;
        this.vaccinationAvailability = vaccinationAvailability;
    }
}
