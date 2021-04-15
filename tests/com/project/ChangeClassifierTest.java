package com.project;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class ChangeClassifierTest extends TestCase {
    private ChangeClassifier classifier;

    @Test
    public void testClassifyIsClassDeletePythonReturnsTrue() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("class A:", null);
        ProgrammingLanguage parentSmt2 = new PythonStatementNode("x=10", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("y=10", parentSmt2);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change expected = new DeclarationChange(ChangeTypeName.ClassInsertDelete, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertEquals(expected, actual);
    }
    @Test
    public void testClassifyIsClassInsertPythonReturnsTrue() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("x=10", null);
        ProgrammingLanguage parentSmt2 = new PythonStatementNode("y=10", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("class A:", parentSmt2);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change expected = new DeclarationChange(ChangeTypeName.ClassInsertDelete, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertEquals(expected, actual);
    }
    @Test
    public void testClassifyIsClassInsertDeletePythonReturnsFalseCommentContainingClassKeyword(){
        ProgrammingLanguage stmt1 = new PythonStatementNode("# class A:", null);
        ProgrammingLanguage parentSmt2 = new PythonStatementNode("x=10", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("y=10", parentSmt2);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change classDeleteChange = new DeclarationChange(ChangeTypeName.ClassInsertDelete, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        // It is not class delete since it is a comment containing the class keyword
        assertNotEquals(classDeleteChange, actual);
    }
    @Test
    public void testClassifyIsClassInsertDeletePythonReturnsFalseMultilineCommentContainingClassKeyword(){
        ProgrammingLanguage stmt1 = new PythonStatementNode("\"\"\"class A: \n\tx=10\n\"\"\"", null);
        ProgrammingLanguage parentSmt2 = new PythonStatementNode("x=10", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("y=10", parentSmt2);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change classDelete = new DeclarationChange(ChangeTypeName.ClassInsertDelete, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertNotEquals(classDelete, actual);
    }

    @Test
    public void testClassifyIsClassUpdatePythonReturnsTrue() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("class A:", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("class B:", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change expected = new DeclarationChange(ChangeTypeName.ClassUpdate, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertEquals(expected, actual);
    }
    @Test
    public void testClassifyIsClassUpdatePythonReturnsFalse() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("class A:", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("class A:", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change expected = new NoChange(ChangeTypeName.NoChange, SignificanceLevel.None);
        Change actual = classifier.classify();
        assertEquals(expected, actual);
    }

    @Test
    public void testClassifyIsInsertInterfacePythonReturnsTrue() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("x=10", null);
        ProgrammingLanguage parentStmt2 = new PythonStatementNode("x=10", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("interface A:", parentStmt2);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change expected = new DeclarationChange(ChangeTypeName.InterfaceInsertDelete, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertEquals(expected, actual);
    }
    @Test
    public void testClassifyIsInsertInterfacePythonReturnsFalse() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("class A:", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("interface B:", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change interfaceInsert = new DeclarationChange(ChangeTypeName.InterfaceInsertDelete, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertNotEquals(interfaceInsert, actual);
    }

    @Test
    public void testClassifyIsInterfaceUpdatePythonReturnsTrue() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("interface A:", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("interface B:", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change expected = new DeclarationChange(ChangeTypeName.InterfaceUpdate, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertEquals(expected, actual);
    }

    @Test
    public void testClassifyIsParamInsertFromZeroToTwoParamsPythonReturnsTrue() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("def fct():", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("def fct(a:int, b:int):", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change expected = new DeclarationChange(ChangeTypeName.ParameterInsertDelete, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertEquals(expected, actual);
    }
    @Test
    public void testClassifyIsParamInsertFromOneToTwoParamsPythonReturnsTrue() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("def fct(a:int):", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("def fct(a:int, b:int):", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change paramInsert = new DeclarationChange(ChangeTypeName.ParameterInsertDelete, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertEquals(paramInsert, actual);
    }
    @Test
    public void testClassifyIsParamDeleteFromTwoToOneParamsPythonReturnsTrue() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("def fct(a:list, b:int):", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("def fct(a:list):", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change paramDelete = new DeclarationChange(ChangeTypeName.ParameterInsertDelete, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertEquals(paramDelete, actual);
    }


    @Test
    public void testClassifyIsParamOrderingChangePythonTwoIntParamsReturnsTrue() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("def fct(a:int, b:int)->list:", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("def fct(b:int, a:int)->list:", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change expected = new DeclarationChange(ChangeTypeName.ParameterOrderingChange, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertEquals(expected, actual);
    }
    @Test
    public void testClassifyIsParamOrderingChangePythonTwoStrParamsReturnsTrue() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("def concat(fname: str, lname: str) -> str:", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("def concat(lname: str, fname: str) -> str:", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change expected = new DeclarationChange(ChangeTypeName.ParameterOrderingChange, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertEquals(expected, actual);
    }
    @Test
    public void testClassifyIsParamOrderingChangePythonThreeParamsReturnsTrue() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("def fct(a:int, b:int, c:int) -> str:", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("def fct(a:int, c:int, b:int) -> str:", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change expected = new DeclarationChange(ChangeTypeName.ParameterOrderingChange, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertEquals(expected, actual);
    }
    @Test
    public void testClassifyIsParamOrderingChangePythonNoParamsReturnsFalse() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("def fct():", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("def fct():", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change expected = new NoChange(ChangeTypeName.NoChange, SignificanceLevel.None);
        Change actual = classifier.classify();
        assertEquals(expected, actual);
    }
    @Test
    public void testClassifyIsParamOrderingChangePythonThreeParamsSameNameDifferentTypesReturnsFalse() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("def fct(a:int, b:int, c:int):", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("def fct(a:int, c:str, b:int):", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change paramOrdering = new DeclarationChange(ChangeTypeName.ParameterOrderingChange, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertNotEquals(paramOrdering, actual);
    }
    @Test
    public void testClassifyIsParamOrderingChangePythonThreeParamsReturnsFalse() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("def fct(a:int, b:int, c:int):", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("def fct(a:int, d:int, b:int):", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change paramOrdering = new DeclarationChange(ChangeTypeName.ParameterOrderingChange, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertNotEquals(paramOrdering, actual);
    }

    @Test
    public void testClassifyIsParamTypeChangePythonOneParamReturnsTrue() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("def fct(a:int) -> str:", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("def fct(a:str) -> str:", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change expected = new DeclarationChange(ChangeTypeName.ParameterTypeChange, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertEquals(expected, actual);
    }
    @Test
    public void testClassifyIsParamTypeChangePythonTwoParamChangeOneReturnsTrue() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("def fct(b:int, a:int) -> int:", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("def fct(b:int, a:list) -> int:", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change expected = new DeclarationChange(ChangeTypeName.ParameterTypeChange, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertEquals(expected, actual);
    }
    @Test
    public void testClassifyIsParamTypeChangePythonTwoParamChangeTwoReturnsTrue() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("def fct(b:int, a:int)->int:", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("def fct(b:str, a:str)->int:", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change expected = new DeclarationChange(ChangeTypeName.ParameterTypeChange, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertEquals(expected, actual);
    }
    @Test
    public void testClassifyIsParamTypeChangePythonNoParamsReturnsFalse() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("def fct():", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("def fct():", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change noChange = new NoChange(ChangeTypeName.NoChange, SignificanceLevel.None);
        Change actual = classifier.classify();
        assertEquals(noChange, actual);
    }

    @Test
    public void testClassifyIsParamRenamingChangePythonOneParamReturnsTrue() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("def fct(a:int)->list:", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("def fct(ab:int)->list:", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change expected = new DeclarationChange(ChangeTypeName.ParameterRenaming, SignificanceLevel.Medium);
        Change actual = classifier.classify();
        assertEquals(expected, actual);
    }
    @Test
    public void testClassifyIsParamRenamingChangePythonTwoParamChangeOneReturnsTrue() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("def fct(a:int, b:int)->list:", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("def fct(a:int, bb:int)->list:", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change expected = new DeclarationChange(ChangeTypeName.ParameterRenaming, SignificanceLevel.Medium);
        Change actual = classifier.classify();
        assertEquals(expected, actual);
    }
    @Test
    public void testClassifyIsRenamingChangePythonTwoParamChangeTwoReturnsTrue() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("def fct(b:int, a:int):", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("def fct(bb:int, aa:int):", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change expected = new DeclarationChange(ChangeTypeName.ParameterRenaming, SignificanceLevel.Medium);
        Change actual = classifier.classify();
        assertEquals(expected, actual);
    }
    @Test
    public void testClassifyIsRenamingTypeChangePythonNoParamsReturnsFalse() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("def fct():", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("def fct():", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change expected = new NoChange(ChangeTypeName.NoChange, SignificanceLevel.None);
        Change actual = classifier.classify();
        assertEquals(expected, actual);
    }

    @Test
    public void testClassifyIsReturnTypeUpdateChangePythonReturnsTrue() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("return 13", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("return '13'", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change expected = new DeclarationChange(ChangeTypeName.ReturnTypeUpdate, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertEquals(expected, actual);
    }
    @Test
    public void testClassifyIsReturnTypeUpdateChangePythonReturnsFalse() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("return 13", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("return 13", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change expected = new NoChange(ChangeTypeName.NoChange, SignificanceLevel.None);
        Change actual = classifier.classify();
        assertEquals(expected, actual);
    }

    @Test
    public void testClassifyIsReturnTypeInsertChangePythonReturnsTrue() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("x=10", null);
        ProgrammingLanguage stmt2Parent = new PythonStatementNode("x=10", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("return 10", stmt2Parent);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change expected = new DeclarationChange(ChangeTypeName.ReturnTypeInsertDelete, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertEquals(expected, actual);
    }
    @Test
    public void testClassifyIsReturnTypeDeleteChangePythonReturnsTrue() {
        ProgrammingLanguage stmt1Parent = new PythonStatementNode("x=10", null);
        ProgrammingLanguage stmt1 = new PythonStatementNode("return 13", stmt1Parent);
        ProgrammingLanguage stmt2 = new PythonStatementNode("x=10", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change expected = new NoChange(ChangeTypeName.ReturnTypeInsertDelete, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertEquals(expected, actual);
    }

}