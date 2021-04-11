package com.project;

import java.util.Dictionary;

public abstract class ProgrammingLanguage {
    protected String statement;
    protected ProgrammingLanguage parentStatement;

    public ProgrammingLanguage(String statement, ProgrammingLanguage parent){
        this.statement = statement;
        this.parentStatement = parent;
    }

    public boolean isEqual(ProgrammingLanguage node){
        if(this.statement.equals(node.statement)){
            return true;
        }
        return false;
    }

    public abstract boolean isComment();
    public abstract boolean isClass();
    public abstract boolean isInterface();
    public abstract boolean isFunction();
    public abstract boolean isDifferentClass(ProgrammingLanguage stmt);
    public abstract boolean isDifferentReturnType(ProgrammingLanguage stmt);
    public abstract boolean isDifferentInterface(ProgrammingLanguage stmt);
    public abstract Dictionary<String, String> getParams();

    public boolean isReturnStmt(){
        return statement.startsWith("return") && !this.isComment();
    }
}
