package com.project;

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

    public boolean isReturnStmt(){
        return statement.contains("return") && !this.isComment();
    }
}
