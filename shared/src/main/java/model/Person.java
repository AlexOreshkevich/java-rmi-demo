package model;

import java.io.Serializable;
import java.rmi.Remote;

public class Person extends Entity implements Serializable, Remote, PersonTO {

    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }
}
