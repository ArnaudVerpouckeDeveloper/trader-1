package main;

import main.observable.Observer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMatcher implements Observer<Exchange> {
    List<Order> buyOrders;
    List<Order> sellOrders;

    @Override
    public void update(Exchange exchange) {
        buyOrders = filterByOrderType(exchange.getOrders(), Order.OrderType.BUY);
        sellOrders = filterByOrderType(exchange.getOrders(), Order.OrderType.SELL);

        checkForMatchingOrders(exchange);
    }

    private List<Order> filterByOrderType(List<Order> orders, Order.OrderType orderType) {
        return orders
                .stream()
                .filter(order -> order.getOrderType() == orderType)
                .sorted()
                .collect(Collectors.toList());
    }

    private void checkForMatchingOrders(Exchange exchange) {
        for (Order buyOrder : buyOrders) {
            Order sellOrder = findSellOrderForBuyOrder(buyOrder);
            if (sellOrder != null) {
                exchange.buySellOrder(sellOrder, buyOrder);
                update(exchange);
                break;
            }
        }
    }

    private Order findSellOrderForBuyOrder(Order buyOrder) {
        for (Order sellOrder : sellOrders) {
            if (sellOrder.getLimit() <= buyOrder.getLimit() && sellOrder.getAmountOfTokens() >= buyOrder.getAmountOfTokens()) {
                System.out.println("SellOrder[" + sellOrder + "] can be bought by buyOrder[" + buyOrder + "]\n");
                return sellOrder;
            } else {
                System.out.println("SellOrder[" + sellOrder + "] cannot be bought by buyOrder[" + buyOrder + "]\n");
            }
        }
        return null;
    }
}