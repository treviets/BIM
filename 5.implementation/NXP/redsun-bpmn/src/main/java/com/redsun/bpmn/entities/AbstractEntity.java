package com.redsun.bpmn.entities;

/**
 * This class will become parent class of all others entity class,
 * Created by HauLL on 2/10/2017.
 */
public class AbstractEntity{

    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }
}
