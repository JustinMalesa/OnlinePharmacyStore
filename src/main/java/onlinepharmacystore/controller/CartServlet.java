/**
 *
 * @author justi
 */


package onlinepharmacystore.controller;

import onlinepharmacystore.model.CartItem;
import onlinepharmacystore.model.Medicine;
import onlinepharmacystore.service.MedicineService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private MedicineService medicineService =
            new MedicineService();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<CartItem> cart =
                (List<CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        request.setAttribute("cart", cart);

        request.getRequestDispatcher("/cart.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        HttpSession session = request.getSession();

        List<CartItem> cart =
                (List<CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        try {

            if ("add".equals(action)) {

                int medicineId = Integer.parseInt(
                        request.getParameter("medicineId"));

                String quantityParameter =
                        request.getParameter("quantity");

                int quantity = quantityParameter == null
                        ? 1 : Integer.parseInt(quantityParameter);

                if (quantity < 1 || quantity > 100) {
                    response.sendError(
                            HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }

                boolean found = false;

                for (CartItem item : cart) {

                    if (item.getMedicine().getId() == medicineId) {

                        item.setQuantity(
                                item.getQuantity() + quantity);

                        found = true;
                        break;
                    }
                }

                if (!found) {

                    for (Medicine medicine :
                            medicineService.getAllMedicines()) {

                        if (medicine.getId() == medicineId) {

                            cart.add(new CartItem(medicine, quantity));
                            break;
                        }
                    }
                }

            } else if ("increase".equals(action)
                    || "decrease".equals(action)
                    || "remove".equals(action)) {

                int medicineId = Integer.parseInt(
                        request.getParameter("medicineId"));

                if ("remove".equals(action)) {

                    cart.removeIf(item ->
                            item.getMedicine().getId() == medicineId);

                } else {

                    for (CartItem item : cart) {

                        if (item.getMedicine().getId() == medicineId) {

                            if ("increase".equals(action)
                                    && item.getQuantity() < 100) {

                                item.setQuantity(
                                        item.getQuantity() + 1);

                            } else if ("decrease".equals(action)
                                    && item.getQuantity() > 1) {

                                item.setQuantity(
                                        item.getQuantity() - 1);
                            }

                            break;
                        }
                    }
                }
            }

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        session.setAttribute("cart", cart);

        response.sendRedirect("cart");
    }
}
