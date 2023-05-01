/**
 * @name        Simple Java Calculator
 * @package     ph.calculator
 * @file        Main.java
 * @author      SORIA Pierre-Henry, expanded by Andrew Moy, Sinead Mckeon
 * @email       pierrehs@hotmail.com
 * @link        http://github.com/pH-7
 * @copyright   Copyright Pierre-Henry SORIA, All Rights Reserved.
 * @license     Apache (http://www.apache.org/licenses/LICENSE-2.0)
 */

package simplejavacalculator;

import static java.lang.Double.NaN;
import static java.lang.Math.log;
import static java.lang.Math.log10;
import static java.lang.Math.pow;
import static java.lang.Math.E;
import static java.lang.Math.PI;

public class Calculator {

    //extended operations from ysqrtx and on
    public enum BiOperatorModes {
        normal, add, minus, multiply, divide , xpowerofy, ysqrtx,mod,logyx
    }

    //extended operations from ex and on
    public enum MonoOperatorModes {
        square, squareRoot, oneDividedBy, cos, sin, tan, log, rate, abs, ln, ex, pi,x2, th_sqrt, fact, tenx, twox,e,neg,thirdSq
    }

    private Double num1, num2;
    private BiOperatorModes mode = BiOperatorModes.normal;

    private Double calculateBiImpl() {
        if (mode == BiOperatorModes.normal) {
            return num2;
        }
        if (mode == BiOperatorModes.add) {
            if (num2 != 0) {
                return num1 + num2;
            }

            return num1;
        }
        if (mode == BiOperatorModes.minus) {
            return num1 - num2;
        }
        if (mode == BiOperatorModes.multiply) {
            return num1 * num2;
        }
        if (mode == BiOperatorModes.divide) {
            return num1 / num2;
        }
        if (mode == BiOperatorModes.xpowerofy) {
            return pow(num1,num2);
        }
        
        //COMP 312 work begins
        //Calculates the nth root of x
        if (mode == BiOperatorModes.ysqrtx) {
            return Math.pow(num1, 1.0/num2);
        }
        
        //Calculates x in the nth base
        if (mode == BiOperatorModes.logyx) {
            return (log(num1)/log(num2));
        }
        
        /*Calculates the mod of two numbers
        * num1 MOD num2
        * Example: 15 MOD 8 = 7
        */
        if (mode == BiOperatorModes.mod) {
            return num1 % num2;
        }
        
        

        // never reach
        throw new Error();
    }

    public Double calculateBi(BiOperatorModes newMode, Double num) {
        if (mode == BiOperatorModes.normal) {
            num2 = 0.0;
            num1 = num;
            mode = newMode;
            return NaN;
        } else {
            num2 = num;
            num1 = calculateBiImpl();
            mode = newMode;
            return num1;
        }
    }

    public Double calculateEqual(Double num) {
        return calculateBi(BiOperatorModes.normal, num);
    }

    public Double reset() {
        num2 = 0.0;
        num1 = 0.0;
        mode = BiOperatorModes.normal;

        return NaN;
    }

    
    public Double calculateMono(MonoOperatorModes newMode, Double num) {
        if (newMode == MonoOperatorModes.square) {
            return num * num;
        }
        if (newMode == MonoOperatorModes.squareRoot) {
            return Math.sqrt(num);
        }
        
        //broken??
        if (newMode == MonoOperatorModes.oneDividedBy) {
            return 1 / num;
        }
        if (newMode == MonoOperatorModes.cos) {
            return Math.cos(Math.toRadians(num));
        }
        if (newMode == MonoOperatorModes.sin) {
            return Math.sin(Math.toRadians(num));
        }
        if (newMode == MonoOperatorModes.tan) {
            if (num == 0 || num % 180 == 0) {
                return 0.0;
            }
            if (num % 90 == 0 && num % 180 != 0) {
                return NaN;
            }

            return Math.tan(Math.toRadians(num));
        }
        if (newMode == MonoOperatorModes.log) {
            return log10(num);
        }
        if (newMode == MonoOperatorModes.ln) {
            return log(num);
        }
        if (newMode == MonoOperatorModes.rate) {
           return num / 100;
        }
        if (newMode == MonoOperatorModes.abs){
            return Math.abs(num);
        }
        
        // COMP 312 WORK BEGIN
        //Calculates e^x
        if (newMode == MonoOperatorModes.ex){
            return pow(Math.E,num) ;
        }
        
        //Returns e, erases anything that existed before
        if (newMode == MonoOperatorModes.e){
            return Math.E;
        }
        
        //duplicate square, not useful but kept in
        if (newMode == MonoOperatorModes.x2){
            return num * num;
        }
        
        ////Calculates the third root
        if (newMode == MonoOperatorModes.th_sqrt){
            return Math.pow(num, 1.0/3);
        }
        
        //Calculates the factorial
        if (newMode == MonoOperatorModes.fact){
            Double toReturn = num;
            Double temp = num;
            for(Double i=num;i>1;i--){
                temp--;
                toReturn = toReturn * temp;
            }
            return toReturn;
        }
        
        //Calculates 10^x
        if (newMode == MonoOperatorModes.tenx){
            return Math.pow(10, num);
        }
        
        //returns pi, removes anything that existed before
        if (newMode == MonoOperatorModes.pi){
            return Math.PI;
        }
        
        //Calculates 2^x
        if (newMode == MonoOperatorModes.twox){
            return Math.pow(2, num);
        }
        
        //switches the sign of the existing number. If 0, does nothing.
        if (newMode == MonoOperatorModes.neg){
            if(num >0 && num != 0){
                return -num;
            }else if(num < 0 && num != 0){
                return num;
            }
            
        }
        
        if (newMode == MonoOperatorModes.thirdSq){
            return pow(num,3) ;
        }
        

        // never reach
        throw new Error();
    }

}
