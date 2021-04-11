package com.project;

import jdk.jshell.spi.ExecutionControl;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Dictionary;
import java.util.List;

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
        if( (!stmt1.isClass() && stmt2.isClass()) || (stmt1.isClass() && !stmt2.isClass()) ){
            return true;
        }
        return false;
    }

    private boolean isClassUpdate(){
        if (stmt1.isClass() && stmt2.isClass()){
            return stmt1.isDifferentClass(stmt2);
        }
        return false;
    }

    private boolean isInterfaceInsertDelete(){
        if( (!stmt1.isInterface() && stmt2.isInterface()) || (stmt1.isInterface() && !stmt2.isInterface()) ){
            return true;
        }
        return false;
    }

    private boolean isInterfaceUpdate(){
        if (stmt1.isInterface() && stmt2.isInterface()){
            return stmt1.isDifferentInterface(stmt2);
        }
        return false;
    }

    private boolean isParameterInsertDelete(){
        if (stmt1.isFunction() && stmt2.isFunction()){
            Dictionary<String, String> params1 = stmt1.getParams();
            Dictionary<String, String> params2 = stmt2.getParams();

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

    private boolean isParameterOrderingChange(){
        if (stmt1.isFunction() && stmt2.isFunction()){
            Dictionary<String, String> params1 = stmt1.getParams();
            Dictionary<String, String> params2 = stmt2.getParams();

            List<String> param_list1 = Collections.list(params1.keys());
            List<String> param_list2 = Collections.list(params2.keys());

            System.out.println(params1);
            System.out.println(params2);

            int min_list_size = Math.min(param_list1.size(), param_list2.size());

            for (int i = 0; i < min_list_size; i++) {
                if (!param_list1.get(i).equals(param_list2.get(i))){   // if param1 != param2, then order has changed
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isParameterTypeChange(){
        throw new UnsupportedOperationException("not implemented");
    }

    private boolean isParameterRenaming(){
        throw new UnsupportedOperationException("not implemented");
    }

    private boolean isReturnTypeInsertDelete(){
        if( (stmt1.isReturnStmt() && !stmt2.isReturnStmt()) || (!stmt1.isReturnStmt() && stmt2.isReturnStmt()) ){
            return true;
        }
        return false;
    }

    private boolean isReturnTypeUpdate(){
        if (stmt1.isReturnStmt() && stmt2.isReturnStmt()){
            return stmt1.isDifferentReturnType(stmt2);
        }
        return false;
    }

    /**
     * Helpers for BodyChange
     */
    private boolean isLoopConditionChange(){
        throw new UnsupportedOperationException("not implemented");
    }

    private boolean isControlStructureCondition(){
        throw new UnsupportedOperationException("not implemented");
    }

    private boolean isElsePartInsert(){
        throw new UnsupportedOperationException("not implemented");
    }

    private boolean isElsePartDelete(){
        throw new UnsupportedOperationException("not implemented");
    }

    private boolean isStatementInsertDelete(){
        throw new UnsupportedOperationException("not implemented");
    }

    private boolean isStatementOrderingChange(){
        throw new UnsupportedOperationException("not implemented");
    }

    private boolean isStatementParentChange(){
        throw new UnsupportedOperationException("not implemented");
    }

    private boolean isStatementUpdate(){
        throw new UnsupportedOperationException("not implemented");
    }

    private boolean isCommentInsertDelete(){
        throw new UnsupportedOperationException("not implemented");
    }

    private boolean isCommentUpdate(){
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
         */

        if(this.isClassInsertDelete()){
            return new DeclarationChange(ChangeTypeName.ClassInsertDelete, SignificanceLevel.Crucial);
        }

        if(this.isClassUpdate()){
            return new DeclarationChange(ChangeTypeName.ClassUpdate, SignificanceLevel.Crucial);
        }

        if(this.isInterfaceInsertDelete()){
            return new DeclarationChange(ChangeTypeName.InterfaceInsertDelete, SignificanceLevel.Crucial);
        }

        if(this.isInterfaceUpdate()){
            return new DeclarationChange(ChangeTypeName.InterfaceUpdate, SignificanceLevel.Crucial);
        }

        if(this.isParameterInsertDelete()){
            return new DeclarationChange(ChangeTypeName.ParameterInsertDelete, SignificanceLevel.Crucial);
        }

        if(this.isParameterOrderingChange()){
            return new DeclarationChange(ChangeTypeName.ParameterOrderingChange, SignificanceLevel.Crucial);
        }

        if(this.isReturnTypeUpdate()){
            return new DeclarationChange(ChangeTypeName.ReturnTypeUpdate, SignificanceLevel.Crucial);
        }

        if(this.isReturnTypeInsertDelete()){
            return new DeclarationChange(ChangeTypeName.ReturnTypeInsertDelete, SignificanceLevel.Crucial);
        }

        return new EmptyChange(ChangeTypeName.NoChange, SignificanceLevel.None);
    }

    public Change classify(){
        isParameterInsertDelete();
        return this.buildChangeObject();
    }
}
