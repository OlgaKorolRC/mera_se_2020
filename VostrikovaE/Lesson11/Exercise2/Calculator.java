package VostrikovaE.Lesson11.Exercise2;

import java.beans.Expression;
import java.util.HashMap;
import java.util.Map;

public class Calculator {
        private final Map<String, Operation> operationMap=new HashMap<>();

    public void addOperation(String description, Operation action){
        operationMap.put(description, action);
    }

    public Double calc(String operation, Double val1, Double val2){
        Operation action = operationMap.get(operation);
        Double returnedResult=null;
        if (action!=null){
            returnedResult = action.doOperation(val1, val2);
        } else {
            throw new UnsupportedOperationException("Операция "+operation+" не поддерживается" );
        }
        return returnedResult;
    }

}
