package com.project;

public abstract class ProgrammingLanguage {
    protected String statement;
    public ProgrammingLanguage(String statement){
        this.statement = statement;
    }
    abstract boolean isReturnStmt();
}
