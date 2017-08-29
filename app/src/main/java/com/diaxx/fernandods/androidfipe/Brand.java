package com.diaxx.fernandods.androidfipe;

/**
 * Created by fernandods on 24/07/17.
 */

class Brand {
    /**
     * name : AUDI
     * fipe_name : Audi
     * order : 2
     * key : audi-6
     * id : 6
     */

    private String name;
    private String fipe_name;
    private int order;
    private String key;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFipe_name() {
        return fipe_name;
    }

    public void setFipe_name(String fipe_name) {
        this.fipe_name = fipe_name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
