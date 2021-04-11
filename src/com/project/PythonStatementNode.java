package com.project;

import java.util.Dictionary;
import java.util.Hashtable;

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
        if(this.statement.startsWith("class ")){
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

    private int countChar(String str, char ch){
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }

        return count;
    }

    public Dictionary<String, String> getParams(){
        Dictionary<String, String> params = new Hashtable<>();
        String paramName, paramType;

        if(this.isFunction()){

            int startIndex = this.statement.indexOf("(");
            int endIndex = this.statement.indexOf(")");

            String paramList_str = this.statement.substring(startIndex+1, endIndex);

            int paramSeparator = paramList_str.indexOf(",");

            if(paramSeparator == -1){   // only one parameter
                int paramEnd = paramList_str.indexOf(":");
                paramName = paramList_str.substring(0, paramEnd);

                paramType = paramList_str.substring(paramEnd+1);

                params.put(paramName.strip(), paramType.strip());
            } else {
               int numberOfParams = countChar(paramList_str, ',') + 1;

                for (int i = 0; i < numberOfParams; i++) {
                    int separator = paramList_str.indexOf(",");

                    if(separator == -1){
                        int paramEnd = paramList_str.indexOf(":");

                        paramName = paramList_str.substring(0, paramEnd);
                        paramType = paramList_str.substring(paramEnd+1);
                        params.put(paramName.strip(), paramType.strip());

                        break;
                    } else {
                        String current_param_str = paramList_str.substring(0, separator);

                        int paramEnd = current_param_str.indexOf(":");
                        paramName = current_param_str.substring(0, paramEnd);
                        paramType = current_param_str.substring(paramEnd + 1);
                        params.put(paramName.strip(), paramType.strip());
                        paramList_str = paramList_str.substring(separator + 1);
                    }
                }

            }
            return params;
        }
        return null;
    }
}
