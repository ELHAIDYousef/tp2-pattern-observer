package obs;

public interface Observable {
    void subscribe(Observer o);
    void unsubscribe(Observer o);
    void notifyObservers();
}
