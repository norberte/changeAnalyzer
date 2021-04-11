package com.project;

import java.util.Dictionary;

public class JavaStatementNode extends ProgrammingLanguage{
    public JavaStatementNode(String statement, ProgrammingLanguage parent) {
        super(statement, parent);
    }

    public boolean isComment() {
        if(this.statement.startsWith("//") || this.statement.startsWith("/*")){
            return true;
        } else {
            return false;
        }
    }

    public boolean isClass() {
        return false;
    }
    public boolean isInterface() {
        return false;
    }
    public boolean isFunction() {
        return false;
    }

    public boolean isDifferentClass(ProgrammingLanguage stmt) {
        return false;
    }
    public boolean isDifferentReturnType(ProgrammingLanguage stmt) {
        return false;
    }
    public boolean isDifferentInterface(ProgrammingLanguage stmt) {
        return false;
    }

    public Dictionary<String, String> getParams() {
        return null;
    }
}
