package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            resp.sendRedirect("/login.jsp");
        } else {
            resp.sendRedirect("/user/hello.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String requestLogin = req.getParameter("login");
        String requestPassword = req.getParameter("password");
        List<String> users = Users.getInstance().getUsers();
        if (users.contains(requestLogin) && !requestPassword.isEmpty()) {
            req.getRequestDispatcher("/user/hello.jsp").forward(req, resp);
            session.setAttribute("user", requestLogin);
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
