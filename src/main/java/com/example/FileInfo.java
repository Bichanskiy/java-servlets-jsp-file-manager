package com.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileInfo {
    private String name;
    private Boolean isDirectory;
    private String path;

    public FileInfo(String path) {
        File file = new File(path);
        if (file.exists()) {
            this.name = file.getName();
            this.isDirectory = file.isDirectory();
            this.path = path;
        }
    }

    public String getName(){return name;}
    public Boolean getIsDirectory(){return isDirectory;}
    public String getPath(){return path;}

    public List<FileInfo> getFileInfoList(){
        File file = new File(path);
        List<FileInfo> fileInfoList = new ArrayList<>();
        if (file.exists()) {
            File[] files = file.listFiles();
            for (File f : files){
                fileInfoList.add(new FileInfo(f.getPath()));
            }
        }

        return fileInfoList;
    }

}
