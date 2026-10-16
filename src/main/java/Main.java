import obs.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        ObservableImpl observable = new ObservableImpl();

        // Keeps the observer objects we created, so we can unsubscribe the same instance later
        Map<Integer, Observer> subscribed = new HashMap<>();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Subscribe an observer   ==> 1");
            System.out.println("Unsubscribe an observer ==> 2");
            System.out.println("Change the state        ==> 3");
            System.out.println("Show subscribed         ==> 4");
            System.out.println("Close the app           ==> -1");
            System.out.print("Your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    int n = askObserver(sc);
                    if (n < 1 || n > 3) {
                        System.out.println("No observer " + n + ".");
                    } else if (subscribed.containsKey(n)) {
                        System.out.println("Observer " + n + " is already subscribed.");
                    } else {
                        Observer o = createObserver(n);
                        observable.subscribe(o);
                        subscribed.put(n, o);
                        System.out.println("Observer " + n + " subscribed.");
                    }
                }
                case 2 -> {
                    int n = askObserver(sc);
                    Observer o = subscribed.remove(n);
                    if (o == null) {
                        System.out.println("Observer " + n + " is not subscribed.");
                    } else {
                        observable.unsubscribe(o);
                        System.out.println("Observer " + n + " unsubscribed.");
                    }
                }
                case 3 -> {
                    System.out.print("New state: ");
                    int state = sc.nextInt();
                    observable.setState(state);   // notifies every subscribed observer
                }
                case 4 -> System.out.println("Subscribed observers: " + subscribed.keySet());
                case -1 -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static int askObserver(Scanner sc) {
        System.out.println("  Observer 1 ==> prints the new state");
        System.out.println("  Observer 2 ==> prints the average of all states");
        System.out.println("  Observer 3 ==> lambda, prints a calculation");
        System.out.print("Which observer: ");
        return sc.nextInt();
    }

    private static Observer createObserver(int n) throws Exception {
        // Observer 3 has no class: it's a lambda (Observer is a @FunctionalInterface)
        if (n == 3) {
            return obs -> {
                if (obs instanceof ObservableImpl o) {
                    System.out.println("================= Observer Impl 3 ====================");
                    System.out.println("Some calculation: " + (o.getState() + Math.random() * 100));
                    System.out.println("=====================================================");
                }
            };
        }
        // Observers 1 and 2 are classes: load them by name, like in the Strategy TP
        return (Observer) Class.forName("obs.ObserverImpl" + n)
                .getConstructor()
                .newInstance();
    }
}