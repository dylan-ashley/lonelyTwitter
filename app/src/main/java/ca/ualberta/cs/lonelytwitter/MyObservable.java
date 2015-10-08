package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

/**
 * Created by dashley on 2015-10-07.
 */
public interface MyObservable {

    void addObserver(MyObserver o);

    void notifyObservers();
}
