import java.util.*;

public class OrderMatcher implements Observer {
    private Exchange exchange;
    List<Order> buyOrders;
    List<Order> sellOrders;

    public OrderMatcher(Exchange exchange){
        this.exchange = exchange;
    }

    @Override
    public void update(Observable observable, Object arg) {
        buyOrders = new ArrayList<>();
        sellOrders = new ArrayList<>();
        for (Order order:exchange.getOrders()) {
            switch (order.getOrderType()){
                case BUY:
                    buyOrders.add(order);
                    break;
                case SELL:
                    sellOrders.add(order);
                    break;
            }
        }
        System.out.println("not sorted: " + buyOrders);
        Collections.sort(buyOrders);
        System.out.println("sorted:     " + buyOrders + "\n");
        Collections.sort(sellOrders);
        checkForMatchingOrders();
    }

    private void checkForMatchingOrders(){
        for (Order order:exchange.getOrders()) {
            switch (order.getOrderType()){
                case BUY:
                    checkIfOrderCanBeBought(order);
                    break;
                case SELL:
                    sellOrders.add(order);
                    break;
            }
        }
    }

    private void checkIfOrderCanBeBought(Order buyOrder) {
        //System.out.println(buyOrders);
        for (Order sellOrder : sellOrders) {

        }
    }
}