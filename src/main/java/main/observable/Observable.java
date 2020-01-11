package main.observable;

import java.util.HashSet;
import java.util.Set;

public abstract class Observable<T> {
    private Set<Observer<T>> observers = new HashSet<>();

    public void addObserver(Observer<T> observer) {
        observers.add(observer);
    }

    public void notifyObservers(T t) {
        observers.forEach(observer -> observer.update(t));
    }
}
