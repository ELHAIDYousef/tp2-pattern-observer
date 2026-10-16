package obs;

import java.util.ArrayList;
import java.util.List;

public class ObserverImpl2 implements Observer {
    private final List<Integer> history = new ArrayList<>();
    @Override
    public void update(Observable observable) {
        if (observable instanceof ObservableImpl obs){
            history.add(obs.getState());
            if (history.size() < 100) {
                double sum = 0;
                int i;
                for (i = 0; i < history.size(); i++) {
                    sum += history.get(i);
                }
                System.out.println("================= Observer Impl 2 ====================");
                System.out.println("The average is: " + sum / i);
                System.out.println("======================================================");
            }
        }
    }
}
