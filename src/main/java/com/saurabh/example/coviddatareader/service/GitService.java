package com.saurabh.example.coviddatareader.service;


import com.saurabh.example.coviddatareader.constants.FilePathConstants;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.eclipse.jgit.api.Git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class GitService {
    @Autowired
    private CovidRepoFileService covidRepoFileService;

    private File localDir=new File(FilePathConstants.LOCAL_COVID_REPO_DIR_PATH);

    private Collection<Ref> repoCollection() throws GitAPIException {
        Git.cloneRepository()
                .setURI(FilePathConstants.REMOTE_REPO_PATH)
                .setDirectory(localDir)
                .setCloneAllBranches(true).
                call();
       return Git.lsRemoteRepository()
               .setHeads(true)
               .setTags(true)
               .setRemote(FilePathConstants.REMOTE_REPO_PATH)
               .call();
   }
    public String loadGitRepo() throws GitAPIException {
        System.out.println("Listing remote repository " + FilePathConstants.REMOTE_REPO_PATH);
        Collection<Ref> refs  = repoCollection();

        for (Ref ref : refs) {
            System.out.println("Ref: " + ref);
        }

        final Map<String, Ref> map = Git.lsRemoteRepository()
                .setHeads(true)
                .setTags(true)
                .setRemote(FilePathConstants.REMOTE_REPO_PATH)
                .callAsMap();

        System.out.println("As map");
        for (Map.Entry<String, Ref> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Ref: " + entry.getValue());
        }

        refs = Git.lsRemoteRepository()
                .setRemote(FilePathConstants.REMOTE_REPO_PATH)
                .call();

        System.out.println("All refs");
        for (Ref ref : refs) {
            System.out.println("Ref: " + ref);
        }
        return "";
    }


    public String loadRepo() {
            try {
               return loadGitRepo();
            } catch (GitAPIException e) {
                e.printStackTrace();
            }
       return "NO";
    }

    public boolean checkRepo(){
        try {
          return   checkRepoisLoaded();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    private boolean checkRepoisLoaded() throws IOException {
        Path dirPath=localDir.toPath();
        if(covidRepoFileService.isDirEmpty(localDir.toPath())){
            System.out.println("Folder is empty...,Loading remote repository "+FilePathConstants.REMOTE_REPO_PATH);
            loadRepo();
        }else {
           covidRepoFileService.printAllFileForLocalDir();
        }
    return (!covidRepoFileService.getAllFileForLocalDir().isEmpty());
    }


}
