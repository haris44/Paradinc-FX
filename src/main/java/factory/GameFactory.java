package factory;

import controllers.ParadincRegionController;
import javafx.util.Pair;
import map.ParadincRegion;
import model.Game;
import model.Timeline;
import model.events.*;
import model.language.Attribute;
import model.language.Language;
import model.language.Platform;
import model.language.PlatformType;
import utils.PlatformArray;

import java.util.concurrent.ThreadLocalRandom;

import java.util.ArrayList;

public class GameFactory {


    public static Game createGame(Language language, Integer stars, String region){
        ArrayList<Event> event = new ArrayList<>();

        event.add(new Bug("Out Of Memory", 0, -10, -2, PlatformArray.getAllPlatform(), new Pair<>(20, 1.0), new Pair<>(0, 0.0)));
        event.add(new Bug("NullPointerException", 0, -10, -2, PlatformArray.getAllPlatform(), new Pair<>(10, 1.0), new Pair<>(0, 0.0)));
        event.add(new Bug("Dépendance Deprecated", 0, -10, -2,PlatformArray.getAllPlatform(), new Pair<>(48, 2.0), new Pair<>(0, 0.0)));
        event.add(new Bug("Array Out of Bound", 0, -10, -2, PlatformArray.getAllPlatform(), new Pair<>(10, 1.0), new Pair<>(0, 0.0)));
        event.add(new Bug("Lenteur de traitement", 0, -10, -2, PlatformArray.getAllPlatform(), new Pair<>(70, 1.0), new Pair<>(0, 0.0)));
        event.add(new Bug("Utilisation processeur intensive", 0, -10, -2, PlatformArray.getAllPlatform(), new Pair<>(30, 0.4), new Pair<>(0, 0.0)));

        event.add(new NewsPaper("Création d’une weekly", 30, 10, 5, new Pair<>(100, 1.0), new Pair<>(0,2.0)));
        event.add(new NewsPaper("Developpez.com publie un article élogieux sur la facilité de votre lanage",10,5,5, new Pair<>(5,4.0), new Pair<>(0,2.0)));
        event.add(new NewsPaper("Sortie d'un IDE JetBrains pour votre langage",100 ,30,40, new Pair<>(55,5.0), new Pair<>(35,5.0)));
        event.add(new NewsPaper("Extension Atom", 15, 5, 5, new Pair<>(25,5.0), new Pair<>(0,5.0)));
        event.add(new NewsPaper("Developpez.com qualifie votre langage de naufrage",0,-10,-10, new Pair<>(0,2.0), new Pair<>(0,5.0)));
        event.add(new NewsPaper("Une catastrophe aérienne survient a cause de votre lanage",0,-50,-60, new Pair<>(0,5.0), new Pair<>(80,4.0)));
        event.add(new NewsPaper("La NASA perd le contrôle d'un rover martien suite à un bug dans votre langage",0,-40,40, new Pair<>(0,0.0), new Pair<>(80,5.0)));

        event.add(new Incompatibility("Mise a jour du Kernel", 0, -20, -10, PlatformArray.getLinuxPlatform(), new Pair<>(30, 2.0)));
        event.add(new Incompatibility("Mise a jour de Windows", 0, -20, -10, PlatformArray.getWindowsPlateform(), new Pair<>(30, 2.0)));
        event.add(new Incompatibility("Mise a jour d'OSX", 0, -20, -10, PlatformArray.getUnixPlateform(), new Pair<>(30, 2.0)));
        event.add(new Incompatibility("Mise a jour de sécurité", 0, -20, -10, PlatformArray.getAllPlatform(), new Pair<>(50, 3.0)));

        event.add(new Conference("Présentation du language", 20, 20, 10, new Pair<>(20, 5.0), new Pair<>(10, 5.0), new Pair<>(5, 3.0)));
        event.add(new Conference("Présenter d'une MAJ", 30, 20, 10, new Pair<>(30, 3.0), new Pair<>(20, 10.0), new Pair<>(30, 3.0)));
        event.add(new Conference("Conférence dans une petite salle", 25, 20, 15, new Pair<>(20, 5.0), new Pair<>(20, 5.0), new Pair<>(15, 2.0)));
        event.add(new Conference("Conférence dans une moyenne salle", 50, 50, 20, new Pair<>(50, 5.0), new Pair<>(50, 3.0), new Pair<>(50, 2.0)));
        event.add(new Conference("Conférence dans une grande salle", 70, 50, 30, new Pair<>(80, 5.0), new Pair<>(80, 5.0), new Pair<>(80, 2.0)));
        event.add(new Conference("Conférence qui critique la facilité", 0, -70, -50, new Pair<>(80, 7.0), new Pair<>(10, 7.0), new Pair<>(40, 2.0)));
        event.add(new Conference("Conférence qui critique la fiabilité", 0, -70, -50, new Pair<>(10, 7.0), new Pair<>(80, 7.0), new Pair<>(40, 2.0)));

        event.add(new Evangelist("FG Oblige iAdvize à adopter votre langage", 0, 40, 10, new Pair<>(75, 5.0), new Pair<>(20, 4.0)));
        event.add(new Evangelist("Julien Tanguy parle de votre langage", 0, 30, 10, new Pair<>(25, 3.0), new Pair<>(20, 2.0)));
        event.add(new Evangelist("Steve Ballmer annonce travailler sur le support de votre langage", 0, -20, -10, new Pair<>(80, 5.0), new Pair<>(20, 4.0)));
        event.add(new Evangelist("Mathias Braux vend votre lanage auprès de ses étudiants",0,-40,-30, new Pair<>(100,4.0), new Pair<>(90,2.0)));

        event.add(new Tweet("Quentin Adam promeux votre langage",0,10,2, new Pair<>(25,5.0), new Pair<>(2,5.0), PlatformArray.getAllPlatform()));
        event.add(new Tweet("Votre tweet annonçant la nouvelle version atteiind les 200RT",0,6,1, new Pair<>(100,1.0), new Pair<>(100,1.0),PlatformArray.getAllPlatform()));
        event.add(new Tweet("De plus en plus de dev conçoivent des POC avec votre langage",0,15,5,new Pair<>(25,2.0), new Pair<>(25,3.0),PlatformArray.getAllPlatform()));
        event.add(new Tweet("Vimaire44 aime tout les tweets concernant votre lanage",0,4,1, new Pair<>(55,3.0), new Pair<>(15,7.0),PlatformArray.getAllPlatform()));
        event.add(new Tweet("Gcouprie créer une liste concernant votre lanage",0,7,3, new Pair<>(55,5.0), new Pair<>(75,4.0),PlatformArray.getAllPlatform()));
        event.add(new Tweet("Des tweets remettent en cause votre langage",0,-5,-4, new Pair<>(0,3.0), new Pair<>(10,3.0),PlatformArray.getAllPlatform()));
        event.add(new Tweet("Des utilisateurs créer un compte parodique pour votre langage",0,-6,-6,new Pair<>(15,6.0),new Pair<>(0,1.0)));
        


        ParadincRegionController regionController = ParadincRegionFactory.createRegion();

        // SET DEFAULT CONTAMINATION OF FIRST REGIONS HERE
        ParadincRegion europe = regionController.getRegions(region);
        europe.setContamination(30);

        return new Game(regionController, language, stars, event);
    }
}
