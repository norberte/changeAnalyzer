package com.project;

public class Main {

    public static void main(String[] args) {
        // compare node by node,
        // where a node is a code statement (ProgrammingLanguage instance)

        ProgrammingLanguage stmt1 = new PythonStatementNode("return x", new PythonStatementNode("x = 10", null));
        ProgrammingLanguage stmt2 = new PythonStatementNode("", null);
        
        if(stmt1.equals(stmt2)){
            System.out.println("No diff");
        } else {
            Change change = new ChangeClassifier(stmt1, stmt2).classify();
            System.out.println(change);
        }








        // Loop Condition
        ProgrammingLanguage stmt3 = new PythonStatementNode("for i in range(1,10):", null);
        ProgrammingLanguage stmt4 = new PythonStatementNode("for i in range(1,20):", null);

        // Control structure condition
        ProgrammingLanguage stmt5 = new PythonStatementNode("while i != 3:", null);
        ProgrammingLanguage stmt6 = new PythonStatementNode("if i != 3:", null);


        // Else-part insert
        ProgrammingLanguage stmt7 = new PythonStatementNode("if i == 2:", null);
        ProgrammingLanguage stmt8 = new PythonStatementNode("if i == 2: else:", null);

        // Else-part delete
        ProgrammingLanguage stmt9 = new PythonStatementNode("if i == 2: else:", null);
        ProgrammingLanguage stmt10 = new PythonStatementNode("if i == 2:", null);

        // Statement insert/delete
        ProgrammingLanguage stmt11 = new PythonStatementNode("", null);
        ProgrammingLanguage stmt12 = new PythonStatementNode("", null);

        // Statement ordering change
        ProgrammingLanguage stmt13 = new PythonStatementNode("x = 10", new PythonStatementNode("y = 20", null));
        ProgrammingLanguage stmt14 = new PythonStatementNode("y = 20", new PythonStatementNode("x = 10", null));

        // Statement Parent Change
        ProgrammingLanguage stmt15 = new PythonStatementNode("x = 10", new PythonStatementNode("z = 20", null));
        ProgrammingLanguage stmt16 = new PythonStatementNode("x = 10", new PythonStatementNode("y = 20", null));

        // Statement update
        ProgrammingLanguage stmt17 = new PythonStatementNode("", null);
        ProgrammingLanguage stmt18 = new PythonStatementNode("", null);

    }
}
