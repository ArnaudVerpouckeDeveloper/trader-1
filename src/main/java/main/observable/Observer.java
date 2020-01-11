package main.observable;

public interface Observer<T> {
    void update(T t);
}
