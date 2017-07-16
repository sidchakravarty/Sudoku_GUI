/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokuapp2;

/**
 *
 * @author Chakravarty
 */
public class History {
    private int intCount;
    private String strCell;
    private String strSelectedValue;
    private String strRemainingValues;
    private boolean blnUsable;

    public History(
            int intCount, 
            String strCell, 
            String strSelectedValue, 
            String strRemainingValues,
            boolean blnUsable) {
        
        this.intCount = intCount;
        this.strCell = strCell;
        this.strSelectedValue = strSelectedValue;
        this.strRemainingValues = strRemainingValues;
        this.blnUsable = blnUsable;
    }

    public int getIntCount() {return intCount;}

    public String getStrCell() {return strCell;}

    public String getStrSelectedValue() {return strSelectedValue;}

    public String getStrRemainingValues() {return strRemainingValues;}
    
    public boolean getUsable() {return blnUsable;}
}
