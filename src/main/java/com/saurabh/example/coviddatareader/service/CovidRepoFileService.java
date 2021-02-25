package com.saurabh.example.coviddatareader.service;

import com.saurabh.example.coviddatareader.constants.FilePathConstants;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class CovidRepoFileService {

    protected boolean isDirEmpty(final Path directory) throws IOException {
        try(DirectoryStream<Path> dirStream = Files.newDirectoryStream(directory)) {
            return !dirStream.iterator().hasNext();
        }
    }
    public void printAllFileForLocalDir(){
        try (Stream<Path> paths = Files.walk(Paths.get(FilePathConstants.LOCAL_COVID_REPO_DIR_PATH))) {
            paths.filter(Files::isRegularFile)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected List<Path> getAllFileForLocalDir(){
        List<Path> allPaths=new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(FilePathConstants.LOCAL_COVID_REPO_DIR_PATH))) {
            paths.forEach(path->{
                allPaths.add(path);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allPaths;
    }

    public List<String> allFilePath(){
        List<Path> allPaths=getAllFileForLocalDir();
        List<String> allPathStr=new ArrayList<>(allPaths.size());
        for(Path filePath: allPaths){
            Resource resourceMulti=new FileSystemResource(filePath.toString());
//            System.out.println("Path is "+filePath);
            System.out.println("KHUP SAARYA FILES PAIKI ,File AHE KA ????"
                    +resourceMulti.exists());
            System.out.println("FILE PATH IS "+filePath);
         //   allPathStr.add(filePath.toString());
        }
        allPaths.forEach(path->{
            if(new FileSystemResource(path.toString()).exists() && path.toString().endsWith(".csv")){
                allPathStr.add(path.toString());
            }
        });
        return allPathStr;
    }
    public Resource[] allFileResources(){
        List<Path> allPaths=getAllFileForLocalDir();
        List<Resource> allResources=new ArrayList<>(allPaths.size());
        allPaths.forEach(path->{
            if(new FileSystemResource(path.toString()).exists() && path.toString().endsWith(".csv")
            && path.toString().contains("csse_covid_19_daily_reports")){
                Resource resourceMulti=new FileSystemResource(path.toString());
                allResources.add(resourceMulti);
                //allPaths
            }
        });
    return  allResources.toArray(new Resource[allResources.size()]);
    }
}
