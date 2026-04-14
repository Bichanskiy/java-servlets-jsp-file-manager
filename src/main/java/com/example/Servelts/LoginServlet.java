package com.example.Servelts;

import com.example.accounts.AccountService;
import com.example.accounts.UserProfile;
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
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String sessionId = req.getSession().getId();

        if (login != null && password != null) {
            try{
                UserProfile profile = accountService.GetUserByLogin(login);

                if (profile != null && profile.GetPassword().equals(password)) {

                    accountService.AddSession(sessionId, profile);
                    req.getSession().setAttribute("userProfile", profile);

                    resp.sendRedirect(req.getContextPath() + "/files");
                }
                else {
                    req.setAttribute("error", "Invalid login or password");
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                }
            } catch (Exception e){
                req.setAttribute("error", "Invalid login or password");
            }


        }
    }
}
