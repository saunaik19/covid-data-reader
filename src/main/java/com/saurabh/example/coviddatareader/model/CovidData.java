package com.saurabh.example.coviddatareader.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CovidData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String FIPS;
    private String Admin2;
    private String Province_State;
    private String Country_Region;
    private String Last_Update;
    private String Lat;
    private String Long_;
    private String Confirmed;
    private String Deaths;
    private String Recovered;
    private String Active;
    private String Combined_Key;
    private String Incidence_Rate;
    private String Case_Fatality_Ratio;

    public CovidData() {
    }

    public CovidData(int id, String FIPS, String admin2, String province_State, String country_Region, String last_Update, String lat, String long_, String confirmed, String deaths, String recovered, String active, String combined_Key, String incidence_Rate, String case_Fatality_Ratio) {
        this.id = id;
        this.FIPS = FIPS;
        Admin2 = admin2;
        Province_State = province_State;
        Country_Region = country_Region;
        Last_Update = last_Update;
        Lat = lat;
        Long_ = long_;
        Confirmed = confirmed;
        Deaths = deaths;
        Recovered = recovered;
        Active = active;
        Combined_Key = combined_Key;
        Incidence_Rate = incidence_Rate;
        Case_Fatality_Ratio = case_Fatality_Ratio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFIPS() {
        return FIPS;
    }

    public void setFIPS(String FIPS) {
        this.FIPS = FIPS;
    }

    public String getAdmin2() {
        return Admin2;
    }

    public void setAdmin2(String admin2) {
        Admin2 = admin2;
    }

    public String getProvince_State() {
        return Province_State;
    }

    public void setProvince_State(String province_State) {
        Province_State = province_State;
    }

    public String getCountry_Region() {
        return Country_Region;
    }

    public void setCountry_Region(String country_Region) {
        Country_Region = country_Region;
    }

    public String getLast_Update() {
        return Last_Update;
    }

    public void setLast_Update(String last_Update) {
        Last_Update = last_Update;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLong_() {
        return Long_;
    }

    public void setLong_(String long_) {
        Long_ = long_;
    }

    public String getConfirmed() {
        return Confirmed;
    }

    public void setConfirmed(String confirmed) {
        Confirmed = confirmed;
    }

    public String getDeaths() {
        return Deaths;
    }

    public void setDeaths(String deaths) {
        Deaths = deaths;
    }

    public String getRecovered() {
        return Recovered;
    }

    public void setRecovered(String recovered) {
        Recovered = recovered;
    }

    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        Active = active;
    }

    public String getCombined_Key() {
        return Combined_Key;
    }

    public void setCombined_Key(String combined_Key) {
        Combined_Key = combined_Key;
    }

    public String getIncidence_Rate() {
        return Incidence_Rate;
    }

    public void setIncidence_Rate(String incidence_Rate) {
        Incidence_Rate = incidence_Rate;
    }

    public String getCase_Fatality_Ratio() {
        return Case_Fatality_Ratio;
    }

    public void setCase_Fatality_Ratio(String case_Fatality_Ratio) {
        Case_Fatality_Ratio = case_Fatality_Ratio;
    }

    @Override
    public String toString() {
        return "CovidData{" +
                "id=" + id +
                ", FIPS='" + FIPS + '\'' +
                ", Admin2='" + Admin2 + '\'' +
                ", Province_State='" + Province_State + '\'' +
                ", Country_Region='" + Country_Region + '\'' +
                ", Last_Update='" + Last_Update + '\'' +
                ", Lat='" + Lat + '\'' +
                ", Long_='" + Long_ + '\'' +
                ", Confirmed='" + Confirmed + '\'' +
                ", Deaths='" + Deaths + '\'' +
                ", Recovered='" + Recovered + '\'' +
                ", Active='" + Active + '\'' +
                ", Combined_Key='" + Combined_Key + '\'' +
                ", Incidence_Rate='" + Incidence_Rate + '\'' +
                ", Case_Fatality_Ratio='" + Case_Fatality_Ratio + '\'' +
                '}';
    }
}
