package com.project;

import java.util.ArrayList;


public class PythonStatementNode extends ProgrammingLanguage {
    public PythonStatementNode(String statement, ProgrammingLanguage parent) {
        super(statement, parent);
    }

    public boolean isComment() {
        if(this.statement.startsWith("#") || this.statement.startsWith("\"\"\"")){
            return true;
        }
        return false;
    }

    public boolean isClass(){
        if(this.statement.startsWith("class ") && this.statement.endsWith(":") && ! this.isComment()){
            return true;
        }
        return false;
    }

    public boolean isDifferentReturnType(ProgrammingLanguage stmt) {
        String value1 = this.statement.replace("return ","");  // remove the keyword "return " from the string to get the value being returned
        String value2 = stmt.statement.replace("return ","");  // remove the keyword "return " from the string to get the val

        if(value1.equals(value2)){  // value from stmt1 equals value from stmt2, so no change
            return false;
        } else {
            return true;  // value was updated
        }
    }

    public boolean isDifferentClass(ProgrammingLanguage stmt){
        String className1 = this.statement.replace("class ","");  // remove the keyword "class " from the string to get the first classname
        String className2 = stmt.statement.replace("class ","");  // remove the keyword "class " from the string to get the second classname

        if(className1.equals(className2)){  // className from stmt1 equals className from stmt2, so no change
            return false;
        } else {
            return true;  // className was updated
        }
    }

    public boolean isInterface(){
        if(this.statement.startsWith("interface")){
            return true;
        }
        return false;
    }

    public boolean isDifferentInterface(ProgrammingLanguage stmt){
        String interfaceName1 = this.statement.replace("interface ","");  // remove the keyword "interface " from the string to get the first interfaceName
        String interfaceName2 = stmt.statement.replace("interface ","");  // remove the keyword "interface " from the string to get the second interfaceName

        if(interfaceName1.equals(interfaceName2)){  // interfaceName from stmt1 equals interfaceName from stmt2, so no change
            return false;
        } else {
            return true;  // interfaceName was updated
        }
    }

    public boolean isFunction(){
        if(this.statement.startsWith("def ")){
            return true;
        }
        return false;
    }

    /*
     *    Function definition syntax:
     *    def fctName(type1:param1, type2:param2) -> str:
     */
    public ArrayList<ArrayList<String>> getParams(){
        ArrayList<ArrayList<String>> params = new ArrayList<>();
        String paramName, paramType;

        if(!this.isFunction()) {
            //not a function, return empty arraylist of arraylists
            return params;
        }

        int startIndex = this.statement.indexOf("(");
        int endIndex = this.statement.indexOf(")");

        String paramList_str = this.statement.substring(startIndex+1, endIndex);
        if(!paramList_str.contains(":")){
            // zero params
            return params;
        }

        //one param
        if(!paramList_str.contains(",")){
            ArrayList<String> param = new ArrayList<>(){{
                add(paramList_str.split(":")[0]);
                add(paramList_str.split(":")[1]);
            }};
            params.add(param);
            return params;
        }

        //two or more params
        for (String param : paramList_str.split(",")) {
            params.add( new ArrayList<>(){{
                add(param.split(":")[0]);
                add(param.split(":")[1]);
            }});
        }
        return params;
    }
}
