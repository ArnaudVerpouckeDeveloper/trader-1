import java.util.*;

public class OrderMatcher implements Observer {
    private Exchange exchange;
    List<Order> buyOrders;
    List<Order> sellOrders;

    @Override
    public void update(Observable observable, Object arg) {
        this.exchange = (Exchange) observable;
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
        //System.out.println("not sorted: " + buyOrders);
        Collections.sort(buyOrders);
        //System.out.println("sorted:     " + buyOrders + "\n");
        Collections.sort(sellOrders);
        checkForMatchingOrders();
    }

    private void checkForMatchingOrders(){
        System.out.println("_____________________________________________________________________________");
        System.out.println("@@@");
        for (Order order:exchange.getOrders()) {
            switch (order.getOrderType()){
                case BUY:
                    checkIfOrderCanBeBought(order);
                    break;
                case SELL:
                    //sellOrders.add(order);
                    break;
            }
        }
        System.out.println("_____________________________________________________________________________\n\n\n");

    }

    private void checkIfOrderCanBeBought(Order buyOrder) {
        for (Order sellOrder : sellOrders) {
            System.out.println("----------------start------------------");
            if (sellOrder.getLimit() <= buyOrder.getLimit() && sellOrder.getAmountOfTokens() >= buyOrder.getAmountOfTokens()){
                System.out.println("SellOrder["+sellOrder+"] can be bought by buyOrder["+buyOrder+"]");
                buySellOrder(sellOrder, buyOrder);
                //System.out.println("Potentional sellOrder: " + sellOrder + " for buyOrder: " + buyOrder);
            }
            else
            {
                System.out.println("SellOrder["+sellOrder+"] cannot be bought by buyOrder["+buyOrder+"]");
            }
            System.out.println("-----------------end-------------------\n");

        }

    }

    private void buySellOrder(Order sellOrder, Order buyOrder) {
        Integer transactionValue = buyOrder.getAmountOfTokens() * sellOrder.getLimit();
        buyOrder.getParticipant().decreaseBalance(transactionValue);
        sellOrder.getParticipant().increaseBalance(transactionValue);


        if(sellOrder.getAmountOfTokens() - buyOrder.getAmountOfTokens() > 0){
            //There are still tokens for sale in the sellOrder
            sellOrder.decreaseAmountOfTokens(buyOrder.getAmountOfTokens());
            exchange.removeOrder(buyOrder);
        }
        else{
            //There are no tokens left for sale in the sellOrder
            if (buyOrder.getAmountOfTokens() - sellOrder.getAmountOfTokens() > 0){
                buyOrder.decreaseAmountOfTokens(sellOrder.getAmountOfTokens());
            }
            exchange.removeOrder(sellOrder);
        }




    }
}