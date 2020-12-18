package com.saurabh.example.coviddatareader.config;

import com.saurabh.example.coviddatareader.model.CovidRawDataCSV;
import com.saurabh.example.coviddatareader.service.CovidRepoFileService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
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
    public MultiResourceItemReader<CovidRawDataCSV> multiResourceItemreader() {
        MultiResourceItemReader<CovidRawDataCSV> reader = new MultiResourceItemReader<>();
        reader.setDelegate(covidCSVFileItemReader());
        reader.setResources(allCovidCSVInputFiles());
        return reader;
    }

    @Bean
    private FlatFileItemReader<CovidRawDataCSV> covidCSVFileItemReader() {

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(",");
        tokenizer.setStrict(false);
        System.out.println(Arrays.toString(fileMetadata));
        tokenizer.setNames(fileMetadata);

        FlatFileItemReader<CovidRawDataCSV> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(covidCsvFileLineMapper());
        flatFileItemReader.setStrict(false);
        flatFileItemReader.setName("covid-data-reader");
        return flatFileItemReader;
    }

    @Bean
    private Resource[] allCovidCSVInputFiles() {
        List<Path> allFilePath = covidRepoFileService.getAllFileForLocalDir();
        List<Resource> allResources = new ArrayList<>(allFilePath.size());
        allFilePath.forEach(path -> {
            Resource resource = new FileSystemResource(path);
            if (path.endsWith(".csv")) {
                allResources.add(resource);
            }
        });
        return allResources.toArray(new Resource[allFilePath.size()]);
    }


    @Bean
    public LineMapper<CovidRawDataCSV> covidCsvFileLineMapper() {
        DefaultLineMapper<CovidRawDataCSV> dataCSVDefaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(",");
        delimitedLineTokenizer.setStrict(false);
        System.out.println(fileMetadata.toString());
        System.out.println(Arrays.toString(fileMetadata));

        delimitedLineTokenizer.setNames(fileMetadata);
        BeanWrapperFieldSetMapper<CovidRawDataCSV> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(CovidRawDataCSV.class);
        dataCSVDefaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        dataCSVDefaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return dataCSVDefaultLineMapper;
    }

    @Bean
    public ItemWriter<CovidRawDataCSV> covidRawDataCSVItemWriter() {
        return items -> {
            for (CovidRawDataCSV covidRawDataCSV : items) {
                System.out.println(covidRawDataCSV.toString());
            }
        };
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<CovidRawDataCSV, CovidRawDataCSV>chunk(10)
                .reader(multiResourceItemreader())
                .writer(covidRawDataCSVItemWriter())
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .start(step1())
                .build();
    }

}

