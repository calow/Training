package com.test.calow.training.ui.model;

import java.io.Serializable;

/**
 * Created by calow on 18/6/4.
 */

public class FavoriteItem implements Serializable{
    private String id;
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
