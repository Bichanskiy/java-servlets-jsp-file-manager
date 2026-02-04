package com.example.Servelts;

import com.example.accounts.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final AccountService accountService = AccountService.getInstance();



    @Override
    protected void doGet(HttpServletRequest req,  HttpServletResponse resp)
        throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,  HttpServletResponse resp)
        throws ServletException, IOException{
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String sessionId = req.getSession().getId();

        if (login != null && password != null) {
            if (accountService.GetUserByLogin(login) != null &&
            accountService.GetUserByLogin(login).GetPassword().equals(password)) {
                accountService.AddSession(sessionId, accountService.GetUserByLogin(login));
                resp.sendRedirect("/servlet/files");
            }
            else {
                req.setAttribute("error", "Invalid login or password");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        }
    }
}
