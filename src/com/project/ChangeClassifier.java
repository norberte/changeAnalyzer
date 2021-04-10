package com.project;

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

    }

    /**
     * Helpers for BodyChange
     */
    /**
     * Helpers for ConditionChange
     */
    private boolean isLoopConditionChange(){

    }

    private boolean isControlStructureCondition(){

    }
    /**
     * Helpers for StatementsChange
     */
    private boolean isStatementChange(){

    }
    /**
     * Helpers for CommentChange
     */
    private boolean isCommentChange(){

    }

    private Change buildChangeObject(){
        if(this.isLoopConditionChange()) {
            return new BodyChange(ChangeTypeName.LoopCondition, SignificanceLevel.Medium);
        }
        if(this.isControlStructureCondition()){
            return new BodyChange(ChangeTypeName.ControlStructureCondition, SignificanceLevel.Medium);
        }
        if(this.isClassInsertDelete()){
            return new DeclarationChange(ChangeTypeName.ClassInsertDelete, SignificanceLevel.Crucial);
        }
    }
    public Change classify(){
        return this.buildChangeObject();
    }
}
