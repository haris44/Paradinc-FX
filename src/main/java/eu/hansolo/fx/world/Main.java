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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

import static eu.hansolo.fx.world.Country.*;

/**
 * User: hansolo
 * Date: 20.09.16
 * Time: 13:37
 */
public class Main extends Application {
    private static final Random        RND = new Random();
    private              World         world;
    private              CountryRegion europe;
    private              CountryRegion america;
    private              CountryRegion oceanie;
    private              CountryRegion africa;

    @Override public void init() {
        europe = new CountryRegion("EU", BE, GR, LT, PT, BG, ES, LU, RO, CZ, FR, HU, SI, DK, HR, MT, SK, DE, IT, NL, FI, EE, CY, AT, SE, IE, LV, PL, GB, BE, NL, LU, AT, CH, RU,IL, LI, LT, IS, IR);
        europe.setColor(Color.LIGHTGREEN);
        america = new CountryRegion("AMERICAS", AI,  GL, AG, AR, AW, BS, BB, BZ, BM, BO, BR, CA, KY, CL, CO, CR, CU, DM, DO, EC, SV, GF, GD, GP, GT, GY, HT, HN, JM, MQ, MX, MS, NI, PA, PY, PE, PR, BL, KN, LC, MF, PM, VC, SR, TT, TC, US, UY, VE, VG, VI);
        america.setColor(Color.LIGHTCYAN);
        oceanie = new CountryRegion("OCEANIE", AS, AU, BD, BN, BT, CC, CK, CN, CX, FJ, FM, GU, HK, ID, IN, IO, JP, KH, KI, KP, KR, LA, LK, MH, MM, MN, MO, MP, MV, MY, NC, NF, NP, NR, NU, NZ, PF, PG, PH, PK, PN, PW, SB, SG, TH, TK, TL, TO, TV, TW, VN, VU, WF, WS);
        oceanie.setColor(Color.LIGHTYELLOW);
        africa = new CountryRegion("EMEA", AF, AX, AL, DZ, AD, AO, AM, AT, AZ, BH, BY, BJ, BA, BW, BV, BF, BI, CM, CV, CF, TD, KM, CD, CG, DJ, GQ, ER,  FK, FO, FI, GA, GM, GE, GH, GI, GR, GG, GW, IQ, IE, IM, CI, JE, JO, KZ, KE, KV, KW, KG, LV, LB, LS, LR, LY, MK, MG, MW, ML, MT, MR, MU, YT, MD, MC, ME, MA, MZ, NA, NE, NG, NO, OM, PK, PS, QA, RE, RW, SH, SM, ST, SA, SN, RS, SC, SL, SK, SO, ZA, GS, SD, SJ, SZ, SE, SY, TJ, TZ, TG, TN, TR, TM, UG, UA, AE, UZ, VA, EH, YE, ZM, ZW);
        africa.setColor(Color.LIGHTSTEELBLUE);



        world = WorldBuilder.create()
                            .resolution(Resolution.HI_RES)
                            .zoomEnabled(true)
                            .selectionEnabled(true)
                            .build();
    }

    @Override public void start(Stage stage) {
        StackPane pane = new StackPane(world);
        pane.setBackground(new Background(new BackgroundFill(world.getBackgroundColor(), CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(pane);

        stage.setTitle("Parad'Inc");
        stage.setScene(scene);
        stage.show();
    }

    @Override public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
