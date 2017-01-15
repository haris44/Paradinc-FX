package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Nathan on 07/12/2016.
 */
public enum ParadygmType {
    Fonctionel,
    Object,
    Imperatif;

   // @Override
    public static ObservableList toObservable() {
        ObservableList obs = FXCollections.observableArrayList();
        for (ParadygmType type: ParadygmType.values()){
            obs.add(type);
        }
        return obs;
    }
}
