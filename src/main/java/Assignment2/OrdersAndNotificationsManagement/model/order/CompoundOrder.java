package Assignment2.OrdersAndNotificationsManagement.model.order;

import Assignment2.OrdersAndNotificationsManagement.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CompoundOrder implements IOrder {
    public int OrderID;
    List<IOrder> orders; //can be either simple order or compound orders
    public String OrderOwner;
    public CompoundOrder(String orderOwner){
        orders = new ArrayList<>();
        SimpleOrder order= new SimpleOrder(orderOwner);
        orders.add(order);
    }
    public CompoundOrder() {

    }
    public List<IOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<IOrder> orders) {
        this.orders = orders;
    }
    public void addOrder(IOrder order){
        orders.add(order);
    }
    public void removeOrder(IOrder order){
        orders.remove(order);
    }
    @Override
    public List<Product> getInfo() { // I am 90% sure this will not work
        List<Product> products = new ArrayList<>();

        for (IOrder order: orders) {

            products.addAll(order.getInfo());

        }

        return products;
    }
    public void addProduct(int productID,String orderOwner){
        for (IOrder order:orders) {
            if(order instanceof SimpleOrder){
                if(order.getOwner() == orderOwner){
                    ((SimpleOrder) order).addProduct(productID);
                }
            }
        }
    }

    @Override
    public int getOrderId(){
        return OrderID;
    }

    @Override
    public void setOrderID(int orderId)
    {
        this.OrderID = orderId;
    }

    @Override
    public String getOwner() {
        return OrderOwner;
    }

    @Override
    public void setOwner(String owner) {
        OrderOwner = owner;
    }
}
