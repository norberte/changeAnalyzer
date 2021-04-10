package com.project;

import jdk.jshell.spi.ExecutionControl;

public class ChangeClassifier {
    private ProgrammingLanguage stmt1;
    private ProgrammingLanguage stmt2;
    private Change change;

    public ChangeClassifier(ProgrammingLanguage stmt1, ProgrammingLanguage stmt2){
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
    }

    /**
     * Helpers for DeclarationChange
     */
    private boolean isClassInsertDelete(){
        throw new UnsupportedOperationException("class insert delete implementation missing");
    }

    private boolean isReturnTypeInsertDelete(){
        if( (stmt1.isReturnStmt() && !stmt2.isReturnStmt()) || (!stmt1.isReturnStmt() && stmt2.isReturnStmt()) ){
            return true;
        }
        return false;
    }

    /**
     * Helpers for BodyChange
     */
    /**
     * Helpers for ConditionChange
     */
    private boolean isLoopConditionChange(){
        throw new UnsupportedOperationException("loop condition change implementation missing");
    }

    private boolean isControlStructureCondition(){
        throw new UnsupportedOperationException("not implemented");
    }

    /**
     * Helpers for StatementsChange
     */
    private boolean isStatementChange(){
        throw new UnsupportedOperationException("not implemented");
    }

    /**
     * Helpers for CommentChange
     */
    private boolean isCommentChange(){
        throw new UnsupportedOperationException("not implemented");
    }

    private Change buildChangeObject(){
        /*
        if(this.isLoopConditionChange()) {
            return new BodyChange(ChangeTypeName.LoopCondition, SignificanceLevel.Medium);
        }

        if(this.isControlStructureCondition()){
            return new BodyChange(ChangeTypeName.ControlStructureCondition, SignificanceLevel.Medium);
        }

        if(this.isClassInsertDelete()){
            return new DeclarationChange(ChangeTypeName.ClassInsertDelete, SignificanceLevel.Crucial);
        }
        */

        if(this.isReturnTypeInsertDelete()){
            return new DeclarationChange(ChangeTypeName.ReturnTypeInsertDelete, SignificanceLevel.Crucial);
        }

       return new EmptyChange(ChangeTypeName.NoChange, SignificanceLevel.None);
    }

    public Change classify(){
        return this.buildChangeObject();
    }
}
