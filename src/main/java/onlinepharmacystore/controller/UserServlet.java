package onlinepharmacystore.controller;
/**
 *
 * @author justi
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import onlinepharmacystore.model.User;
import onlinepharmacystore.util.PasswordUtil;

@WebServlet(urlPatterns = "/user", loadOnStartup = 1)
public class UserServlet extends HttpServlet {

    private static final String USERNAME_PATTERN =
            "^[A-Za-z0-9_]{3,20}$";

    private static final int MIN_PASSWORD_LENGTH = 8;

    private final List<User> users =
            new CopyOnWriteArrayList<>();

    @Override
    public void init() throws ServletException {

        String adminUsername =
                System.getenv("PHARMACY_ADMIN_USERNAME");

        String adminPassword =
                System.getenv("PHARMACY_ADMIN_PASSWORD");

        if (adminUsername == null
                || adminUsername.trim().isEmpty()
                || adminPassword == null
                || adminPassword.length() < MIN_PASSWORD_LENGTH) {

            throw new ServletException(
                    "Administrator credentials are missing or invalid. "
                    + "Please configure PHARMACY_ADMIN_USERNAME "
                    + "and PHARMACY_ADMIN_PASSWORD.");
        }

        adminUsername = adminUsername.trim();

        if (!adminUsername.matches(USERNAME_PATTERN)) {
            throw new ServletException(
                    "Administrator username must contain "
                    + "3 to 20 letters, numbers or underscores.");
        }

        users.add(new User(
                adminUsername,
                PasswordUtil.hash(adminPassword),
                "ADMIN"
        ));
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/register.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("register".equals(action)) {
            register(request, response);

        } else if ("login".equals(action)) {
            login(request, response);

        } else if ("logout".equals(action)) {
            logout(request, response);

        } else {
            response.sendRedirect("login.jsp");
        }
    }

    private synchronized void register(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null
                || !username.trim().matches(USERNAME_PATTERN)) {

            showRegisterError(
                    request,
                    response,
                    "Username must be 3 to 20 letters, numbers or underscores.");
            return;
        }

        username = username.trim();

        if (password == null
                || password.length() < MIN_PASSWORD_LENGTH) {

            showRegisterError(
                    request,
                    response,
                    "Password must be at least 8 characters.");
            return;
        }

        if (findUser(username) != null) {
            showRegisterError(
                    request,
                    response,
                    "That username is already taken.");
            return;
        }

        users.add(new User(
                username,
                PasswordUtil.hash(password),
                "USER"
        ));

        request.setAttribute(
                "message",
                "Registration Successful!");

        request.getRequestDispatcher("/login.jsp")
                .forward(request, response);
    }

    private void login(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = findUser(
                username == null ? "" : username.trim());

        if (user != null
                && PasswordUtil.verify(
                        password,
                        user.getPasswordHash())) {

            HttpSession session = request.getSession();

            request.changeSessionId();

            session.setAttribute("loggedInUser", user);

            if ("ADMIN".equals(user.getRole())) {
                response.sendRedirect("adminDashboard.jsp");
            } else {
                response.sendRedirect("profile.jsp");
            }

            return;
        }

        request.setAttribute(
                "message",
                "Invalid Login Details");

        request.getRequestDispatcher("/login.jsp")
                .forward(request, response);
    }

    private void logout(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        response.sendRedirect("login.jsp");
    }

    private User findUser(String username) {

        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }

        return null;
    }

    private void showRegisterError(
            HttpServletRequest request,
            HttpServletResponse response,
            String message)
            throws ServletException, IOException {

        request.setAttribute("message", message);

        request.getRequestDispatcher("/register.jsp")
                .forward(request, response);
    }
}