package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/files")
public class MainServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getParameter("path");
        List<FileInfo> fileInfoList = getFileInfos(path);
        String parentPath = new  File(path).getParent();
        req.setAttribute("parentPath", parentPath);
        req.setAttribute("fileInfoList", fileInfoList);
        req.setAttribute("time", LocalDateTime.now()
                        .format(DateTimeFormatter
                                .ofPattern("dd-MM HH:mm")));
        req.getRequestDispatcher("mypage.jsp").forward(req, resp);
    }

    private static List<FileInfo> getFileInfos(String path) {
        List<FileInfo> fileInfoList = new ArrayList<>();
        FileInfo fileInfo = new FileInfo(path);
        if (fileInfo.getIsDirectory()){
            fileInfoList = fileInfo.getFileInfoList();
        }
        return fileInfoList;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        super.doPost(req, resp);
    }
}