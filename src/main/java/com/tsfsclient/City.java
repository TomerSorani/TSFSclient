package com.tsfsclient;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum City {
    ARAD("ערד"),
    KFAR_SABA("כפר-סבא"),
    RAANANA("רעננה"),
    HERTZELIA("הרצליה"),
    HOD_HAHSRON("הוד-השרון"),
    RAMAT_HASHARON("רמת-השרון"),
    HOLON("חולון"),
    TEL_AVIV("תל-אביב"),
    BAT_YAM("בת-ים"),
    RAMAT_GAN("רמת-גן"),
    BNEY_BRAK("בני-ברק"),
    PETAH_TIQUA("פתח-תקווה"),
    ROSH_HAAYIN("ראש-העין"),
    ELAD("אלעד"),
    QUIRYAT_UNO("קריית-אונו"),
    REHOVOT("רחובות"),
    DIMONA("דימונה"),
    YEROCHAM("ירוחם"),
    MITZPE_RAMON("מצפה-רמון"),
    ODAKIM("אופקים"),
    SDEROT("שדרות"),
    NETIVOT("נתיבות"),
    KIRYAT_GAT("קריית-גת"),
    MODIIN("מודיעין"),
    ASHDOD("אשדוד"),
    JERUSALEM("ירושלים"),
    BEIT_SHEMESH("בית-שמש"),
    TIBE("טייבה"),
    TIRA("טירה"),
    KALANSUA("קלנסואה"),
    RISHON_LETZION("ראשון-לציון"),
    NETANYA("נתניה"),
    BEER_SHEVA("באר-שבע");

    private final String displayName;

    City(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static List<City> sort() {
        List<City> cities = Arrays.asList(City.values());
        cities.sort(Comparator.comparing(City::toString));
        return cities;
    }

    public static void sort(List<City> cities) {
        cities.sort(Comparator.comparing(City::toString));
    }
}
