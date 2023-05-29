package com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.Models;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {

    Boolean isActive; //for delete

    public Boolean getActive() {

        return isActive;
    }

    public void setActive(Boolean active) {

        isActive = active;
    }
}
