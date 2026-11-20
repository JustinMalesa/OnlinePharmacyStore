/**
 *
 * @author justi
 */

package onlinepharmacystore.controller;

import onlinepharmacystore.model.User;
import onlinepharmacystore.service.MedicineService;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/medicines")
public class MedicineServlet extends HttpServlet {

    private final MedicineService medicineService =
            new MedicineService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String category = request.getParameter("category");
        String admin = request.getParameter("admin");

        if ("true".equals(admin)) {

            HttpSession session =
                    request.getSession(false);

            User loggedInUser = null;

            if (session != null) {
                loggedInUser = (User) session.getAttribute(
                        "loggedInUser");
            }

            if (loggedInUser == null) {
                response.sendRedirect(
                        request.getContextPath() + "/login.jsp");
                return;
            }

            if (!"ADMIN".equals(loggedInUser.getRole())) {
                response.sendError(
                        HttpServletResponse.SC_FORBIDDEN,
                        "Administrator access required.");
                return;
            }

            request.setAttribute(
                    "medicineList",
                    medicineService.getMedicinesByCategory(category));

            request.getRequestDispatcher("/manageMedicines.jsp")
                    .forward(request, response);

            return;
        }

        request.setAttribute(
                "medicineList",
                medicineService.getMedicinesByCategory(category));

        request.getRequestDispatcher("/medicines.jsp")
                .forward(request, response);
    }
}