package com.project;

import java.util.ArrayList;

public class ChangeClassifier {
    private ProgrammingLanguage stmt1;
    private ProgrammingLanguage stmt2;
    private Change change;

    public ChangeClassifier(ProgrammingLanguage stmt1, ProgrammingLanguage stmt2) {
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
    }

    /**
     * Helpers for DeclarationChange
     */
    private boolean isClassInsertDelete() {
        if ((!stmt1.isClass() && stmt2.isClass()) || (stmt1.isClass() && !stmt2.isClass())) {
            return true;
        }
        return false;
    }

    private boolean isClassUpdate() {
        if (stmt1.isClass() && stmt2.isClass()) {
            return stmt1.isDifferentClass(stmt2);
        }
        return false;
    }

    private boolean isInterfaceInsertDelete() {
        if ((!stmt1.isInterface() && stmt2.isInterface()) || (stmt1.isInterface() && !stmt2.isInterface())) {
            return true;
        }
        return false;
    }

    private boolean isInterfaceUpdate() {
        if (stmt1.isInterface() && stmt2.isInterface()) {
            return stmt1.isDifferentInterface(stmt2);
        }
        return false;
    }

    private boolean isParameterInsertDelete(){
        if (stmt1.isFunction() && stmt2.isFunction()){
            ArrayList<ArrayList<String>> params1 = stmt1.getParams();
            ArrayList<ArrayList<String>> params2 = stmt2.getParams();

            int paramList_size1 = params1.size();
            int paramList_size2 = params2.size();

            // insertion: paramList_size2 is larger than paramList_size1
            // Deletion: paramList_size1 is larger than paramList_size2
            if(paramList_size1 < paramList_size2 || paramList_size1 > paramList_size2){
                return true;
            }
        }
        return false;
    }

