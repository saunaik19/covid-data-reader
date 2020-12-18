package com.saurabh.example.coviddatareader.batch;


import com.saurabh.example.coviddatareader.model.CovidData;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBWriter implements ItemWriter<CovidData> {

 //   @Autowired
  //  private CovidRepo covidRepo;


    @Override
    public void write(List<? extends CovidData> covidData) throws Exception {
        covidData.forEach(System.out::println);
     //  covidRepo.saveAll(covidData);

    }
}
