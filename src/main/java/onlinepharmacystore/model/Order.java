
package onlinepharmacystore.model;

/**
 *
 * @author justi
 */

import java.util.List;

public class Order {

    private int orderId;
    private List<CartItem> items;
    private double totalPrice;

    public Order() {
    }

    public Order(int orderId, List<CartItem> items, double totalPrice) {
        this.orderId = orderId;
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
