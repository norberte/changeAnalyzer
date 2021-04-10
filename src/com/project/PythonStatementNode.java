package com.project;

public class PythonStatementNode extends ProgrammingLanguage {
    public PythonStatementNode(String statement) {
        super(statement);
    }

    public boolean isComment() {
        if(this.statement.startsWith("#") || this.statement.startsWith("\"\"\"")){
            return true;
        } else {
            return false;
        }
    }


}
