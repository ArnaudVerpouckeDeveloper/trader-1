import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Participant {
    private String name;
    private int balance;

    public Participant(String name){
        this.name = name;
        balance = 100;
    }

    public int getBalance() {
        return this.balance;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name+": "+balance;
    }

    public void decreaseBalance(Integer amount) {
        this.balance = this.balance - amount;
        System.out.println(this.name + " lost " + amount + ". New balance is: " + this.balance + ". ");
    }

    public void increaseBalance(Integer amount) {
        this.balance = this.balance + amount;
        System.out.println(this.name + " received " + amount + ". New balance is: " + this.balance + ". ");
    }

}
