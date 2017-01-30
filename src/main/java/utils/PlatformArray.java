package utils;

import model.language.Attribute;
import model.language.Platform;
import model.language.PlatformType;

import java.util.ArrayList;

/**
 * Created by Alexandre on 30/01/2017.
 */
public class PlatformArray {

    public static ArrayList<Attribute> getAllPlatform(){
        return new ArrayList<Attribute>(3){{
            add(Platform.fromPlatformType(PlatformType.Windows));
            add(Platform.fromPlatformType(PlatformType.Unix));
            add(Platform.fromPlatformType(PlatformType.Linux));
        }};
    }

    public static ArrayList<Attribute> getUnixPlateform(){
        return new ArrayList<Attribute>(1){{
            add(Platform.fromPlatformType(PlatformType.Unix));
        }};
    }

    public static ArrayList<Attribute> getLinuxPlatform(){
        return new ArrayList<Attribute>(1){{
            add(Platform.fromPlatformType(PlatformType.Linux));
        }};
    }

    public static ArrayList<Attribute> getLinuxAndUnixPlateform(){
        return new ArrayList<Attribute>(2){{
            add(Platform.fromPlatformType(PlatformType.Unix));
            add(Platform.fromPlatformType(PlatformType.Linux));
        }};
    }

    public static ArrayList<Attribute> getWindowsPlateform(){
        return new ArrayList<Attribute>(1){{
            add(Platform.fromPlatformType(PlatformType.Windows));
        }};
    }



}
