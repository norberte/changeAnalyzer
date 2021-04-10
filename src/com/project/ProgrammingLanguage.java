package com.project;

public abstract class ProgrammingLanguage {
    protected String statement;

    public ProgrammingLanguage(String statement){
        this.statement = statement;
    }

    public abstract boolean isComment();

    public boolean isReturnStmt(){
        return statement.contains("return") && !this.isComment();
    }
}
