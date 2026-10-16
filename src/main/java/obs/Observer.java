package obs;

@FunctionalInterface
public interface Observer {
    void update(Observable observable);
}
