package observer;


import java.util.ArrayList;
import java.util.List;

public class Blog implements Observable{
    private final List<MyObserver> subscribers = new ArrayList<>();
    private final String name;

    public Blog(String name){
        this.name = name;
    }
    @Override
    public void add(MyObserver subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void notifyObserver(MyObserver observer) {

    }

    @Override
    public void createContent(Update update) {

    }

    @Override
    public void remove(MyObserver observer) {

    }
}
