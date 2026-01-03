package com.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, IOException {
        String path = req.getParameter("path");
        File file = new File(path);

        if (file.exists() && file.isFile()) {
            resp.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            Files.copy(file.toPath(), resp.getOutputStream());
        }
    }
}