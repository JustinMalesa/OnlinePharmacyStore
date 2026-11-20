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

@WebServlet("/deleteMedicine")
public class DeleteMedicineServlet extends HttpServlet {

    private final MedicineService medicineService =
            new MedicineService();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String medicineIdValue =
                request.getParameter("medicineId");

        try {

            int medicineId = Integer.parseInt(medicineIdValue);

            if (medicineId <= 0) {
                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Invalid medicine ID.");
                return;
            }

            medicineService.removeMedicine(medicineId);

            response.sendRedirect(
                    request.getContextPath() + "/medicines?admin=true");

        } catch (NumberFormatException e) {

            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Please provide a valid medicine ID.");
        }
    }
}
