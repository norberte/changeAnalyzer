package com.project;

public class Main {

    public static void main(String[] args) {
        // compare node by node,
        // where a node is a code statement (ProgrammingLanguage instance)

        ProgrammingLanguage stmt1 = new PythonStatementNode("return 10");
        ProgrammingLanguage stmt2 = new PythonStatementNode("");
        
        if(stmt1.equals(stmt2)){
            System.out.println("No diff");
        } else {
            Change change = new ChangeClassifier(stmt1, stmt2).classify();
            System.out.println(change);
        }
    }
}
