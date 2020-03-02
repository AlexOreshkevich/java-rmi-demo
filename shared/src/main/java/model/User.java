package model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.rmi.Remote;

@EqualsAndHashCode(callSuper = true, exclude = {"name", "passwd"})
@ToString(callSuper = true)
public class User extends Entity implements Serializable, Remote, UserTO {

    private String name;
    private String passwd;

    public User() {
        super();
    }

    public User(final Long id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(final String passwd) {
        this.passwd = passwd;
    }
}
