package com.saurabh.example.coviddatareader.service;

import com.saurabh.example.coviddatareader.constants.FilePathConstants;
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

    public List<Path> getAllFileForLocalDir(){
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
}
