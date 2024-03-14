package ru.sug4chy.demo5.servlet;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ru.sug4chy.demo5.service.FileService;

@WebServlet("/files")
public class FilesServlet extends HttpServlet {

    private final FileService fileService = new FileService();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String path = request.getParameter("path");
        File[] directories = fileService.getDirectories(path);
        if (directories == null) {
            directories = new File[0];
        }

        File[] files = fileService.getFiles(path);
        if (files == null) {
            files = new File[0];
        }

        request.setAttribute("directories", directories);
        request.setAttribute("files", files);
        var dispatcher = request.getRequestDispatcher("files.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}