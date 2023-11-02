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

    BATZRA("בצרה"),

    BEER_SHEVA("באר-שבע"),

    BEIT_BELEL("בית-ברל"),

    BEIT_DAGAN("בית-דגן"),

    BEIT_SHEMESH("בית-שמש"),

    BNEY_BRAK("בני-ברק"),

    BNEY_TZION("בני-ציון"),

    DIMONA("דימונה"),

    ELAD("אלעד"),

    GANEI_TIQUA("גני תקווה"),

    GIVAT_SHMUEL("גבעת שמואל"),

    GIVATIM("גבעתיים"),

    HARUTZIM("חרוצים"),

    HEMED("חמד"),

    HERTZELIA("הרצליה"),

    HOD_HAHSRON("הוד-השרון"),

    HOLON("חולון"),

    JALGULYA("ג'לג'וליה"),

    JERUSALEM("ירושלים"),

    KALANSUA("קלנסואה"),

    KFAR_BARA("כפר-ברא"),

    KFAR_KASEM("כפר-קאסם"),

    KFAR_SABA("כפר-סבא"),

    KIRYAT_ONO("קריית-אונו"),

    KIRYAT_GAT("קריית-גת"),

    KIRTAT_MALAHI("קריית מלאכי"),

    LOD("לוד"),

    MAGSHIMIM("מגשימים"),

    MATAN("מתן"),

    MITZPE_RAMON("מצפה רמון"),

    MODIIN("מודיעין"),

    NATBAG("נתבג"),

    NETIVOT("נתיבות"),

    NETANYA("נתניה"),

    NIRIT("נירית"),

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

    SDEI_HEMED("שדי חמד"),

    SDEROT("שדרות"),

    SHOAM("שוהם"),

    TEL_AVIV("תל-אביב"),

    TIBE("טייבה"),

    TIRA("טירה"),

    TZFAT("צפת"),

    TZIFRIYA("צפריה"),

    YAHOD("יהוד"),

    YARHIV("ירחיב"),

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
