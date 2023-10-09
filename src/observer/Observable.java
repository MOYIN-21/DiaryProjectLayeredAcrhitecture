package observer;

public interface Observable {
    void add(MyObserver observer);
    void notifyObserver(MyObserver observer);
    void createContent(Update update);
    void remove(MyObserver observer);
}
