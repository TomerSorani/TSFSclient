package com.tsfsclient;

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
}
