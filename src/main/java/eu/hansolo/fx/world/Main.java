/*
 * Copyright (c) 2016 by Gerrit Grunwald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.hansolo.fx.world;

import eu.hansolo.fx.world.World.Resolution;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign.MaterialDesign;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Random;

import static eu.hansolo.fx.world.Country.*;
import static eu.hansolo.fx.world.Country.GB;
import static eu.hansolo.fx.world.Country.PL;


/**
 * User: hansolo
 * Date: 20.09.16
 * Time: 13:37
 */
public class Main extends Application {
    private static final Random        RND = new Random();
    private              World         world;
    private              CountryRegion europeanUnion;


    @Override public void init() {
        //europeanUnion = new CountryRegion("EU", BE, GR, LT, PT, BG, ES, LU, RO, CZ, FR, HU, SI, DK, HR, MT, SK, DE, IT, NL, FI, EE, CY, AT, SE, IE, LV, PL, GB);
        //europeanUnion.setColor(Color.LIGHTBLUE);
        //for (Country country : europeanUnion.getCountries()) {
        //    country.setColor(Color.rgb(RND.nextInt(127) + 100, 0, RND.nextInt(127) + 128));
        //}

        //BusinessRegion.EU.setColor(Color.rgb(124, 208, 255));
        //BusinessRegion.APAC.setColor(Color.LIGHTSALMON);
        world = WorldBuilder.create()
                            .resolution(Resolution.HI_RES)
                            //.backgroundColor(Color.web("#4aa9d7"))
                            //.fillColor(Color.web("#dcb36c"))
                            //.strokeColor(Color.web("#987028"))
                            //.hoverColor(Color.web("#fec47e"))
                            //.pressedColor(Color.web("#6cee85"))
                            //.locationColor(Color.web("#0000ff"))
                            //.selectedColor(Color.MAGENTA)
                            .zoomEnabled(true)
                            .selectionEnabled(true)
                            .build();
    }

    @Override public void start(Stage stage) {
        StackPane pane = new StackPane(world);
        pane.setBackground(new Background(new BackgroundFill(world.getBackgroundColor(), CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(pane);
        //scene.getStylesheets().add(Main.class.getResource("custom-styles.css").toExternalForm());

        stage.setTitle("World Map");
        stage.setScene(scene);
        stage.show();

        //world.zoomToCountry(Country.DE);
        //world.zoomToRegion(europeanUnion);
        world.zoomToRegion(BusinessRegion.EU);
    }

    @Override public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
