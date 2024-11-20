package org.project.Functions;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public final class Calculations {
    private Calculations(){}

    public static String calculate(String expr) {
        System.out.println(expr);
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine jsEngine = manager
                    .getEngineByName("graal.js");

            return jsEngine.eval(expr).toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "!_ERROR_!";
        }
    }
    public static String calculateSQRT(String numExpr){
        double num = Double.parseDouble(numExpr);
        double sqrtOfNum = Math.sqrt(num);
        return String.valueOf(sqrtOfNum);
    }
}
