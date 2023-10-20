package com.tsfsclient;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum City {
    ELSE(".אחר"),

    ARAD("ערד"),

    ARARA_DAROM("ערערה-דרום"),

    ASHDOD("אשדוד"),

    BAREKET("ברקת"),

    BAT_YAM("בת-ים"),

    BEER_SHEVA("באר-שבע"),

    BEIT_DAGAN("בית-דגן"),

    BEIT_SHEMESH("בית-שמש"),

    BNEY_BRAK("בני-ברק"),

    DIMONA("דימונה"),

    ELAD("אלעד"),

    GANEI_TIQUA("גני תקווה"),

    GIVAT_SHMUEL("גבעת שמואל"),

    GIVATIM("גבעתיים"),

    HEMED("חמד"),

    HERTZELIA("הרצליה"),

    HOD_HAHSRON("הוד-השרון"),

    HOLON("חולון"),

    JERUSALEM("ירושלים"),

    KALANSUA("קלנסואה"),

    KFAR_SABA("כפר-סבא"),

    KIRYAT_ONO("קריית-אונו"),

    KIRYAT_GAT("קריית-גת"),

    KIRTAT_MALAHI("קריית מלאכי"),

    LOD("לוד"),

    MAGSHIMIM("מגשימים"),

    MITZPE_RAMON("מצפה רמון"),

    MODIIN("מודיעין"),

    NATBAG("נתבג"),

    NETIVOT("נתיבות"),

    NETANYA("נתניה"),

    OFAKIM("אופקים"),

    OR_YAHODA("אור יהודה"),

    PETAH_TIQUA("פתח-תקווה"),

    RAMAT_GAN("רמת-גן"),

    RAMAT_HASHARON("רמת-השרון"),

    RAANANA("רעננה"),

    REHOVOT("רחובות"),

    RISHON_LETZION("ראשון-לציון"),

    ROSH_HAAYIN("ראש-העין"),

    SAVION("סביון"),

    SDEROT("שדרות"),

    SHOAM("שוהם"),

    TEL_AVIV("תל-אביב"),

    TIBE("טייבה"),

    TIRA("טירה"),

    TZFAT("צפת"),

    TZIFRIYA("צפריה"),

    YAHOD("יהוד"),

    YAVNE("יבנה"),

    YEROCHAM("ירוחם");

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
