package sudokuapp2;

public class MinimumRV_Cell {

    private int intCount;
    private String strCell;
    private int intCurrentValue;
    private int intAlternateValue;
    private String strRemainingValues;
    private String strUsedValues;
    private int intCountRemaining;
    
    public MinimumRV_Cell () {}
    
    // Setters
    
    public void setIntCount(int intCount) {this.intCount = intCount;}

    public void setStrCell(String strCell) {this.strCell = strCell;}

    public void setIntCurrentValue(int intCurrentValue) {this.intCurrentValue = intCurrentValue;}    

    public void setIntAlternateValue(int intAlternateValue) {this.intAlternateValue = intAlternateValue;}    

    public void setStrRemainingValues(String strRemainingValues) {this.strRemainingValues = strRemainingValues;}    
    
    public void setStrUsedValues(String strUsedValues) {this.strUsedValues = strUsedValues;}

    public void setIntCountRemaining(int intCountRemaining) {this.intCountRemaining = intCountRemaining;}
    
    // Getters 
    
    public int getIntCount() {return intCount;}

    public String getStrCell() {return strCell;}

    public int getIntCurrentValue() {return intCurrentValue;}

    public int getIntAlternateValue() {return intAlternateValue;}

    public String getStrRemainingValues() {return strRemainingValues;}
    
    public String getStrUsedValues() {return strUsedValues;}

    public int getIntCountRemaining() {return intCountRemaining;}    
    
}
