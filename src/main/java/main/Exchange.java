package main;

import main.observable.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Exchange extends Observable<Exchange> {
    private List<Participant> participants = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private Boolean runExchange = false;
    public String exchangeName;
    static private Random rand = new Random();

    public Exchange(String exchangeName) {
        this.exchangeName = exchangeName;
        List<String> names = new ArrayList<>();
        names.add("James");
        names.add("Bob");
        names.add("Alice");
        names.add("Louis");
        names.add("Jim");

        for (int i = 0; i < 4; i++) {
            String pickedName = names.get(rand.nextInt(names.size()));
            names.remove(pickedName);
            participants.add(new Participant(pickedName));
        }

        System.out.println(participants.toString());
        startExchange();
        terminateExchange(5000);
    }

    public void terminateExchange(Integer terminateAfterMs) {
        new Thread(() -> {
            try {
                Thread.sleep(terminateAfterMs);
                System.out.println("Terminating exchange...");
                runExchange = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void startExchange() {
        System.out.println("Starting exchange... \n\n");
        runExchange = true;
        OrderMatcher orderMatcherTest = new OrderMatcher();
        addObserver(orderMatcherTest);


        new Thread(() -> {
            try {
                Integer orderId = 0;
                while (runExchange) {
                    placeOrder(new Order(Order.OrderType.values()[rand.nextInt(Order.OrderType.values().length)], rand.nextInt(5)+1, rand.nextInt(10), getRandomParticipant(),orderId));
                    orderId++;
                    Thread.sleep(rand.nextInt(15) * 100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public void placeOrder(Order order) {
        if (order.getParticipant().getBalance() >= order.getLimit() * order.getAmountOfTokens()) {
            System.out.println("Order accepted");
            orders.add(order);
            notifyObservers(this);
        } else {
            System.out.println("Order canceled");
        }
    }

    public void removeOrder(Order order){
        this.orders.remove(order);
    }

    @Override
    public String toString() {
        return "Exchange says hi";
    }

    private Participant getRandomParticipant() {
        return participants.get(rand.nextInt(participants.size()));
    }
    public List<Order> getOrders() {
        return orders;
    }




    public void buySellOrder(Order sellOrder, Order buyOrder) {
        Integer transactionValue = buyOrder.getAmountOfTokens() * sellOrder.getLimit();
        buyOrder.getParticipant().decreaseBalance(transactionValue);
        sellOrder.getParticipant().increaseBalance(transactionValue);

        if (sellOrder.getAmountOfTokens() - buyOrder.getAmountOfTokens() > 0) {
            //There are still tokens for sale in the sellOrder
            sellOrder.decreaseAmountOfTokens(buyOrder.getAmountOfTokens());
            removeOrder(buyOrder);
        } else {
            //There are no tokens left for sale in the sellOrder
            if (buyOrder.getAmountOfTokens() - sellOrder.getAmountOfTokens() > 0) {
                buyOrder.decreaseAmountOfTokens(sellOrder.getAmountOfTokens());
            }
            removeOrder(sellOrder);
        }
    }
}
