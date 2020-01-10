public class Order implements Comparable< Order >{
    @Override
    public int compareTo(Order order) {
        return this.getLimit().compareTo(order.getLimit());
    }

    enum OrderType{
        BUY,
        SELL
    }

    private Integer amount;
    private OrderType orderType;
    private Integer limit;
    private Participant participant;
    private Integer id;

    public Order(OrderType orderType, Integer amount, Integer limit, Participant participant,Integer id){
        this.orderType = orderType;
        this.amount = amount;
        this.limit = limit;
        this.participant = participant;
        this.id = id;

        System.out.println("Created: " + this.id + "-" + this.getLimit());

        //System.out.println(toString());
    }

    public Integer getAmount() {
        return amount;
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

    @Override
    public String toString() {
        //        return "@id:" + this.id + " " +this.participant.getName() + " - " +this.orderType + " | " + this.amount + " | " + "Limit " + this.limit + "";
        //return "@id:" + this.id;
        return "@id:" + this.id + "-"+limit;

    }
}