    private boolean isParameterOrderingChange() {
        if (stmt1.isFunction() && stmt2.isFunction()) {
            ArrayList<ArrayList<String>> params1 = stmt1.getParams();
            ArrayList<ArrayList<String>> params2 = stmt2.getParams();

            if (params1.size() != params2.size()) {
                return false;
            }
            for (int i = 0; i < params1.size(); i++) {
                // if paramName1 != paramName2
                // && param1 is still in the param2's list somewhere
                // && param2 is in params1
                if (!params1.get(i).get(0).equals(params2.get(i).get(0))
                        && params2.contains(params1.get(i))
                        && params1.contains(params2.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isParameterTypeChange() {
        if (stmt1.isFunction() && stmt2.isFunction()) {
            ArrayList<ArrayList<String>> params1 = stmt1.getParams();
            ArrayList<ArrayList<String>> params2 = stmt2.getParams();

            int min_param_num = Math.min(params1.size(), params2.size());

            for (int i = 0; i < min_param_num; i++) {
                // if the name of the params is the same, but the types are not the same, return true
                if (params1.get(i).get(0).equals(params2.get(i).get(0)) &&
                        !params1.get(i).get(1).equals(params2.get(i).get(1))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isParameterRenaming() {
        if (stmt1.isFunction() && stmt2.isFunction()) {
            ArrayList<ArrayList<String>> params1 = stmt1.getParams();
            ArrayList<ArrayList<String>> params2 = stmt2.getParams();

            int min_param_num = Math.min(params1.size(), params2.size());

            for (int i = 0; i < min_param_num; i++) {
                // if the type of a param is the same, but the name is not the same, return true
                if (params1.get(i).get(1).equals(params2.get(i).get(1)) &&
                        !params1.get(i).get(0).equals(params2.get(0).get(1))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isReturnTypeInsertDelete() {
        if ((stmt1.isReturnStmt() && !stmt2.isReturnStmt()) || (!stmt1.isReturnStmt() && stmt2.isReturnStmt())) {
            return true;
        }
        return false;
    }

    private boolean isReturnTypeUpdate() {
        if (stmt1.isReturnStmt() && stmt2.isReturnStmt()) {
            return stmt1.isDifferentReturnType(stmt2);
        }
        return false;
    }

    /**
     * Helpers for BodyChange
     */
    private boolean isLoopConditionChange() {
        throw new UnsupportedOperationException("not implemented");
    }

    private boolean isControlStructureCondition() {
        throw new UnsupportedOperationException("not implemented");
    }

    private boolean isElsePartInsert() {
        throw new UnsupportedOperationException("not implemented");
    }

    private boolean isElsePartDelete() {
        throw new UnsupportedOperationException("not implemented");
    }

    private boolean isStatementInsertDelete() {
        throw new UnsupportedOperationException("not implemented");
    }

    private boolean isStatementOrderingChange() {
        throw new UnsupportedOperationException("not implemented");
    }

    private boolean isStatementParentChange() {
        throw new UnsupportedOperationException("not implemented");
    }

    private boolean isStatementUpdate() {
        throw new UnsupportedOperationException("not implemented");
    }

    private boolean isCommentInsertDelete() {
        throw new UnsupportedOperationException("not implemented");
    }

    private boolean isCommentUpdate() {
        throw new UnsupportedOperationException("not implemented");
    }

    private Change buildChangeObject() {
        /*
        if(this.isLoopConditionChange()) {
            return new BodyChange(ChangeTypeName.LoopCondition, SignificanceLevel.Medium);
        }

        if(this.isControlStructureCondition()){
            return new BodyChange(ChangeTypeName.ControlStructureCondition, SignificanceLevel.Medium);
        }

        if(this.isElsePartInsert()){
            return new BodyChange(ChangeTypeName.ElsePartInsert, SignificanceLevel.Medium);
        }

        if(this.isElsePartDelete()){
            return new BodyChange(ChangeTypeName.ElsePartDelete, SignificanceLevel.Medium);
        }

        if(this.isStatementInsertDelete()){
            return new BodyChange(ChangeTypeName.StatementInsertDelete, SignificanceLevel.Low);
        }

        if(this.isStatementOrderingChange()){
            return new BodyChange(ChangeTypeName.StatementOrderingChange, SignificanceLevel.Low);
        }

        if(this.isStatementParentChange()){
            return new BodyChange(ChangeTypeName.StatementParentChange, SignificanceLevel.Medium);
        }

        if(this.isStatementUpdate()){
            return new BodyChange(ChangeTypeName.StatementUpdate, SignificanceLevel.Low);
        }

        if(this.isCommentInsertDelete()){
            return new BodyChange(ChangeTypeName.CommentInsertDelete, SignificanceLevel.None);
        }

        if(this.isCommentUpdate()){
            return new BodyChange(ChangeTypeName.CommentUpdate, SignificanceLevel.None);
        }
        */

        if (this.isClassInsertDelete()) {
            return new DeclarationChange(ChangeTypeName.ClassInsertDelete, SignificanceLevel.Crucial);
        }

        if (this.isClassUpdate()) {
            return new DeclarationChange(ChangeTypeName.ClassUpdate, SignificanceLevel.Crucial);
        }

        if (this.isInterfaceInsertDelete()) {
            return new DeclarationChange(ChangeTypeName.InterfaceInsertDelete, SignificanceLevel.Crucial);
        }

        if (this.isInterfaceUpdate()) {
            return new DeclarationChange(ChangeTypeName.InterfaceUpdate, SignificanceLevel.Crucial);
        }

        if (this.isParameterInsertDelete()) {
            return new DeclarationChange(ChangeTypeName.ParameterInsertDelete, SignificanceLevel.Crucial);
        }

        if (this.isParameterOrderingChange()) {
            return new DeclarationChange(ChangeTypeName.ParameterOrderingChange, SignificanceLevel.Crucial);
        }

        if (this.isParameterTypeChange()) {
            return new DeclarationChange(ChangeTypeName.ParameterTypeChange, SignificanceLevel.Crucial);
        }

        if (this.isParameterRenaming()) {
            return new DeclarationChange(ChangeTypeName.ParameterRenaming, SignificanceLevel.Medium);
        }

        if (this.isReturnTypeUpdate()) {
            return new DeclarationChange(ChangeTypeName.ReturnTypeUpdate, SignificanceLevel.Crucial);
        }

        if (this.isReturnTypeInsertDelete()) {
            return new DeclarationChange(ChangeTypeName.ReturnTypeInsertDelete, SignificanceLevel.Crucial);
        }

        return new NoChange(ChangeTypeName.NoChange, SignificanceLevel.None);
    }

    public Change classify() {
        return this.buildChangeObject();
    }
}
