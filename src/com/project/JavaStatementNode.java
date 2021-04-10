package com.project;

public class JavaStatementNode extends ProgrammingLanguage{
    public JavaStatementNode(String line) {
        super(line);
    }

    public boolean isComment() {
        if(this.statement.startsWith("//")){
            return true;
        } else if (this.statement.startsWith("/* */")){
            return true;
        } else {
            return false;
        }
    }
}
