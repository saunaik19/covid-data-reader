package com.saurabh.example.coviddatareader.batch;

import com.saurabh.example.coviddatareader.model.CovidData;
import com.saurabh.example.coviddatareader.model.CovidRawDataCSV;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class Processor implements ItemProcessor<CovidRawDataCSV, CovidData> {

    @Override
    public CovidData process(CovidRawDataCSV item) throws Exception {
        CovidData covidData=new CovidData();
           covidData.setActive(item.getActive());
           covidData.setAdmin2(item.getAdmin2());
           covidData.setCase_Fatality_Ratio(item.getCase_Fatality_Ratio());
           covidData.setCombined_Key(item.getCombined_Key());
           covidData.setConfirmed(item.getConfirmed());
           covidData.setDeaths(item.getDeaths());
           covidData.setCountry_Region(item.getCountry_Region());
           covidData.setIncidence_Rate(item.getIncidence_Rate());
           covidData.setFIPS(item.getFIPS());
           covidData.setLast_Update(item.getLast_Update());
           covidData.setLat(item.getLat());
           covidData.setLong_(item.getLong_());
           covidData.setProvince_State(item.getProvince_State());
           covidData.setRecovered(item.getRecovered());
        return covidData;
    }
}
