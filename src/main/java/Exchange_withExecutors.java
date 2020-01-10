import com.sun.org.apache.xpath.internal.operations.Bool;
/*
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Exchange_withExecutors extends Observable {

    private static Executor executor = Executors.newFixedThreadPool(10);

    private List<Participant> participants = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private Boolean runExchange = false;
    public String exchangeName;
    static private Random rand = new Random();

    public Exchange_withExecutors(String exchangeName) {
        this.exchangeName = exchangeName;
        List<String> names = new ArrayList<>();
        names.add("James");
        names.add("Bob");
        names.add("Alice");
        names.add("Louis");
        names.add("Jim");

        for (int i = 0; i < 3; i++) {
            String pickedName = names.get(rand.nextInt(names.size()));
            names.remove(pickedName);
            participants.add(new Participant(pickedName));
        }

        System.out.println(participants.toString());
        startExchange();
        terminateExchange(5000);
    }

    public void terminateExchange(Integer terminateAfterMs) {
        executor.execute(() -> {
            try {
                Thread.sleep(terminateAfterMs);
                System.out.println("Terminating exchange...");
                runExchange = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void startExchange() {
        System.out.println("Starting exchange... \n\n");
        runExchange = true;
        OrderMatcher orderMatcherTest = new OrderMatcher(this);
        addObserver(orderMatcherTest);
        //Thread orderMatcher = new Thread(new OrderMatcher (this));


        executor.execute(() -> {
            try {
                while (runExchange) {
                    placeOrder(new Order(Order.OrderType.values()[rand.nextInt(Order.OrderType.values().length)], rand.nextInt(3), rand.nextInt(10), getRandomParticipant()));
                    Thread.sleep(rand.nextInt(8) * 1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

    public void placeOrder(Order order) {
        System.out.println(order.getParticipant().getName() + " placing order...");
        if (order.getParticipant().getBalance() >= order.getLimit() * order.getAmount()) {
            System.out.println("Order accepted \n");
            orders.add(order);
            setChanged();
            notifyObservers(this.orders);
        } else {
            System.out.println("Order canceled \n");
        }
        orders.add(order);
    }


    private Participant getRandomParticipant() {
        return participants.get(rand.nextInt(participants.size()));
    }
}
*/