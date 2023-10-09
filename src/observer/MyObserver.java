package observer;

public interface MyObserver {
    void update(Observable observable);
    void subscribe(Observable observable);
    void  unsubscribe();
    int countUpdates();
}
