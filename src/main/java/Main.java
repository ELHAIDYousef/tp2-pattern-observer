import obs.*;

public class Main {
    public static void main(String[] args) {
        ObservableImpl observable = new ObservableImpl();

        Observer o1 = new ObserverImpl1();
        Observer o2 = new ObserverImpl2();

        observable.subscribe(o1);
        observable.subscribe(o2);

        observable.subscribe(newState -> {
            System.out.println("================= Observer Impl 3 ====================");
            System.out.println("Some calculation: " + newState + Math.random()*100);
            System.out.println("=====================================================");
        });

        observable.setState(60);
        observable.setState(70);
        observable.setState(80);
        observable.setState(80);




    }
}
