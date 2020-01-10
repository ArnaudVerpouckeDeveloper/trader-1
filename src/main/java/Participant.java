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
}
