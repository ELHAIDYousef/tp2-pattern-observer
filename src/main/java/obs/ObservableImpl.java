package obs;

import java.util.ArrayList;
import java.util.List;

public class ObservableImpl implements Observable {

    private List<Observer> observers = new ArrayList<>();
    private int state;

    @Override
    public void subscribe(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void unsubscribe(Observer o) {
        this.observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers){
            o.update(state);
        }
    }

    public void setState(int state){
        this.state = state;
        this.notifyObservers();
    }

    public int getState(){
        return state;
    }


}
