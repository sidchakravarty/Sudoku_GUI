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
    private int intCounter;
    private String strCell;
    private int intSelectedValue;
    private int intCountRemaining;
    private String strRemainingValues;
    private String strUsedValues;
    private boolean blnUsable;

    public History () {
    }
    
    public History(
            int intCounter,
            String strCell, 
            int intSelectedValue, 
            int intCountRemaining,
            String strRemainingValues,
            String strUsedValues,
            boolean blnUsable) {
        
        this.intCounter = intCounter;
        this.strCell = strCell;
        this.intSelectedValue = intSelectedValue;
        this.intCountRemaining = intCountRemaining;
        this.strRemainingValues = strRemainingValues;
        this.strUsedValues = strUsedValues;
        this.blnUsable = blnUsable;
    }


    // Getters
    public void setIntCounter(int intCounter) {this.intCounter = intCounter;}

    public void setStrCell(String strCell) {this.strCell = strCell;}

    public void setIntSelectedValue(int intSelectedValue) {this.intSelectedValue = intSelectedValue;}

    public void setIntCountRemaining(int intCountRemaining) {this.intCountRemaining = intCountRemaining;}
    
    public void setStrRemainingValues(String strRemainingValues) {this.strRemainingValues = strRemainingValues;}

    public void setStrUsedValues(String strUsedValues) {this.strUsedValues = strUsedValues;}

    public void setBlnUsable(boolean blnUsable) {this.blnUsable = blnUsable;}
    
    // Setters
    public int getIntCounter() {return intCounter;}

    public boolean isBlnUsable() {return blnUsable;}

    public String getStrCell() {return strCell;}

    public int getIntSelectedValue() {return intSelectedValue;}

    public int getIntCountRemaining() {return intCountRemaining;}
    
    public String getStrRemainingValues() {return strRemainingValues;}
    
    public String getStrUsedValues() {return strUsedValues;}

    public boolean getUsable() {return blnUsable;}
    
}
