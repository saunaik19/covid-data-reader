package com.saurabh.example.coviddatareader.resource;

import com.saurabh.example.coviddatareader.service.GitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GitRepoResource {
    @Autowired
    GitService gitService;

    @GetMapping(path = "/loadRepo")
    public String loadRepo(){
        return gitService.loadRepo();
    }
    @GetMapping(path = "/checkRepo")
    public boolean checkRepo(){
        return gitService.checkRepo();
    }



}
