package com.project;

public abstract class Change {
    protected SignificanceLevel significanceLevel;
    protected ChangeTypeName name;

    public Change(ChangeTypeName name, SignificanceLevel significanceLevel){
        this.name = name;
        this.significanceLevel = significanceLevel;
    }

    public String toString(){
        return "ChangeTypeName = " + this.name.toString() + "\t\t\t SignificanceLevel = " + this.significanceLevel.toString();
    }
}
