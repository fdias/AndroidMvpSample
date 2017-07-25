package com.diaxx.fernandods.androidfipe;

/**
 * Created by fernandods on 25/07/17.
 */

public class AppPreferences {

    private static AppPreferences instace;

    public static AppPreferences newInstace() {

        if (instace == null) {
            instace = new AppPreferences();
        }

        return instace;
    }

    public void toggleSaveFavorites(Marca marca) {

    }

}
