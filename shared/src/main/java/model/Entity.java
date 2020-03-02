package model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.rmi.Remote;

@EqualsAndHashCode
@ToString
public class Entity implements Remote, Serializable {

    private Long id;

    public Entity() {
    }

    public Entity(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}
