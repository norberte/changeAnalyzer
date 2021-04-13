package com.project;

public class Main {

    public static void main(String[] args) {

        System.out.println("Testing Return Type Insert/Delete");

        // compare node by node, where a node is a code statement (ProgrammingLanguage instance)
        ProgrammingLanguage stmt1 = new PythonStatementNode("return x", new PythonStatementNode("x = 10", null));
        ProgrammingLanguage stmt2 = new PythonStatementNode("", null);

        if(stmt1.equals(stmt2)){
            System.out.println("No diff");
        } else {
            Change change = new ChangeClassifier(stmt1, stmt2).classify();
            System.out.println(change);
        }





        System.out.println("\nTesting Return Type Update");

        ProgrammingLanguage stmt3 = new PythonStatementNode("return 10", null);
        ProgrammingLanguage stmt4 = new PythonStatementNode("return 20", null);

        if(stmt3.equals(stmt4)){
            System.out.println("No diff");
        } else {
            Change change = new ChangeClassifier(stmt3, stmt4).classify();
            System.out.println(change);
        }





        ProgrammingLanguage stmt5 = new PythonStatementNode("x = 10", null);
        ProgrammingLanguage stmt6 = new PythonStatementNode("class Employee:", null);

        if(stmt5.equals(stmt6)){
            System.out.println("No diff");
        } else {
            System.out.println("\nTesting Class Insert");
            Change change = new ChangeClassifier(stmt5, stmt6).classify();
            System.out.println(change);

            System.out.println("\nTesting Class Delete");
            change = new ChangeClassifier(stmt6, stmt5).classify();
            System.out.println(change);
        }




        System.out.println("\nTesting Class Update");

        ProgrammingLanguage stmt7 = new PythonStatementNode("class Emp:", null);
        ProgrammingLanguage stmt8 = new PythonStatementNode("class Employee:", null);

        if(stmt7.equals(stmt8)){
            System.out.println("No diff");
        } else {
            Change change = new ChangeClassifier(stmt7, stmt8).classify();
            System.out.println(change);
        }



        ProgrammingLanguage stmt9 = new PythonStatementNode("x = 10", null);
        ProgrammingLanguage stmt10 = new PythonStatementNode("interface EmployeeInterface", null);

        if(stmt9.equals(stmt10)){
            System.out.println("No diff");
        } else {
            System.out.println("\nTesting Interface Insert");
            Change change = new ChangeClassifier(stmt9, stmt10).classify();
            System.out.println(change);

            System.out.println("\nTesting Interface Delete");
            change = new ChangeClassifier(stmt10, stmt9).classify();
            System.out.println(change);
        }



        System.out.println("\nTesting Interface Update");

        ProgrammingLanguage stmt11 = new PythonStatementNode("interface EmpInterface:", null);
        ProgrammingLanguage stmt12 = new PythonStatementNode("interface EmployeeInterface:", null);

        if(stmt11.equals(stmt12)){
            System.out.println("No diff");
        } else {
            Change change = new ChangeClassifier(stmt11, stmt12).classify();
            System.out.println(change);
        }



        ProgrammingLanguage stmt13 = new PythonStatementNode("def my_function(fname: str)-> str:", null);
        ProgrammingLanguage stmt14 = new PythonStatementNode("def my_function(fname: str, lname: str)-> str:", null);

        if(stmt13.equals(stmt14)){
            System.out.println("No diff");
        } else {
            System.out.println("\nTesting Parameter Insert");
            Change change = new ChangeClassifier(stmt13, stmt14).classify();
            System.out.println(change);

            System.out.println("\nTesting Parameter Delete");
            change = new ChangeClassifier(stmt14, stmt13).classify();
            System.out.println(change);
        }




        System.out.println("\nTesting Parameter Ordering Change");

        ProgrammingLanguage stmt15 = new PythonStatementNode("def concat(fname: str, lname: str) -> str:", null);
        ProgrammingLanguage stmt16 = new PythonStatementNode("def concat(lname: str, fname: str) -> str:", null);

        if(stmt15.equals(stmt16)){
            System.out.println("No diff");
        } else {
            Change change = new ChangeClassifier(stmt15, stmt16).classify();
            System.out.println(change);
        }




        System.out.println("\nTesting Parameter Type Change");

        ProgrammingLanguage stmt17 = new PythonStatementNode("def concat(fname: str, lname: str) -> str:", null);
        ProgrammingLanguage stmt18 = new PythonStatementNode("def concat(fname: str, lname: list) -> str:", null);

        if(stmt17.equals(stmt18)){
            System.out.println("No diff");
        } else {
            Change change = new ChangeClassifier(stmt17, stmt18).classify();
            System.out.println(change);
        }





        System.out.println("\nTesting Parameter Renaming");

        ProgrammingLanguage stmt19 = new PythonStatementNode("def my_function(fname: str)-> str:", null);
        ProgrammingLanguage stmt20 = new PythonStatementNode("def my_function(lname: str)-> str:", null);

        if(stmt19.equals(stmt20)){
            System.out.println("No diff");
        } else {
            Change change = new ChangeClassifier(stmt19, stmt20).classify();
            System.out.println(change);
        }


        /*
        // Loop Condition
        System.out.println("\nTesting Loop Condition");

        ProgrammingLanguage stmt = new PythonStatementNode("for i in range(1,10):", null);
        ProgrammingLanguage stmt = new PythonStatementNode("for i in range(1,20):", null);

        // Control structure condition
        System.out.println("\nTesting Control structure condition");

        ProgrammingLanguage stmt = new PythonStatementNode("while i != 3:", null);
        ProgrammingLanguage stmt = new PythonStatementNode("if i != 3:", null);

        // Else-part insert
        ProgrammingLanguage stmt = new PythonStatementNode("if i == 2:", null);
        ProgrammingLanguage stmt = new PythonStatementNode("if i == 2: else:", null);

        // Else-part delete
        ProgrammingLanguage stmt = new PythonStatementNode("if i == 2: else:", null);
        ProgrammingLanguage stmt = new PythonStatementNode("if i == 2:", null);

        // Statement insert/delete
        ProgrammingLanguage stmt = new PythonStatementNode("", null);
        ProgrammingLanguage stmt = new PythonStatementNode("", null);

        // Statement ordering change
        ProgrammingLanguage stmt = new PythonStatementNode("x = 10", new PythonStatementNode("y = 20", null));
        ProgrammingLanguage stmt = new PythonStatementNode("y = 20", new PythonStatementNode("x = 10", null));

        // Statement Parent Change
        ProgrammingLanguage stmt = new PythonStatementNode("x = 10", new PythonStatementNode("z = 20", null));
        ProgrammingLanguage stmt = new PythonStatementNode("x = 10", new PythonStatementNode("y = 20", null));
        */
    }
}
