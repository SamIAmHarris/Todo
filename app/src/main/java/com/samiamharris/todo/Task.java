package com.samiamharris.todo;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by samharris on 2/22/14.
 */

@ParseClassName("Task")
public class Task extends ParseObject {

    public Task(){
    }

    public ParseUser getUser() {
        return getParseUser("user");
    }

    public void setUser(ParseUser user) {
        put("user", user);
    }

    public boolean isCompleted() {
        return getBoolean("completed");
    }

    public void setCompleted(boolean complete) {
        put("completed", complete);
    }

    public String getDescription(){
        return getString("description");
    }

    public void setDescription(String description) {
        put("description", description);
    }



}
