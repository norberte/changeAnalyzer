package com.project;

public class PythonStatementNode extends ProgrammingLanguage {
    public PythonStatementNode(String statement) {
        super(statement);
    }

    boolean isReturnStmt() {
        return false;
    }
}
