package com.redsun.hrm.entities;

import java.util.Date;

/**
 * This class will become parent class of all others entity class,
 * Created by HauLL on 2/10/2017.
 */
public class AbstractEntity{

   	protected Integer id;
    protected Integer createdUser;
    protected Date createdDate;
    protected Integer modifierUser;
    protected Date modifierDate;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }
    
    public Integer getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(Integer createdUser) {
		this.createdUser = createdUser;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getModifierUser() {
		return modifierUser;
	}

	public void setModifierUser(Integer modifierUser) {
		this.modifierUser = modifierUser;
	}

	public Date getModifierDate() {
		return modifierDate;
	}

	public void setModifierDate(Date modifierDate) {
		this.modifierDate = modifierDate;
	}


}
