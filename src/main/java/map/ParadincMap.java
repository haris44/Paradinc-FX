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
       this.europe = new CountryRegion("EU", AX, BE, GR, LT, PT, BG, ES, LU, RO, CZ, FR, HU,
                SI, DK, HR, MT, SK, DE, IT, NL, FI, EE, CY, AT, SE, IE, LV, PL, GB, BE, NL,
                LU, AT, CH, RU,IL, LI, LT, IS, SH,AL,AD,AM, AT,BY,BA,BV,GE, GI, GR,GG,IE,IM,JE,
                KG,LV,MK,MT,MD, MC,ME,TM,SE,NO, SK, SH, RS,GS,SJ,UA, VA,KZ,UZ,AF,TR,IR,AZ);
        this.europe.setColor(Color.LIGHTGREEN);

        this.america = new CountryRegion("AMERICAS", AI,  GL, AG, AR, AW, BS, BB, BZ, BM,
                BO, BR, CA, KY, CL, CO, CR, CU, DM, DO, EC, SV, GF, GD, GP, GT, GY, HT, HN,
                JM, MQ, MX, MS, NI, PA, PY, PE, PR, BL, KN, LC, MF, PM, VC, SR, TT, TC, US,
                UY, VE, VG, VI);
        this.america.setColor(Color.LIGHTCYAN);

        this.oceanie = new CountryRegion("OCEANIE", AS, AU, BD, BN, BT, CC, CK, CN, CX, FJ,
                FM, GU, HK, ID, IN, IO, JP, KH, KI, KP, KR, LA, LK, MH, MM, MN, MO, MP, MV,
                MY, NC, NF, NP, NR, NU, NZ, PF, PG, PH, PK, PN, PW, SB, SG, TH, TK, TL, TO,
                TV, TW, VN, VU, WF, WS,RE,SC,TJ);
        this.oceanie.setColor(Color.LIGHTYELLOW);

        this.africa = new CountryRegion("AFRICA",DZ,AO,BH,
                BJ,BW,BF,BI,CM,CV,CF,TD,KM,CD,CG,DJ,GQ,ER,FK,FO,
                GA,GM,GH,GW,IQ,CI,JO,KE,KV,KW,
                LB,LS,LR,LY,MG,MW,ML,MR,MU,YT,MA,MZ,NA,NE,NG,
                OM,PS,QA,RW,SM,ST,SA,SN,SL,SO,ZA,SD,
                SZ,SY,TZ,TG,TN,UG,AE,EH,YE,ZM,ZW,EG,GN,SS,ET);
        this.africa.setColor(Color.LIGHTSTEELBLUE);

        this.world = WorldBuilder.create()
                .resolution(World.Resolution.HI_RES)
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
