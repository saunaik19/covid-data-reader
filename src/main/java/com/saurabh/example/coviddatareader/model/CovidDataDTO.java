package com.saurabh.example.coviddatareader.model;

import java.util.Date;


public class CovidDataDTO {

    private Long fips;
    private String admin2;
    private String provinceState;
    private String countryRegion;
    private Date lastUpdate;
    private Double latitude;
    private Double longitude;
    private Long confirmedCasesCount;
    private Long deathsCasesCount;
    private Long recoveredCasesCount;
    private Long activeCasesCount;
    private String combinedKey;
    private Double incidenceRate;
    private Double caseFatalityRatio;

    public CovidDataDTO() {
    }

    public CovidDataDTO(Long fips, String admin2, String provinceState, String countryRegion, Date lastUpdate, Double latitude, Double longitude, Long confirmedCasesCount, Long deathsCasesCount, Long recoveredCasesCount, Long activeCasesCount, String combinedKey, Double incidenceRate, Double caseFatalityRatio) {
        this.fips = fips;
        this.admin2 = admin2;
        this.provinceState = provinceState;
        this.countryRegion = countryRegion;
        this.lastUpdate = lastUpdate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.confirmedCasesCount = confirmedCasesCount;
        this.deathsCasesCount = deathsCasesCount;
        this.recoveredCasesCount = recoveredCasesCount;
        this.activeCasesCount = activeCasesCount;
        this.combinedKey = combinedKey;
        this.incidenceRate = incidenceRate;
        this.caseFatalityRatio = caseFatalityRatio;
    }

    @Override
    public String toString() {
        return "CovidDataDTO{" +
                "fips=" + fips +
                ", admin2='" + admin2 + '\'' +
                ", provinceState='" + provinceState + '\'' +
                ", countryRegion='" + countryRegion + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", confirmedCasesCount=" + confirmedCasesCount +
                ", deathsCasesCount=" + deathsCasesCount +
                ", recoveredCasesCount=" + recoveredCasesCount +
                ", activeCasesCount=" + activeCasesCount +
                ", combinedKey='" + combinedKey + '\'' +
                ", incidenceRate=" + incidenceRate +
                ", caseFatalityRatio=" + caseFatalityRatio +
                '}';
    }

    public Long getFips() {
        return fips;
    }

    public void setFips(Long fips) {
        this.fips = fips;
    }

    public String getAdmin2() {
        return admin2;
    }

    public void setAdmin2(String admin2) {
        this.admin2 = admin2;
    }

    public String getProvinceState() {
        return provinceState;
    }

    public void setProvinceState(String provinceState) {
        this.provinceState = provinceState;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Long getConfirmedCasesCount() {
        return confirmedCasesCount;
    }

    public void setConfirmedCasesCount(Long confirmedCasesCount) {
        this.confirmedCasesCount = confirmedCasesCount;
    }

    public Long getDeathsCasesCount() {
        return deathsCasesCount;
    }

    public void setDeathsCasesCount(Long deathsCasesCount) {
        this.deathsCasesCount = deathsCasesCount;
    }

    public Long getRecoveredCasesCount() {
        return recoveredCasesCount;
    }

    public void setRecoveredCasesCount(Long recoveredCasesCount) {
        this.recoveredCasesCount = recoveredCasesCount;
    }

    public Long getActiveCasesCount() {
        return activeCasesCount;
    }

    public void setActiveCasesCount(Long activeCasesCount) {
        this.activeCasesCount = activeCasesCount;
    }

    public String getCombinedKey() {
        return combinedKey;
    }

    public void setCombinedKey(String combinedKey) {
        this.combinedKey = combinedKey;
    }

    public Double getIncidenceRate() {
        return incidenceRate;
    }

    public void setIncidenceRate(Double incidenceRate) {
        this.incidenceRate = incidenceRate;
    }

    public Double getCaseFatalityRatio() {
        return caseFatalityRatio;
    }

    public void setCaseFatalityRatio(Double caseFatalityRatio) {
        this.caseFatalityRatio = caseFatalityRatio;
    }
}
