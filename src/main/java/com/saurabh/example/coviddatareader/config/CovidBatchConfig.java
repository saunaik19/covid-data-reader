package com.saurabh.example.coviddatareader.config;

import com.saurabh.example.coviddatareader.batch.DBWriter;
import com.saurabh.example.coviddatareader.batch.Processor;
import com.saurabh.example.coviddatareader.batch.SkipRecord;
import com.saurabh.example.coviddatareader.model.CovidData;
import com.saurabh.example.coviddatareader.model.CovidRawDataCSV;
import com.saurabh.example.coviddatareader.service.CovidRepoFileService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class CovidBatchConfig {



    @Value("#{'${csv.file.metadata}'.split(',')}")
    @NonNull
    private String[] fileMetadata;

    @Autowired
    private CovidRepoFileService covidRepoFileService;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<CovidRawDataCSV> covidRawDataCSVReader(){
        FlatFileItemReader<CovidRawDataCSV> flatFileItemReader=new FlatFileItemReader<>();

        //Configure how each line will be parsed and mapped to different values
        flatFileItemReader.setLineMapper(new DefaultLineMapper() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(fileMetadata);
                    }
                });
                //Set values in class
                setFieldSetMapper(new BeanWrapperFieldSetMapper<CovidRawDataCSV>() {
                    {
                        setTargetType(CovidRawDataCSV.class);
                    }
                });
            }
        });


        flatFileItemReader.setLinesToSkip(1);
      return flatFileItemReader;
    }

    @Bean
    public MultiResourceItemReader<CovidRawDataCSV> multiResourceItemReader()
    {
        MultiResourceItemReader<CovidRawDataCSV> resourceItemReader = new MultiResourceItemReader<CovidRawDataCSV>();
        Resource[] inputResources=covidRepoFileService.allFileResources();
        resourceItemReader.setResources(inputResources);
        resourceItemReader.setDelegate(covidRawDataCSVReader());
        return resourceItemReader;
    }

    @Bean
    public Job readCovidFilesJob() {
        return jobBuilderFactory
                .get("readCSVFilesJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").<CovidRawDataCSV, CovidData>chunk(10)
                .faultTolerant().skipPolicy(new SkipRecord())
                .reader(multiResourceItemReader())
                .processor(new Processor())
                .writer(new DBWriter())
                .build();
    }


}

