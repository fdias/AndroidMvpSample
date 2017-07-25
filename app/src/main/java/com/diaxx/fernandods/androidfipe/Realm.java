package com.diaxx.fernandods.androidfipe;

import java.util.List;

/**
 * Created by fernandods on 25/07/17.
 */

public class Realm {

    private static Realm instace;

    public static Realm newInstace() {

        if (instace == null) {
            instace = new Realm();
        }

        return instace;
    }

    public void saveCache(List<Marca> list) {

    }


}
