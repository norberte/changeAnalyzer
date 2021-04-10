package com.project;

public class CStatementNode extends ProgrammingLanguage {
    public CStatementNode(String statement) {
        super(statement);
    }

    public boolean isComment() {
       return false;
    }
}
