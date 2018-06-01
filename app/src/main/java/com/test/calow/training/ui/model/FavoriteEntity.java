package com.test.calow.training.ui.model;

import java.io.Serializable;

/**
 * Created by calow on 18/5/31.
 */

public class FavoriteEntity implements Serializable {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
