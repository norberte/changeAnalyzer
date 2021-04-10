package com.project;

public abstract class Change {
    protected SignificanceLevel significanceLevel;
    protected String name;
    public Change(String name, SignificanceLevel significanceLevel){
        this.name = name;
        this.significanceLevel = significanceLevel;
    }
}
