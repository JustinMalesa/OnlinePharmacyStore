/**
 *
 * @author justi
 */

package onlinepharmacystore.controller;

import onlinepharmacystore.service.MedicineService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/addMedicine")
public class AddMedicineServlet extends HttpServlet {

    private final MedicineService medicineService =
            new MedicineService();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String priceValue = request.getParameter("price");

        if (name == null || name.trim().isEmpty()
                || category == null || category.trim().isEmpty()
                || priceValue == null || priceValue.trim().isEmpty()) {

            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "All medicine fields are required.");
            return;
        }

        try {

            double price = Double.parseDouble(priceValue);

            if (!Double.isFinite(price) || price <= 0) {
                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Medicine price must be greater than zero.");
                return;
            }

            medicineService.addMedicine(
                    name.trim(),
                    category.trim(),
                    price
            );

            response.sendRedirect(
                    request.getContextPath() + "/medicines?admin=true");

        } catch (NumberFormatException e) {

            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Please enter a valid medicine price.");
        }
    }
}