package com.saurabh.example.coviddatareader.resource;




import com.saurabh.example.coviddatareader.service.CovidRepoFileService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReadCovidDataResource {
    @Autowired
    private Job fileReaderJob;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private CovidRepoFileService covidRepoFileService;
    @RequestMapping(path = "/initBatch")
    public BatchStatus loadCovidDatafromFile() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobRestartException, JobInstanceAlreadyCompleteException {
        Map<String, JobParameter> params = new HashMap<>();
        System.out.println("STARTING JOB.......");
        params.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(params);
        JobExecution jobExecution = jobLauncher.run(fileReaderJob, jobParameters);
        System.out.println(" Job Execution : " + jobExecution.getStatus());

        while (jobExecution.isRunning()) {
            System.out.println("....");
        }
        return jobExecution.getStatus();
    }
    @GetMapping(path = "/paths")
    public List<String> allFilePaths(){
        return covidRepoFileService.allFilePath();
    }
}
