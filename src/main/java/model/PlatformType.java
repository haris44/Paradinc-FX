package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Nathan on 08/12/2016.
 */
public enum PlatformType {
    Linux,
    Unix,
    Windows;

    public static ObservableList toObservable() {
        ObservableList obs = FXCollections.observableArrayList();
        for (PlatformType type: PlatformType.values()){
            obs.add(type);
        }
        return obs;
    }
}
