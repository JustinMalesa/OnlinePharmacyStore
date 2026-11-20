
package onlinepharmacystore.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import onlinepharmacystore.model.User;

@WebFilter("/*")
public class AdminFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) request;

        HttpServletResponse httpResponse =
                (HttpServletResponse) response;

        String contextPath = httpRequest.getContextPath();
        System.out.println("ADMIN FILTER IS RUNNING");

        String path = httpRequest.getRequestURI()
                .substring(contextPath.length());

        boolean adminPage =
                path.equals("/adminDashboard.jsp")
                || path.equals("/manageMedicines.jsp")
                || path.equals("/addMedicine")
                || path.equals("/deleteMedicine");

        if (!adminPage) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session =
                httpRequest.getSession(false);

        User loggedInUser = null;

        if (session != null) {
            loggedInUser = (User) session.getAttribute(
                    "loggedInUser");
        }

        if (loggedInUser == null) {
            httpResponse.sendRedirect(
                    contextPath + "/login.jsp");
            return;
        }

        if (!"ADMIN".equals(loggedInUser.getRole())) {
            httpResponse.sendError(
                    HttpServletResponse.SC_FORBIDDEN,
                    "Administrator access required.");
            return;
        }

        chain.doFilter(request, response);
    }
}
