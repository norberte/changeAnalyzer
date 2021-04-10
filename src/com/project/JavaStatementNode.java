package com.project;

public class JavaStatementNode extends ProgrammingLanguage{
    public JavaStatementNode(String line) {
        super(line);
    }

    boolean isReturnStmt() {
        return false;
    }
}
