package com.project;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ChangeClassifierTest extends TestCase {
    private ChangeClassifier classifier;

    @Test
    public void testClassifyIsClassInsertPythonReturnsTrue() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("class A:", null);
        ProgrammingLanguage parentSmt2 = new PythonStatementNode("x=10", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("y=10", parentSmt2);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change expected = new DeclarationChange(ChangeTypeName.ClassInsertDelete, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertEquals(expected, actual);
    }
    @Test
    public void testClassifyIsClassDeletePythonReturnsTrue() {
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
        Change notExpected = new DeclarationChange(ChangeTypeName.ClassInsertDelete, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertNotSame(notExpected, actual);
    }
    @Test
    public void testClassifyIsClassInsertDeletePythonReturnsFalseMultilineCommentContainingClassKeyword(){
        ProgrammingLanguage stmt1 = new PythonStatementNode("\"\"\"class A: \n\tx=10\n\"\"\"", null);
        ProgrammingLanguage parentSmt2 = new PythonStatementNode("x=10", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("y=10", parentSmt2);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change notExpected = new DeclarationChange(ChangeTypeName.ClassInsertDelete, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertNotSame(notExpected, actual);
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
        Change notExpected = new DeclarationChange(ChangeTypeName.ClassUpdate, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertNotSame(notExpected, actual);
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
        Change notExpected = new DeclarationChange(ChangeTypeName.InterfaceInsertDelete, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertNotSame(notExpected, actual);
    }
    @Test
    public void testClassifyIsDeleteInterfacePythonReturnsFalse() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("interface A:", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("class A:", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change notExpected = new DeclarationChange(ChangeTypeName.InterfaceInsertDelete, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertNotSame(notExpected, actual);
    }


    @Test
    public void testClassifyIsParamInsertFromZeroToTwoParamsPythonReturnsTrue() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("def fct():", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("def fct(int:a, int:b):", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change notExpected = new DeclarationChange(ChangeTypeName.ParameterInsertDelete, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertNotSame(notExpected, actual);
    }
    @Test
    public void testClassifyIsParamInsertFromOneToTwoParamsPythonReturnsTrue() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("def fct(int:a):", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("def fct(int:a, int:b):", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change notExpected = new DeclarationChange(ChangeTypeName.ParameterInsertDelete, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertNotSame(notExpected, actual);
    }
    @Test
    public void testClassifyIsParamInsertFromOne2ToTwoParamsPythonReturnsTrue() {
        ProgrammingLanguage stmt1 = new PythonStatementNode("def fct(a):", null);
        ProgrammingLanguage stmt2 = new PythonStatementNode("def fct(a, b):", null);
        classifier = new ChangeClassifier(stmt1, stmt2);
        Change notExpected = new DeclarationChange(ChangeTypeName.ParameterInsertDelete, SignificanceLevel.Crucial);
        Change actual = classifier.classify();
        assertNotSame(notExpected, actual);
    }
}