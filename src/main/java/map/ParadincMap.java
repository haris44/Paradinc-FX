package map;

import javafx.scene.paint.Color;

import static map.Country.*;

public class ParadincMap {

    private              World         world;
    private              CountryRegion europe;
    private              CountryRegion america;
    private              CountryRegion oceanie;
    private              CountryRegion africa;

    public ParadincMap() {
        this.europe = new CountryRegion("EU", BE, GR, LT, PT, BG, ES, LU, RO, CZ, FR, HU, SI, DK, HR, MT, SK, DE, IT, NL, FI, EE, CY, AT, SE, IE, LV, PL, GB, BE, NL, LU, AT, CH, RU,IL, LI, LT, IS, IR);
        this.europe.setColor(Color.LIGHTGREEN);
        this.america = new CountryRegion("AMERICAS", AI,  GL, AG, AR, AW, BS, BB, BZ, BM, BO, BR, CA, KY, CL, CO, CR, CU, DM, DO, EC, SV, GF, GD, GP, GT, GY, HT, HN, JM, MQ, MX, MS, NI, PA, PY, PE, PR, BL, KN, LC, MF, PM, VC, SR, TT, TC, US, UY, VE, VG, VI);
        this.america.setColor(Color.LIGHTCYAN);
        this.oceanie = new CountryRegion("OCEANIE", AS, AU, BD, BN, BT, CC, CK, CN, CX, FJ, FM, GU, HK, ID, IN, IO, JP, KH, KI, KP, KR, LA, LK, MH, MM, MN, MO, MP, MV, MY, NC, NF, NP, NR, NU, NZ, PF, PG, PH, PK, PN, PW, SB, SG, TH, TK, TL, TO, TV, TW, VN, VU, WF, WS);
        this.oceanie.setColor(Color.LIGHTYELLOW);
        this.africa = new CountryRegion("EMEA", AF, AX, AL, DZ, AD, AO, AM, AT, AZ, BH, BY, BJ, BA, BW, BV, BF, BI, CM, CV, CF, TD, KM, CD, CG, DJ, GQ, ER,  FK, FO, FI, GA, GM, GE, GH, GI, GR, GG, GW, IQ, IE, IM, CI, JE, JO, KZ, KE, KV, KW, KG, LV, LB, LS, LR, LY, MK, MG, MW, ML, MT, MR, MU, YT, MD, MC, ME, MA, MZ, NA, NE, NG, NO, OM, PK, PS, QA, RE, RW, SH, SM, ST, SA, SN, RS, SC, SL, SK, SO, ZA, GS, SD, SJ, SZ, SE, SY, TJ, TZ, TG, TN, TR, TM, UG, UA, AE, UZ, VA, EH, YE, ZM, ZW);
        this.africa.setColor(Color.LIGHTSTEELBLUE);

        this.world = WorldBuilder.create()
                .resolution(World.Resolution.HI_RES)
                .zoomEnabled(true)
                .selectionEnabled(true)
                .build();
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public CountryRegion getEurope() {
        return europe;
    }

    public void setEurope(CountryRegion europe) {
        this.europe = europe;
    }

    public CountryRegion getAmerica() {
        return america;
    }

    public void setAmerica(CountryRegion america) {
        this.america = america;
    }

    public CountryRegion getOceanie() {
        return oceanie;
    }

    public void setOceanie(CountryRegion oceanie) {
        this.oceanie = oceanie;
    }

    public CountryRegion getAfrica() {
        return africa;
    }

    public void setAfrica(CountryRegion africa) {
        this.africa = africa;
    }
}
