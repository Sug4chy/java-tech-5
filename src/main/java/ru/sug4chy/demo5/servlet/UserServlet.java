package ru.sug4chy.demo5.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.sug4chy.demo5.model.UserProfile;
import ru.sug4chy.demo5.service.AccountService;
import ru.sug4chy.demo5.service.FileService;

import java.io.IOException;

import static ru.sug4chy.demo5.service.FileService.usersHomeDirectoriesPath;

@WebServlet("/register")
public class UserServlet extends HttpServlet {

    private final AccountService accountService = new AccountService();
    private final FileService fileService = new FileService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (login.isEmpty() || email.isEmpty() || password.isEmpty()) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Отсутствует значение одного или нескольких полей");
            return;
        }

        var user = new UserProfile(login, password, email);
        if (accountService.getUserByLogin(login) != null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            resp.getWriter().println("Пользователь с таким логином уже существует");
            return;
        }

        accountService.addNewUser(user);
        req.getSession().setAttribute("login", login);
        req.getSession().setAttribute("password", password);

        try {
            fileService.createDirectory(usersHomeDirectoriesPath + "\\" + login);
        } catch (IOException e) {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println(e.getMessage());
            return;
        }

        String url = req.getRequestURL().toString();
        resp.sendRedirect( url.substring(0, url.lastIndexOf("/")) + "/files");
    }
}
