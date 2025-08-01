package by.kostya.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import java.io.Serializable;

@MappedSuperclass
public interface BaseEntity <T extends Serializable> {

    T getId();
    void setId(T id);

}
