package main;

public class Order implements Comparable< Order >{


    enum OrderType{
        BUY,
        SELL
    }
    private Integer amountOfTokens;
    private OrderType orderType;
    private Integer limit;
    private Participant participant;
    private Integer id;

    public Order(OrderType orderType, Integer amountOfTokens, Integer limit, Participant participant, Integer id){
        this.orderType = orderType;
        this.amountOfTokens = amountOfTokens;
        this.limit = limit;
        this.participant = participant;
        this.id = id;

        System.out.println("CREATED: " + this.toString());

    }

    public Integer getAmountOfTokens() {
        return amountOfTokens;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public Integer getLimit() {
        return limit;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void decreaseAmountOfTokens(Integer amount) {
        this.amountOfTokens -= amount;
    }

    @Override
    public String toString() {
        //return "@id:" + this.id + " " +this.participant.getName() + " - " +this.orderType + " | " + this.amount + " | " + "Limit " + this.limit + "";
        //return "@id:" + this.id;
        return "@" + this.id + ":"
                +" " + this.getOrderType()
                +" limit="+limit
                +" amount="+ amountOfTokens
                +" by " + this.participant.getName()
                ;
    }

    @Override
    public int compareTo(Order order) {
        return this.getLimit().compareTo(order.getLimit());
    }
}
