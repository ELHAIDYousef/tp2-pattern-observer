package obs;

public class ObserverImpl1 implements Observer {
    @Override
    public void update(int newState) {
        System.out.println("=====================================");
        System.out.println("New state: " + newState);
        System.out.println("=====================================");
    }
}
