/*
    PROGRAM     : Sudoku App (using AI)
    PURPOSE     : This game is allows users to select the following three modes
                  to play Sudoku
                  a. Manual - No intelligence
                  b. Uninformed Search
                  c. Minimum Remaining Values
    PROGRAMMER  : Sid Chakravarty
    DATE        : 07.09.2017
    
    UNIVERSTIY  : University of Illinois (Springfield)
    COURSE      : CSC 479A - Introduction to Artificial Intelligence
*/
package sudokuapp2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 *
 * @author Chakravarty
 */
public class FXMLDocumentController implements Initializable {
    
    private boolean hasGameStarted;
    private List<TextField> target;
    private List<TextField> myCells;
    private Iterator<TextField> it;
    private String[] strCells = new String[81];
    private int[][] sudoku_grid = new int [9][9];
    private String strGameMode = "";
    private String[] strTargetCells = new String[27];
    private String strSourcePuzzleFile;
    private Service<Void> backgroundThread;
    private boolean blnFirstTimeStepMode = true;
    private int intStepModeCounter = 0;
    private final ObservableList<History> data = FXCollections.observableArrayList();
    
    // PANE
    @FXML private Pane mainPane; 
    @FXML private ComboBox<String> cmb_selectMethod;
    @FXML private Button btn_loadpuzzle;
    @FXML private ComboBox<String> cmb_showFiles;
    @FXML private Button btn_AutoPlay;
    @FXML private Button btn_SingleStep;
    @FXML private Button btn_Start;
    @FXML private Button btn_Stop;
    @FXML private Button btn_ShowOptions;
    @FXML private ListView<Integer> lst_options;
    @FXML private TableView<History> tbl_History;
    @FXML private final Label lbl_ShowResults;
    
    // ROW 1
    @FXML private TextField R1C1;
    @FXML private TextField R1C2;
    @FXML private TextField R1C3;
    @FXML private TextField R1C4;
    @FXML private TextField R1C5;
    @FXML private TextField R1C6;
    @FXML private TextField R1C7;
    @FXML private TextField R1C8;
    @FXML private TextField R1C9;

    // ROW 2
    @FXML private TextField R2C1;
    @FXML private TextField R2C2;
    @FXML private TextField R2C3;
    @FXML private TextField R2C4;
    @FXML private TextField R2C5;
    @FXML private TextField R2C6;
    @FXML private TextField R2C7;
    @FXML private TextField R2C8;
    @FXML private TextField R2C9;
    
    // ROW 3
    @FXML private TextField R3C1;
    @FXML private TextField R3C2;
    @FXML private TextField R3C3;
    @FXML private TextField R3C4;
    @FXML private TextField R3C5;
    @FXML private TextField R3C6;
    @FXML private TextField R3C7;
    @FXML private TextField R3C8;
    @FXML private TextField R3C9;
    
    // ROW 4
    @FXML private TextField R4C1;
    @FXML private TextField R4C2;
    @FXML private TextField R4C3;
    @FXML private TextField R4C4;
    @FXML private TextField R4C5;
    @FXML private TextField R4C6;
    @FXML private TextField R4C7;
    @FXML private TextField R4C8;
    @FXML private TextField R4C9;

    // ROW 5
    @FXML private TextField R5C1;
    @FXML private TextField R5C2;
    @FXML private TextField R5C3;
    @FXML private TextField R5C4;
    @FXML private TextField R5C5;
    @FXML private TextField R5C6;
    @FXML private TextField R5C7;
    @FXML private TextField R5C8;
    @FXML private TextField R5C9;    
    
    // ROW 6
    @FXML private TextField R6C1;
    @FXML private TextField R6C2;
    @FXML private TextField R6C3;
    @FXML private TextField R6C4;
    @FXML private TextField R6C5;
    @FXML private TextField R6C6;
    @FXML private TextField R6C7;
    @FXML private TextField R6C8;
    @FXML private TextField R6C9;    

    // ROW 7
    @FXML private TextField R7C1;
    @FXML private TextField R7C2;
    @FXML private TextField R7C3;
    @FXML private TextField R7C4;
    @FXML private TextField R7C5;
    @FXML private TextField R7C6;
    @FXML private TextField R7C7;
    @FXML private TextField R7C8;
    @FXML private TextField R7C9;        

    // ROW 8
    @FXML private TextField R8C1;
    @FXML private TextField R8C2;
    @FXML private TextField R8C3;
    @FXML private TextField R8C4;
    @FXML private TextField R8C5;
    @FXML private TextField R8C6;
    @FXML private TextField R8C7;
    @FXML private TextField R8C8;
    @FXML private TextField R8C9;          

    // ROW 9
    @FXML private TextField R9C1;
    @FXML private TextField R9C2;
    @FXML private TextField R9C3;
    @FXML private TextField R9C4;
    @FXML private TextField R9C5;
    @FXML private TextField R9C6;
    @FXML private TextField R9C7;
    @FXML private TextField R9C8;
    @FXML private TextField R9C9;       

    public FXMLDocumentController() {
        this.lbl_ShowResults = new Label();
    }
    
    @FXML private void solvePuzzle_SingleStep (Event e) {
        try {
            strGameMode = cmb_selectMethod.getSelectionModel().getSelectedItem();        
            switch (strGameMode) {
                case "Manual":
                    JOptionPane.showMessageDialog(null, "To play game manually, "
                            + "use the mouse to select each cell and then enter "
                            + "one of the recommended values", "Initiate Game", 
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "Uninformed Search":
                    initiate_StepMode("Uninformed Search");
                    break;
                case "Minimum Remaining Values": 
                    initiate_StepMode("Minimum Remaining Values");
                    break;
            }
        } catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(null, "Please select a Game Mode.", "Initiate Game", JOptionPane.INFORMATION_MESSAGE);  
                cmb_selectMethod.requestFocus();
                System.err.println("Error: " + npe.getMessage());
        }
    }

    private void initiate_StepMode(String strMode) {
        
        if(blnFirstTimeStepMode) {
            blnFirstTimeStepMode = false;
            myCells = getNodesOfType(mainPane, TextField.class);
            it = myCells.iterator();   
            int intLocalCount = 0;
            while(it.hasNext()) {
                strCells[intLocalCount] = it.next().getId();
                intLocalCount++;
            }
        }             
        switch (strMode){
            case "Uninformed Search":
                String strStyle;
                target = getNodesOfType(mainPane, TextField.class);
                for (TextField t : target) {
                    strStyle = t.getStyle();
                    if(t.getId().contains(strCells[intStepModeCounter])) {
                        if(!strStyle.contains("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;")) {
                              play_mode_uninformed_search(strCells[intStepModeCounter]);
                              try {
                                  int intValue = lst_options.getItems().get(0);
                                  addValue(intValue, strCells[intStepModeCounter]);
                                  addToTableView(intValue, strCells[intStepModeCounter], intStepModeCounter);
                                  break;
                              } catch (Exception e) {
                                  System.err.println("Error: " + e.getMessage());
                                  break;
                              }                        
                          }
                    }                       
                }
                
                intStepModeCounter++;
                break;
                
            case "Minimum Remaining Values":
                play_mode_mrv();
                break;
        }
    }
    
    private void play_mode_uninformed_search(String strCell) {
        String strStyle;        
        if(strCell.length()>0) {
            resetHighlights();
            strTargetCells = createHighlightArray(strCell);
            target = getNodesOfType(mainPane, TextField.class);
            for(TextField t : target) {
                strStyle = t.getStyle();
                System.out.println("Cell Style: " + t.getId() + " - " + t.getText() +" - " + strStyle);
                if(!strStyle.contains("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;")) {
                    for(int i = 0; i < strTargetCells.length; i++) {
                        if(strTargetCells[i] != null) {
                            if(t.getId().contains(strTargetCells[i])) {
                                t.setStyle("-fx-background-color: RGB(162,100,100); -fx-opacity: 0.7; -fx-border-color: silver");
                                break;
                            }                                    
                        }
                    }                        
                }
            }
            populateOptions();
        }       
    }
    
    private void play_mode_mrv() {
        
    }

    private void addValue(int intValue, String strCell) {
        switch (strCell) {
            // Row 1
            case "R1C1":
                R1C1.setText(Integer.toString(intValue));
                break;
            case "R1C2":
                R1C2.setText(Integer.toString(intValue));
                break;
            case "R1C3":
                R1C3.setText(Integer.toString(intValue));
                break;
            case "R1C4":
                R1C4.setText(Integer.toString(intValue));
                break;
            case "R1C5":
                R1C5.setText(Integer.toString(intValue));
                break;
            case "R1C6":
                R1C6.setText(Integer.toString(intValue));
                break;
            case "R1C7":
                R1C7.setText(Integer.toString(intValue));
                break;
            case "R1C8":
                R1C8.setText(Integer.toString(intValue));
                break;
            case "R1C9":
                R1C9.setText(Integer.toString(intValue));
                break;

            // Row 2
            case "R2C1":
                R2C1.setText(Integer.toString(intValue));
                break;
            case "R2C2":
                R2C2.setText(Integer.toString(intValue));
                break;
            case "R2C3":
                R2C3.setText(Integer.toString(intValue));
                break;
            case "R2C4":
                R2C4.setText(Integer.toString(intValue));
                break;
            case "R2C5":
                R2C5.setText(Integer.toString(intValue));
                break;
            case "R2C6":
                R2C6.setText(Integer.toString(intValue));
                break;
            case "R2C7":
                R2C7.setText(Integer.toString(intValue));
                break;
            case "R2C8":
                R2C8.setText(Integer.toString(intValue));
                break;
            case "R2C9":
                R2C9.setText(Integer.toString(intValue));
                break;

            // Row 3                
            case "R3C1":
                R3C1.setText(Integer.toString(intValue));
                break;
            case "R3C2":
                R3C2.setText(Integer.toString(intValue));
                break;
            case "R3C3":
                R3C3.setText(Integer.toString(intValue));
                break;
            case "R3C4":
                R3C4.setText(Integer.toString(intValue));
                break;
            case "R3C5":
                R3C5.setText(Integer.toString(intValue));
                break;
            case "R3C6":
                R3C6.setText(Integer.toString(intValue));
                break;
            case "R3C7":
                R3C7.setText(Integer.toString(intValue));
                break;
            case "R3C8":
                R3C8.setText(Integer.toString(intValue));
                break;
            case "R3C9":
                R3C9.setText(Integer.toString(intValue));
                break;

            // Row 4                
            case "R4C1":
                R4C1.setText(Integer.toString(intValue));
                break;
            case "R4C2":
                R4C2.setText(Integer.toString(intValue));
                break;
            case "R4C3":
                R4C3.setText(Integer.toString(intValue));
                break;
            case "R4C4":
                R4C4.setText(Integer.toString(intValue));
                break;
            case "R4C5":
                R4C5.setText(Integer.toString(intValue));
                break;
            case "R4C6":
                R4C6.setText(Integer.toString(intValue));
                break;
            case "R4C7":
                R4C7.setText(Integer.toString(intValue));
                break;
            case "R4C8":
                R4C8.setText(Integer.toString(intValue));
                break;
            case "R4C9":
                R4C9.setText(Integer.toString(intValue));
                break;

            // Row 5                
            case "R5C1":
                R5C1.setText(Integer.toString(intValue));
                break;
            case "R5C2":
                R5C2.setText(Integer.toString(intValue));
                break;
            case "R5C3":
                R5C3.setText(Integer.toString(intValue));
                break;
            case "R5C4":
                R5C4.setText(Integer.toString(intValue));
                break;
            case "R5C5":
                R5C5.setText(Integer.toString(intValue));
                break;
            case "R5C6":
                R5C6.setText(Integer.toString(intValue));
                break;
            case "R5C7":
                R5C7.setText(Integer.toString(intValue));
                break;
            case "R5C8":
                R5C8.setText(Integer.toString(intValue));
                break;
            case "R5C9":
                R5C9.setText(Integer.toString(intValue));
                break;

            // Row 6
            case "R6C1":
                R6C1.setText(Integer.toString(intValue));
                break;
            case "R6C2":
                R6C2.setText(Integer.toString(intValue));
                break;
            case "R6C3":
                R6C3.setText(Integer.toString(intValue));
                break;
            case "R6C4":
                R6C4.setText(Integer.toString(intValue));
                break;
            case "R6C5":
                R6C5.setText(Integer.toString(intValue));
                break;
            case "R6C6":
                R6C6.setText(Integer.toString(intValue));
                break;
            case "R6C7":
                R6C7.setText(Integer.toString(intValue));
                break;
            case "R6C8":
                R6C8.setText(Integer.toString(intValue));
                break;
            case "R6C9":
                R6C9.setText(Integer.toString(intValue));
                break;

            // Row 7                
            case "R7C1":
                R7C1.setText(Integer.toString(intValue));
                break;
            case "R7C2":
                R7C2.setText(Integer.toString(intValue));
                break;
            case "R7C3":
                R7C3.setText(Integer.toString(intValue));
                break;
            case "R7C4":
                R7C4.setText(Integer.toString(intValue));
                break;
            case "R7C5":
                R7C5.setText(Integer.toString(intValue));
                break;
            case "R7C6":
                R7C6.setText(Integer.toString(intValue));
                break;
            case "R7C7":
                R7C7.setText(Integer.toString(intValue));
                break;
            case "R7C8":
                R7C8.setText(Integer.toString(intValue));
                break;
            case "R7C9":
                R7C9.setText(Integer.toString(intValue));
                break;

            // Row 8                
            case "R8C1":
                R8C1.setText(Integer.toString(intValue));
                break;
            case "R8C2":
                R8C2.setText(Integer.toString(intValue));
                break;
            case "R8C3":
                R8C3.setText(Integer.toString(intValue));
                break;
            case "R8C4":
                R8C4.setText(Integer.toString(intValue));
                break;
            case "R8C5":
                R8C5.setText(Integer.toString(intValue));
                break;
            case "R8C6":
                R8C6.setText(Integer.toString(intValue));
                break;
            case "R8C7":
                R8C7.setText(Integer.toString(intValue));
                break;
            case "R8C8":
                R8C8.setText(Integer.toString(intValue));
                break;
            case "R8C9":
                R8C9.setText(Integer.toString(intValue));
                break;

            // Row 9                
            case "R9C1":
                R9C1.setText(Integer.toString(intValue));
                break;
            case "R9C2":
                R9C2.setText(Integer.toString(intValue));
                break;
            case "R9C3":
                R9C3.setText(Integer.toString(intValue));
                break;
            case "R9C4":
                R9C4.setText(Integer.toString(intValue));
                break;
            case "R9C5":
                R9C5.setText(Integer.toString(intValue));
                break;
            case "R9C6":
                R9C6.setText(Integer.toString(intValue));
                break;
            case "R9C7":
                R9C7.setText(Integer.toString(intValue));
                break;
            case "R9C8":
                R9C8.setText(Integer.toString(intValue));
                break;
            case "R9C9":
                R9C9.setText(Integer.toString(intValue));
                break;
        }
    }    
    
    // JavaFX Concurrence Error
    @FXML private void solvePuzzle_AutoMode (Event e) throws InterruptedException {
 
            myCells = getNodesOfType(mainPane, TextField.class);
            it = myCells.iterator();
            backgroundThread = new Service<Void>() {

            @Override
            protected Task<Void> createTask() {
                
                return new Task<Void>() {

                    @Override
                    protected Void call() throws Exception {
                        String strCellID;
                        String strStyle;
                        while (it.hasNext()) {
                            strCellID = it.next().getId();
                            if(strCellID.length()>0) {
                                resetHighlights();
                                strTargetCells = createHighlightArray(strCellID);
                                target = getNodesOfType(mainPane, TextField.class);
                                for(TextField t : target) {
                                    strStyle = t.getStyle();
                                    System.out.println("Cell Style: " + t.getId() + " - " + t.getText() +" - " + strStyle);
                                    if(!strStyle.contains("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;")) {
                                        for(int i = 0; i < strTargetCells.length; i++) {
                                            if(strTargetCells[i] != null) {
                                                if(t.getId().contains(strTargetCells[i])) {
                                                    t.setStyle("-fx-background-color: RGB(162,100,100); -fx-opacity: 0.7; -fx-border-color: silver");
                                                    break;
                                                }                                    
                                            }
                                        }                        
                                    }
                                }
                                populateOptions();
                                //System.out.println("Cell ID: " + strCellID);                
                            }
                        }                         
                        return null;
                    }
                };
            }
        };
            
            backgroundThread.setOnSucceeded((WorkerStateEvent event) -> {
                System.out.println("Done!");
            });
            
            backgroundThread.restart();
    }

    private String[] createHighlightArray(String strControlName) {
        String[] strTargetCells = new String[27];
        String strC = strControlName.substring(2);
        String strR = strControlName.substring(0, 2);
        int r = Integer.parseInt(strR.substring(1));
        int c = Integer.parseInt(strC.substring(1));

        // For Rows 
        int counter = 0;
        for (int i=1; i < 10; i++) {
            strTargetCells[counter] = strR + "C" + i;
            counter++;
        }

        // For Cols
        for (int i=1; i < 10; i++) {
            strTargetCells[counter] = "R" + i + strC;
            counter++;
        }            

        // For Block

        // TOP LEFT
        if(r >= 1 && r <= 3) {
            if (c >= 1 && c <= 3) {
                for (int i = 1; i <=3; i++) {
                    for (int j = 1; j <= 3; j++) {
                        strTargetCells[counter] = "R" + i + "C" + j;
                        counter++;
                    }
                }
            }
        }

        // TOP CENTER
        if(r >= 1 && r <= 3) {
            if (c >= 4 && c <= 6) {
                for (int i = 1; i <=3; i++) {
                    for (int j = 4; j <= 6; j++) {
                        strTargetCells[counter] = "R" + i + "C" + j;
                        counter++;
                    }
                }
            }
        }            

        // TOP RIGHT
        if(r >= 1 && r <= 3) {
            if (c >= 7 && c <= 9) {
                for (int i = 1; i <=3; i++) {
                    for (int j = 7; j <= 9; j++) {
                        strTargetCells[counter] = "R" + i + "C" + j;
                        counter++;
                    }
                }
            }
        }               

        // MIDDLE LEFT
        if(r >= 4 && r <= 6) {
            if (c >= 1 && c <= 3) {
                for (int i = 4; i <= 6; i++) {
                    for (int j = 1; j <= 3; j++) {
                        strTargetCells[counter] = "R" + i + "C" + j;
                        counter++;
                    }
                }
            }
        }

        // MIDDLE CENTER
        if(r >= 4 && r <= 6) {
            if (c >= 4 && c <= 6) {
                for (int i = 4; i <=6; i++) {
                    for (int j = 4; j <= 6; j++) {
                        strTargetCells[counter] = "R" + i + "C" + j;
                        counter++;
                    }
                }
            }
        }            

        // MIDDLE RIGHT
        if(r >= 4 && r <= 6) {
            if (c >= 7 && c <= 9) {
                for (int i = 4; i <=6; i++) {
                    for (int j = 7; j <= 9; j++) {
                        strTargetCells[counter] = "R" + i + "C" + j;
                        counter++;
                    }
                }
            }
        }              

        // BOTTOM LEFT
        if(r >= 7 && r <= 9) {
            if (c >= 1 && c <= 3) {
                for (int i = 7; i <= 9; i++) {
                    for (int j = 1; j <= 3; j++) {
                        strTargetCells[counter] = "R" + i + "C" + j;
                        counter++;
                    }
                }
            }
        }

        // BOTTOM CENTER
        if(r >= 7 && r <= 9) {
            if (c >= 4 && c <= 6) {
                for (int i = 7; i <= 9; i++) {
                    for (int j = 4; j <= 6; j++) {
                        strTargetCells[counter] = "R" + i + "C" + j;
                        counter++;
                    }
                }
            }
        }            

        // BOTTOM RIGHT
        if(r >= 7 && r <= 9) {
            if (c >= 7 && c <= 9) {
                for (int i = 7; i <= 9; i++) {
                    for (int j = 7; j <= 9; j++) {
                        strTargetCells[counter] = "R" + i + "C" + j;
                        counter++;
                    }
                }
            }
        }                

        return strTargetCells;
    }
    
    @FXML private void loadPuzzle(Event e) throws IOException {
        String strFullPath = System.getProperty("user.dir");
        strFullPath = strFullPath + "\\" + strSourcePuzzleFile;
        try {
            BufferedReader br = new BufferedReader(new FileReader(strFullPath));
            String strLine;
            int intCounter = 0;
            resetBoard();
            resetHighlights();
            while((strLine = br.readLine()) != null) {
                System.out.println(strLine);
                int intValues[] = new int[9];
                    intValues = uploadPuzzle(strLine);
                    switch(intCounter) {
                        
                        case 0:
                            transfer_row1(intValues);
                            break;
                            
                        case 1:
                            transfer_row2(intValues);
                            break;
                            
                        case 2:
                            transfer_row3(intValues);
                            break;
                            
                        case 3:
                            transfer_row4(intValues);
                            break;
                            
                        case 4:
                            transfer_row5(intValues);
                            break;
                            
                        case 5:
                            transfer_row6(intValues);
                            break;
                            
                        case 6:
                            transfer_row7(intValues);
                            break;
                            
                        case 7:
                            transfer_row8(intValues);
                            break;
                            
                        case 8:
                            transfer_row9(intValues);
                            break;
                        
                    }
                    
                    intCounter++;
            }
        } catch (IOException ie) {
            System.err.println("File IO Exception: " + ie.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + ie.getMessage(), "File IO Error", JOptionPane.ERROR_MESSAGE);
            btn_loadpuzzle.setDisable(true);
        }
    }
    
    private void showFiles() {
        String strCurrentDirectory = System.getProperty("user.dir");
        System.out.println("Current Directory: " + strCurrentDirectory);
        try {
            FilenameFilter textFilter = new FilenameFilter() {

                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".txt");
                }
            };
            File f = new File(strCurrentDirectory);
            File[] files = f.listFiles(textFilter);
            int intLocal = 1;
            ObservableList<String> options = FXCollections.observableArrayList();
            for(File file : files) {
                if(!file.isDirectory()) {
                    System.out.println(intLocal + ". " + file.getName());
                    options.add(file.getName());
                    intLocal++;
                }
            }
            cmb_showFiles.getItems().addAll(options);
        } catch(Exception ioe) {
            System.err.println("Error opening file: " + ioe);
        }        
    }
    
    @FXML private void stopGame (Event e) {
        btn_Start.setDisable(false);
        btn_Stop.setDisable(true);
        btn_AutoPlay.setDisable(true);
        lst_options.setDisable(true);
        btn_SingleStep.setDisable(true);
        cmb_selectMethod.setDisable(false);
        hasGameStarted = false;
        blnFirstTimeStepMode = true;
        intStepModeCounter = 0;
        resetBoard();
        resetHighlights();
     }

   /**
    * Purpose: This method prints traverses through all the cells in the Sudoku
    * grid and then populates a 2D array with the values listed. Wherever a
    * value is missing, it will enter ZERO
    * @param e 
    */
    @FXML private void startGame(Event e) {
        // Check to make sure Select Method has been selected ...
        hasGameStarted = true;
        blnFirstTimeStepMode = true;
        intStepModeCounter = 0;
        int rows, cols;
        rows = cols = 0;
        String text;
        String textfield_id;
        for(int i =0; i < myCells.size(); i++) {
            text = myCells.get(i).getText().toString();
            textfield_id = myCells.get(i).getId().toString();
            if(text.length() > 0) {
                System.out.println(textfield_id + " - " + text);
                sudoku_grid[rows][cols] = Integer.parseInt(text);
            } else {
                sudoku_grid[rows][cols] = 0;
            }
            cols++;
            if(cols>=9) {
                cols=0;
                rows++;
            }
        }
        
        boolean blnColumn = true;
        String strSudokuBoard="";
        for(int i = 0; i<9; i++) {
            for (int j = 0; j<9; j++) {
                if(blnColumn) {
                    strSudokuBoard = strSudokuBoard + " " + Integer.toString(sudoku_grid[i][j]);
                    blnColumn=false;
                } else {
                    strSudokuBoard = strSudokuBoard + " " + Integer.toString(sudoku_grid[i][j]);                    
                }
                if (j == 8) {
                    System.out.println(strSudokuBoard);
                    strSudokuBoard="";
                    break;
                }
            }
        }
        strGameMode = cmb_selectMethod.getSelectionModel().getSelectedItem();
        try {
            switch(strGameMode) {
                case "Manual":
                    JOptionPane.showMessageDialog(null, "To play game manually, "
                            + "use the mouse to select each cell and then enter "
                            + "one of the recommended values", "Initiate Game", 
                            JOptionPane.INFORMATION_MESSAGE);
                    //cmb_selectMethod.setDisable(true);                    
                    break;
                case "Uninformed Search":
                    //cmb_selectMethod.setDisable(true);
                    break;
                case "Minimum Remaining Values": 
                    //cmb_selectMethod.setDisable(true);
                    break;                
                
            }
        } catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(null, "Please select a Game Mode.", "Initiate Game", JOptionPane.INFORMATION_MESSAGE);  
                cmb_selectMethod.setDisable(false);
                cmb_selectMethod.requestFocus();
        }
        
        btn_Stop.setDisable(false);
        btn_Start.setDisable(true);
        btn_SingleStep.setDisable(false);
        setupTable();
    }
    
    private void setupTable() {
        
        tbl_History.getColumns().clear();
        
        TableColumn<History, Integer> iDCol = new TableColumn<>("#");
        iDCol.setMaxWidth(30);
        iDCol.setCellValueFactory(new PropertyValueFactory("intCounter"));

        TableColumn<History, String> strCell = new TableColumn<>("Cell");
        strCell.setCellValueFactory(new PropertyValueFactory("strCell"));        
        
        TableColumn<History, Integer> strSelectedValue = new TableColumn<>("Value");
        strSelectedValue.setCellValueFactory(new PropertyValueFactory("intSelectedValue"));        

        TableColumn<History, String> strRemainingValues = new TableColumn<>("Remaining Values");
        strRemainingValues.setMinWidth(150);
        strRemainingValues.setCellValueFactory(new PropertyValueFactory("strRemainingValues"));        

        TableColumn<History, String> strUsedValues = new TableColumn<>("Used Values");
        strUsedValues.setCellValueFactory(new PropertyValueFactory("strUsedValues"));        

        TableColumn<History, String> blnUsable = new TableColumn<>("Usable");
        blnUsable.setMinWidth(40);
        blnUsable.setCellValueFactory(new PropertyValueFactory("blnUsable"));      
        
        tbl_History.setEditable(true);
        tbl_History.setItems(data);   
        tbl_History.getColumns().addAll(iDCol,strCell,strSelectedValue,strRemainingValues,strUsedValues,blnUsable);
        tbl_History.getSelectionModel().setCellSelectionEnabled(true);
      
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hasGameStarted = false;
        showFiles();
        btn_loadpuzzle.setDisable(true);
        btn_AutoPlay.setDisable(true);
        cmb_showFiles.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                if(!ov.equals("")) {
                    strSourcePuzzleFile = ov.getValue();
                    System.out.println("Source File: " + strSourcePuzzleFile);
                    btn_loadpuzzle.setDisable(false);
                } else {
                    btn_loadpuzzle.setDisable(true);
                }
            }
        });
        
        R1C1.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R1C2.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R1C3.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R1C4.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R1C5.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R1C6.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R1C7.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R1C8.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R1C9.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());

        R2C1.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R2C2.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R2C3.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R2C4.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R2C5.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R2C6.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R2C7.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R2C8.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R2C9.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());        

        R3C1.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R3C2.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R3C3.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R3C4.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R3C5.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R3C6.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R3C7.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R3C8.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R3C9.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());            

        R4C1.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R4C2.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R4C3.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R4C4.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R4C5.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R4C6.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R4C7.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R4C8.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R4C9.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());           
        
        R5C1.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R5C2.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R5C3.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R5C4.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R5C5.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R5C6.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R5C7.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R5C8.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R5C9.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());         

        R6C1.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R6C2.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R6C3.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R6C4.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R6C5.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R6C6.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R6C7.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R6C8.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R6C9.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());                
        
        R7C1.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R7C2.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R7C3.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R7C4.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R7C5.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R7C6.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R7C7.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R7C8.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R7C9.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());              
        
        R8C1.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R8C2.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R8C3.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R8C4.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R8C5.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R8C6.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R8C7.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R8C8.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R8C9.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());         
        
        R9C1.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R9C2.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R9C3.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R9C4.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R9C5.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R9C6.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R9C7.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R9C8.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());
        R9C9.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseClickedEventHandler());                 

        R1C1.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R1C2.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R1C3.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R1C4.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R1C5.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R1C6.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R1C7.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R1C8.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R1C9.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        
        R2C1.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R2C2.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R2C3.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R2C4.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R2C5.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R2C6.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R2C7.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R2C8.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R2C9.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());

        R3C1.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R3C2.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R3C3.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R3C4.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R3C5.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R3C6.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R3C7.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R3C8.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R3C9.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());

        R4C1.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R4C2.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R4C3.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R4C4.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R4C5.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R4C6.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R4C7.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R4C8.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R4C9.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());

        R5C1.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R5C2.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R5C3.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R5C4.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R5C5.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R5C6.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R5C7.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R5C8.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R5C9.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());

        R6C1.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R6C2.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R6C3.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R6C4.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R6C5.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R6C6.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R6C7.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R6C8.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R6C9.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());

        R7C1.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R7C2.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R7C3.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R7C4.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R7C5.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R7C6.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R7C7.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R7C8.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R7C9.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());

        R8C1.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R8C2.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R8C3.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R8C4.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R8C5.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R8C6.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R8C7.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R8C8.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R8C9.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());

        R9C1.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R9C2.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R9C3.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R9C4.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R9C5.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R9C6.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R9C7.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R9C8.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
        R9C9.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());

        myCells = getNodesOfType(mainPane, TextField.class);
        it = myCells.iterator();
        while (it.hasNext()) {
            String text = it.next().getText().toString();
            if(text.length()>0) {
                System.out.println("Content: " + text.toString());                
            }
        }
        
        // Add Combo Box options
        String[] strOptions = {
           "Manual", "Uninformed Search", "Minimum Remaining Values"
       };
        try {
            cmb_selectMethod.getItems().addAll(strOptions);            
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
        
        cmb_selectMethod.valueProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                switch(newValue) {
                    case "Manual":
                        btn_SingleStep.setDisable(true);
                        break;
                    case "Uninformed Search":
                        btn_SingleStep.setDisable(false);                
                        break;
                    case "Minimum Remaining Values":
                        btn_SingleStep.setDisable(false);                
                        break;
                }                
            }
        });
        
        btn_Stop.setDisable(true);
        //lst_options.setDisable(true);
        btn_SingleStep.setDisable(true);
    }    
    
    /**
     * This method iterates through all the controls in JavaFX scene and it lists
     * all the controls of a certain type
     * @param <T>
     * @param parent
     * @param type
     * @return 
     */
    private <T> List<T> getNodesOfType (Pane parent, Class<T> type) {
        List<T> elements = new ArrayList<>();
        try {
            for (Node node : parent.getChildren()) {
                if (node instanceof Pane) {
                    elements.addAll(getNodesOfType((Pane) node, type));
                } else if (type.isAssignableFrom(node.getClass())) {
                    elements.add((T) node);
                }
            }            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return Collections.unmodifiableList(elements);
    }

    private void resetBoard() {
        it = myCells.iterator();
        while (it.hasNext()) {
            it.next().setText("");
        }
        
        it = myCells.iterator();
        while (it.hasNext()) {
            it.next().setStyle("-fx-background-color:  white; -fx-border-color: silver; -fx-text-fill: black; -fx-font-size: 17;");
        }
        
    }

    private int[] uploadPuzzle(String strLine) {
        int intValues[] = new int[9];
        char ch;
        String strTemp = "";
        strTemp = strLine;
        int intTemp = 0;
        int intCounter = 0;
        do {
            ch = (char) strTemp.charAt(0);
            try {
                intTemp = Character.getNumericValue(ch);
                intValues[intCounter] = intTemp;
                intCounter++;
            } catch (NumberFormatException numberFormatException) {
                //JOptionPane.showMessageDialog(null, "Error Parsing Data: " + numberFormatException, "Error Loading Puzzle", JOptionPane.ERROR_MESSAGE);
                System.err.println("Error: " + numberFormatException.getMessage());
                intValues[intCounter] = intTemp;
                intCounter++;
            }
            try {
                strTemp = strTemp.substring(2);
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        } while(intCounter<9);
        return intValues;
    }

    private void transfer_row1(int[] intValues) {
        
        if(intValues[0] != 0) {
            R1C1.setText(Integer.toString(intValues[0]));
            R1C1.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }

        if(intValues[1] != 0) {
            R1C2.setText(Integer.toString(intValues[1]));
            R1C2.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[2] != 0) {
            R1C3.setText(Integer.toString(intValues[2]));
            R1C3.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[3] != 0) {
            R1C4.setText(Integer.toString(intValues[3]));
            R1C4.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[4] != 0) {
            R1C5.setText(Integer.toString(intValues[4]));
            R1C5.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[5] != 0) {
            R1C6.setText(Integer.toString(intValues[5]));
            R1C6.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[6] != 0) {
            R1C7.setText(Integer.toString(intValues[6]));
            R1C7.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[7] != 0) {
            R1C8.setText(Integer.toString(intValues[7]));
            R1C8.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[8] != 0) {
            R1C9.setText(Integer.toString(intValues[8]));
            R1C9.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }        
    }    
        
    private void transfer_row2(int[] intValues) {
        
        if(intValues[0] != 0) {
            R2C1.setText(Integer.toString(intValues[0]));
            R2C1.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }

        if(intValues[1] != 0) {
            R2C2.setText(Integer.toString(intValues[1]));
            R2C2.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[2] != 0) {
            R2C3.setText(Integer.toString(intValues[2]));
            R2C3.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[3] != 0) {
            R2C4.setText(Integer.toString(intValues[3]));
            R2C4.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[4] != 0) {
            R2C5.setText(Integer.toString(intValues[4]));
            R2C5.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[5] != 0) {
            R2C6.setText(Integer.toString(intValues[5]));
            R2C6.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[6] != 0) {
            R2C7.setText(Integer.toString(intValues[6]));
            R2C7.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[7] != 0) {
            R2C8.setText(Integer.toString(intValues[7]));
            R2C8.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[8] != 0) {
            R2C9.setText(Integer.toString(intValues[8]));
            R2C9.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }        
    }    
    
    private void transfer_row3(int[] intValues) {
        
        if(intValues[0] != 0) {
            R3C1.setText(Integer.toString(intValues[0]));
            R3C1.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }

        if(intValues[1] != 0) {
            R3C2.setText(Integer.toString(intValues[1]));
            R3C2.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[2] != 0) {
            R3C3.setText(Integer.toString(intValues[2]));
            R3C3.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[3] != 0) {
            R3C4.setText(Integer.toString(intValues[3]));
            R3C4.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[4] != 0) {
            R3C5.setText(Integer.toString(intValues[4]));
            R3C5.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[5] != 0) {
            R3C6.setText(Integer.toString(intValues[5]));
            R3C6.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[6] != 0) {
            R3C7.setText(Integer.toString(intValues[6]));
            R3C7.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[7] != 0) {
            R3C8.setText(Integer.toString(intValues[7]));
            R3C8.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[8] != 0) {
            R3C9.setText(Integer.toString(intValues[8]));
            R3C9.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }        
    }        

    private void transfer_row4(int[] intValues) {
        
        if(intValues[0] != 0) {
            R4C1.setText(Integer.toString(intValues[0]));
            R4C1.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }

        if(intValues[1] != 0) {
            R4C2.setText(Integer.toString(intValues[1]));
            R4C2.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[2] != 0) {
            R4C3.setText(Integer.toString(intValues[2]));
            R4C3.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[3] != 0) {
            R4C4.setText(Integer.toString(intValues[3]));
            R4C4.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[4] != 0) {
            R4C5.setText(Integer.toString(intValues[4]));
            R4C5.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[5] != 0) {
            R4C6.setText(Integer.toString(intValues[5]));
            R4C6.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[6] != 0) {
            R4C7.setText(Integer.toString(intValues[6]));
            R4C7.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[7] != 0) {
            R4C8.setText(Integer.toString(intValues[7]));
            R4C8.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[8] != 0) {
            R4C9.setText(Integer.toString(intValues[8]));
            R4C9.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }        
    }       
       
    private void transfer_row5(int[] intValues) {
        
        if(intValues[0] != 0) {
            R5C1.setText(Integer.toString(intValues[0]));
            R5C1.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }

        if(intValues[1] != 0) {
            R5C2.setText(Integer.toString(intValues[1]));
            R5C2.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[2] != 0) {
            R5C3.setText(Integer.toString(intValues[2]));
            R5C3.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[3] != 0) {
            R5C4.setText(Integer.toString(intValues[3]));
            R5C4.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[4] != 0) {
            R5C5.setText(Integer.toString(intValues[4]));
            R5C5.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[5] != 0) {
            R5C6.setText(Integer.toString(intValues[5]));
            R5C6.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[6] != 0) {
            R5C7.setText(Integer.toString(intValues[6]));
            R5C7.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[7] != 0) {
            R5C8.setText(Integer.toString(intValues[7]));
            R5C8.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[8] != 0) {
            R5C9.setText(Integer.toString(intValues[8]));
            R5C9.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }        
    }        

    private void transfer_row6(int[] intValues) {
        
        if(intValues[0] != 0) {
            R6C1.setText(Integer.toString(intValues[0]));
            R6C1.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }

        if(intValues[1] != 0) {
            R6C2.setText(Integer.toString(intValues[1]));
            R6C2.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[2] != 0) {
            R6C3.setText(Integer.toString(intValues[2]));
            R6C3.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[3] != 0) {
            R6C4.setText(Integer.toString(intValues[3]));
            R6C4.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[4] != 0) {
            R6C5.setText(Integer.toString(intValues[4]));
            R6C5.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[5] != 0) {
            R6C6.setText(Integer.toString(intValues[5]));
            R6C6.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[6] != 0) {
            R6C7.setText(Integer.toString(intValues[6]));
            R6C7.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[7] != 0) {
            R6C8.setText(Integer.toString(intValues[7]));
            R6C8.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[8] != 0) {
            R6C9.setText(Integer.toString(intValues[8]));
            R6C9.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }        
    }    
   
    private void transfer_row7(int[] intValues) {
        
        if(intValues[0] != 0) {
            R7C1.setText(Integer.toString(intValues[0]));
            R7C1.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }

        if(intValues[1] != 0) {
            R7C2.setText(Integer.toString(intValues[1]));
            R7C2.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[2] != 0) {
            R7C3.setText(Integer.toString(intValues[2]));
            R7C3.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[3] != 0) {
            R7C4.setText(Integer.toString(intValues[3]));
            R7C4.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[4] != 0) {
            R7C5.setText(Integer.toString(intValues[4]));
            R7C5.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[5] != 0) {
            R7C6.setText(Integer.toString(intValues[5]));
            R7C6.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[6] != 0) {
            R7C7.setText(Integer.toString(intValues[6]));
            R7C7.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[7] != 0) {
            R7C8.setText(Integer.toString(intValues[7]));
            R7C8.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[8] != 0) {
            R7C9.setText(Integer.toString(intValues[8]));
            R7C9.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }        
    }       
   
    private void transfer_row8(int[] intValues) {
        
        if(intValues[0] != 0) {
            R8C1.setText(Integer.toString(intValues[0]));
            R8C1.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }

        if(intValues[1] != 0) {
            R8C2.setText(Integer.toString(intValues[1]));
            R8C2.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[2] != 0) {
            R8C3.setText(Integer.toString(intValues[2]));
            R8C3.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[3] != 0) {
            R8C4.setText(Integer.toString(intValues[3]));
            R8C4.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[4] != 0) {
            R8C5.setText(Integer.toString(intValues[4]));
            R8C5.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[5] != 0) {
            R8C6.setText(Integer.toString(intValues[5]));
            R8C6.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[6] != 0) {
            R8C7.setText(Integer.toString(intValues[6]));
            R8C7.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[7] != 0) {
            R8C8.setText(Integer.toString(intValues[7]));
            R8C8.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[8] != 0) {
            R8C9.setText(Integer.toString(intValues[8]));
            R8C9.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }        
    }

    private void transfer_row9(int[] intValues) {
        
        if(intValues[0] != 0) {
            R9C1.setText(Integer.toString(intValues[0]));
            R9C1.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }

        if(intValues[1] != 0) {
            R9C2.setText(Integer.toString(intValues[1]));
            R9C2.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[2] != 0) {
            R9C3.setText(Integer.toString(intValues[2]));
            R9C3.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[3] != 0) {
            R9C4.setText(Integer.toString(intValues[3]));
            R9C4.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[4] != 0) {
            R9C5.setText(Integer.toString(intValues[4]));
            R9C5.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[5] != 0) {
            R9C6.setText(Integer.toString(intValues[5]));
            R9C6.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[6] != 0) {
            R9C7.setText(Integer.toString(intValues[6]));
            R9C7.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[7] != 0) {
            R9C8.setText(Integer.toString(intValues[7]));
            R9C8.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }
        
        if(intValues[8] != 0) {
            R9C9.setText(Integer.toString(intValues[8]));
            R9C9.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
        }        
    }    

    // This method is used when the forward pass is going on. strUsedValues will be the currently used value
    // For reverse pass (another method), strUsedValues will contain more values based on previous successes
    private void addToTableView(int intValue, String strCell, int intCounter) {
        String strRemainingValues = "";
        boolean blnFirstEntry = true;
        
        for(int i = 1; i < lst_options.getItems().size(); i++) {
            if(blnFirstEntry) {
               strRemainingValues = Integer.toString(lst_options.getItems().get(i));
               blnFirstEntry = false;
            } else {
               strRemainingValues = strRemainingValues + " " + lst_options.getItems().get(i);
            }
        }
        
        History history = new History();
        
        history.setIntCounter(intCounter + 1);

        history.setStrCell(strCell);

        history.setIntSelectedValue(intValue);

        history.setStrRemainingValues(strRemainingValues);

        history.setStrUsedValues(Integer.toString(intValue));

        history.setBlnUsable(true);
        
        tbl_History.getItems().add(history);
       
    }
   
    private class MouseClickedEventHandler implements EventHandler<Event> {
        @Override
        public void handle (Event e) {
            if(hasGameStarted) {
                String strStyle = "";
                resetHighlights();
                String strControlName = ((Control)e.getSource()).getId().toString();
                strTargetCells = createHighlightArray(strControlName);
                target = getNodesOfType(mainPane, TextField.class);
                for(TextField t : target) {
                    strStyle = t.getStyle();
                    System.out.println("Cell Style: " + t.getId() + " - " + t.getText() +" - " + strStyle);
                    if(!strStyle.contains("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;")) {
                        for(int i = 0; i < strTargetCells.length; i++) {
                            if(strTargetCells[i] != null) {
                                if(t.getId().contains(strTargetCells[i])) {
                                    t.setStyle("-fx-background-color: RGB(162,100,100); -fx-opacity: 0.7; -fx-border-color: silver");
                                    break;
                                }                                    
                            }
                        }                        
                    }
                }
                populateOptions();
            }
        } 
        
        /**
        * <br>
        * <h3 style="color:white;">CLASS: FXMLDocumentController </h3>
        * <HR>
        * <h3 style="color:white;">PARAMETERS: None</h3>
        *      <blockquote><i>a. strControlName - String name the cell that was selected by the user (e.g., R1C1)</i></blockquote>  
        * <HR>
        * <h3 style="color:white;">VARIABLES: </h3>
        *      <blockquote><i>a. strTargetCells - Array of all target cells that are impacted by the target cell (Row / Col / Block)</i></blockquote>
        *      <blockquote><i>b. strC - The name of the target column</i></blockquote>
        *      <blockquote><i>c. strR - The name of the target row</i></blockquote>
        *      <blockquote><i>d. r - Integer row </i></blockquote>
        *      <blockquote><i>e. c - Integer column</i></blockquote>
        * <HR>
        * <h3 style="color:white;">PURPOSE: </h3>
        * <pre>
        When the user clicks on a specific Sudoku cell, this method will
        highlight the row, column and the block that the target cell belongs to
        While it is doing that the array blnOptions will get populated based on
        existing values. This information will then be used by the Observable List
        'options' to populate the listview lst_options. The contents of the 
        observable list will be only those values that the user can select</pre>
        */
        private String[] createHighlightArray(String strControlName) {
            String[] strTargetCells = new String[27];
            String strC = strControlName.substring(2);
            String strR = strControlName.substring(0, 2);
            int r = Integer.parseInt(strR.substring(1));
            int c = Integer.parseInt(strC.substring(1));

            // For Rows 
            int counter = 0;
            for (int i=1; i < 10; i++) {
                strTargetCells[counter] = strR + "C" + i;
                counter++;
            }
            
            // For Cols
            for (int i=1; i < 10; i++) {
                strTargetCells[counter] = "R" + i + strC;
                counter++;
            }            
            
            // For Block
            
            // TOP LEFT
            if(r >= 1 && r <= 3) {
                if (c >= 1 && c <= 3) {
                    for (int i = 1; i <=3; i++) {
                        for (int j = 1; j <= 3; j++) {
                            strTargetCells[counter] = "R" + i + "C" + j;
                            counter++;
                        }
                    }
                }
            }

            // TOP CENTER
            if(r >= 1 && r <= 3) {
                if (c >= 4 && c <= 6) {
                    for (int i = 1; i <=3; i++) {
                        for (int j = 4; j <= 6; j++) {
                            strTargetCells[counter] = "R" + i + "C" + j;
                            counter++;
                        }
                    }
                }
            }            

            // TOP RIGHT
            if(r >= 1 && r <= 3) {
                if (c >= 7 && c <= 9) {
                    for (int i = 1; i <=3; i++) {
                        for (int j = 7; j <= 9; j++) {
                            strTargetCells[counter] = "R" + i + "C" + j;
                            counter++;
                        }
                    }
                }
            }               

            // MIDDLE LEFT
            if(r >= 4 && r <= 6) {
                if (c >= 1 && c <= 3) {
                    for (int i = 4; i <= 6; i++) {
                        for (int j = 1; j <= 3; j++) {
                            strTargetCells[counter] = "R" + i + "C" + j;
                            counter++;
                        }
                    }
                }
            }

            // MIDDLE CENTER
            if(r >= 4 && r <= 6) {
                if (c >= 4 && c <= 6) {
                    for (int i = 4; i <=6; i++) {
                        for (int j = 4; j <= 6; j++) {
                            strTargetCells[counter] = "R" + i + "C" + j;
                            counter++;
                        }
                    }
                }
            }            

            // MIDDLE RIGHT
            if(r >= 4 && r <= 6) {
                if (c >= 7 && c <= 9) {
                    for (int i = 4; i <=6; i++) {
                        for (int j = 7; j <= 9; j++) {
                            strTargetCells[counter] = "R" + i + "C" + j;
                            counter++;
                        }
                    }
                }
            }              

            // BOTTOM LEFT
            if(r >= 7 && r <= 9) {
                if (c >= 1 && c <= 3) {
                    for (int i = 7; i <= 9; i++) {
                        for (int j = 1; j <= 3; j++) {
                            strTargetCells[counter] = "R" + i + "C" + j;
                            counter++;
                        }
                    }
                }
            }

            // BOTTOM CENTER
            if(r >= 7 && r <= 9) {
                if (c >= 4 && c <= 6) {
                    for (int i = 7; i <= 9; i++) {
                        for (int j = 4; j <= 6; j++) {
                            strTargetCells[counter] = "R" + i + "C" + j;
                            counter++;
                        }
                    }
                }
            }            

            // BOTTOM RIGHT
            if(r >= 7 && r <= 9) {
                if (c >= 7 && c <= 9) {
                    for (int i = 7; i <= 9; i++) {
                        for (int j = 7; j <= 9; j++) {
                            strTargetCells[counter] = "R" + i + "C" + j;
                            counter++;
                        }
                    }
                }
            }                
            
            return strTargetCells;
        }
    }
    
    private void resetHighlights() {
        String strHighlightedStyle = "-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;";
        String strCurrentCellStyle = "";

        strCurrentCellStyle = R1C1.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R1C1.setStyle("-fx-background-color:  white; -fx-border-color: silver");            
        }        
            
        strCurrentCellStyle = R1C2.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R1C2.setStyle("-fx-background-color:  white; -fx-border-color: silver");            
        }        

        strCurrentCellStyle = R1C3.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
           R1C3.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R1C4.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
           R1C4.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }
        
        strCurrentCellStyle = R1C5.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R1C5.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R1C6.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {            
            R1C6.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R1C7.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R1C7.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R1C8.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R1C8.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R1C9.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R1C9.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }
    

        strCurrentCellStyle = R2C1.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R2C1.setStyle("-fx-background-color:  white; -fx-border-color: silver");            
        }        
            
        strCurrentCellStyle = R2C2.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R2C2.setStyle("-fx-background-color:  white; -fx-border-color: silver");            
        }        

        strCurrentCellStyle = R2C3.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
           R2C3.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R2C4.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
           R2C4.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }
        
        strCurrentCellStyle = R2C5.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R2C5.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R2C6.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {            
            R2C6.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R2C7.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R2C7.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R2C8.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R2C8.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R2C9.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R2C9.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }


        
        strCurrentCellStyle = R3C1.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R3C1.setStyle("-fx-background-color:  white; -fx-border-color: silver");            
        }        
            
        strCurrentCellStyle = R3C2.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R3C2.setStyle("-fx-background-color:  white; -fx-border-color: silver");            
        }        

        strCurrentCellStyle = R3C3.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
           R3C3.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R3C4.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
           R3C4.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }
        
        strCurrentCellStyle = R3C5.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R3C5.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R3C6.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {            
            R3C6.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R3C7.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R3C7.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R3C8.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R3C8.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R3C9.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R3C9.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }
        

        strCurrentCellStyle = R4C1.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R4C1.setStyle("-fx-background-color:  white; -fx-border-color: silver");            
        }        
            
        strCurrentCellStyle = R4C2.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R4C2.setStyle("-fx-background-color:  white; -fx-border-color: silver");            
        }        

        strCurrentCellStyle = R4C3.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
           R4C3.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R4C4.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
           R4C4.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }
        
        strCurrentCellStyle = R4C5.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R4C5.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R4C6.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {            
            R4C6.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R4C7.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R4C7.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R4C8.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R4C8.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R4C9.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R4C9.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }
        
        

        strCurrentCellStyle = R5C1.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R5C1.setStyle("-fx-background-color:  white; -fx-border-color: silver");            
        }        
            
        strCurrentCellStyle = R5C2.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R5C2.setStyle("-fx-background-color:  white; -fx-border-color: silver");            
        }        

        strCurrentCellStyle = R5C3.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
           R5C3.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R5C4.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
           R5C4.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }
        
        strCurrentCellStyle = R5C5.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R5C5.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R5C6.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {            
            R5C6.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R5C7.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R5C7.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R5C8.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R5C8.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R5C9.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R5C9.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }
        

        strCurrentCellStyle = R6C1.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R6C1.setStyle("-fx-background-color:  white; -fx-border-color: silver");            
        }        
            
        strCurrentCellStyle = R6C2.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R6C2.setStyle("-fx-background-color:  white; -fx-border-color: silver");            
        }        

        strCurrentCellStyle = R6C3.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
           R6C3.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R6C4.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
           R6C4.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }
        
        strCurrentCellStyle = R6C5.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R6C5.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R6C6.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {            
            R6C6.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R6C7.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R6C7.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R6C8.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R6C8.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R6C9.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R6C9.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }
        

        
        strCurrentCellStyle = R7C1.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R7C1.setStyle("-fx-background-color:  white; -fx-border-color: silver");            
        }        
            
        strCurrentCellStyle = R7C2.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R7C2.setStyle("-fx-background-color:  white; -fx-border-color: silver");            
        }        

        strCurrentCellStyle = R7C3.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
           R7C3.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R7C4.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
           R7C4.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }
        
        strCurrentCellStyle = R7C5.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R7C5.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R7C6.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {            
            R7C6.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R7C7.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R7C7.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R7C8.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R7C8.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R7C9.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R7C9.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }
        

        strCurrentCellStyle = R8C1.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R8C1.setStyle("-fx-background-color:  white; -fx-border-color: silver");            
        }        
            
        strCurrentCellStyle = R8C2.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R8C2.setStyle("-fx-background-color:  white; -fx-border-color: silver");            
        }        

        strCurrentCellStyle = R8C3.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
           R8C3.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R8C4.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
           R8C4.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }
        
        strCurrentCellStyle = R8C5.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R8C5.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R8C6.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {            
            R8C6.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R8C7.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R8C7.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R8C8.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R8C8.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R8C9.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R8C9.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }
        

        strCurrentCellStyle = R9C1.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R9C1.setStyle("-fx-background-color:  white; -fx-border-color: silver");            
        }        
            
        strCurrentCellStyle = R9C2.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R9C2.setStyle("-fx-background-color:  white; -fx-border-color: silver");            
        }        

        strCurrentCellStyle = R9C3.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
           R9C3.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R9C4.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
           R9C4.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }
        
        strCurrentCellStyle = R9C5.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R9C5.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R9C6.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {            
            R9C6.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R9C7.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R9C7.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R9C8.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {
            R9C8.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }

        strCurrentCellStyle = R9C9.getStyle();
        if(!strCurrentCellStyle.contains(strHighlightedStyle)) {        
            R9C9.setStyle("-fx-background-color:  white; -fx-border-color: silver");
        }
        
        
        
    }

    /**
     * This inner class adds an event handler to each of the strCellID boxes so that when
 they are clicked, the fx:id of the strCellID box is shown below
     */
    private class MouseExitedEventHandler implements EventHandler<Event> {
        @Override
        public void handle(Event e) {
            //System.out.println("Cell: " + ((Control)e.getSource()).getId());
            String strControlName = ((Control)e.getSource()).getId().toString();
            switch (strControlName) {
                case "R1C1":
                    if(validateText(R1C1.getText().toString())==false) {
                        R1C1.setText("");
                    } 
                    changeStyle(R1C1);
                    break;
                case "R1C2":
                    if(validateText(R1C2.getText().toString())==false) {
                        R1C2.setText("");
                    } 
                    changeStyle(R1C2);
                    break;
                case "R1C3":
                    if(validateText(R1C3.getText().toString())==false) {
                        R1C3.setText("");
                    } 
                    changeStyle(R1C3);
                    break;
                case "R1C4":
                    if(validateText(R1C4.getText().toString())==false) {
                        R1C4.setText("");
                    } 
                    changeStyle(R1C4);
                    break;
                case "R1C5":
                    if(validateText(R1C5.getText().toString())==false) {
                        R1C5.setText("");
                    } 
                    changeStyle(R1C5);
                    break;
                case "R1C6":
                    if(validateText(R1C6.getText().toString())==false) {
                        R1C6.setText("");
                    } 
                    changeStyle(R1C6);
                    break;
                case "R1C7":
                    if(validateText(R1C7.getText().toString())==false) {
                        R1C7.setText("");
                    } 
                    changeStyle(R1C7);
                    break;
                case "R1C8":
                    if(validateText(R1C8.getText().toString())==false) {
                        R1C8.setText("");
                    } 
                    changeStyle(R1C8);
                    break;
                case "R1C9":
                    if(validateText(R1C9.getText().toString())==false) {
                        R1C9.setText("");
                    } 
                    changeStyle(R1C9);
                    break;       

                case "R2C1":
                    if(validateText(R2C1.getText().toString())==false) {
                        R2C1.setText("");
                    } 
                    changeStyle(R2C1);
                    break;
                case "R2C2":
                    if(validateText(R2C2.getText().toString())==false) {
                        R2C2.setText("");
                    } 
                    changeStyle(R2C2);
                    break;
                case "R2C3":
                    if(validateText(R2C3.getText().toString())==false) {
                        R2C3.setText("");
                    } 
                    changeStyle(R2C3);
                    break;
                case "R2C4":
                    if(validateText(R2C4.getText().toString())==false) {
                        R2C4.setText("");
                    } 
                    changeStyle(R2C4);
                    break;
                case "R2C5":
                    if(validateText(R2C5.getText().toString())==false) {
                        R2C5.setText("");
                    } 
                    changeStyle(R2C5);
                    break;
                case "R2C6":
                    if(validateText(R2C6.getText().toString())==false) {
                        R2C6.setText("");
                    } 
                    changeStyle(R2C6);
                    break;
                case "R2C7":
                    if(validateText(R2C7.getText().toString())==false) {
                        R2C7.setText("");
                    } 
                    changeStyle(R2C7);
                    break;
                case "R2C8":
                    if(validateText(R2C8.getText().toString())==false) {
                        R2C8.setText("");
                    } 
                    changeStyle(R2C8);
                    break;
                case "R2C9":
                    if(validateText(R2C9.getText().toString())==false) {
                        R1C1.setText("");
                    } 
                    changeStyle(R2C9);
                    break;       
                    
                case "R3C1":
                    if(validateText(R3C1.getText().toString())==false) {
                        R3C1.setText("");
                    } 
                    changeStyle(R3C1);
                    break;
                case "R3C2":
                    if(validateText(R3C2.getText().toString())==false) {
                        R3C2.setText("");
                    } 
                    changeStyle(R3C2);
                    break;
                case "R3C3":
                    if(validateText(R3C3.getText().toString())==false) {
                        R3C3.setText("");
                    } 
                    changeStyle(R3C3);
                    break;
                case "R3C4":
                    if(validateText(R3C4.getText().toString())==false) {
                        R3C4.setText("");
                    } 
                    changeStyle(R3C4);
                    break;
                case "R3C5":
                    if(validateText(R3C5.getText().toString())==false) {
                        R3C5.setText("");
                    } 
                    changeStyle(R3C5);
                    break;
                case "R3C6":
                    if(validateText(R3C6.getText().toString())==false) {
                        R3C6.setText("");
                    } 
                    changeStyle(R3C6);
                    break;
                case "R3C7":
                    if(validateText(R3C7.getText().toString())==false) {
                        R3C7.setText("");
                    } 
                    changeStyle(R3C7);
                    break;
                case "R3C8":
                    if(validateText(R3C8.getText().toString())==false) {
                        R3C8.setText("");
                    } 
                    changeStyle(R3C8);
                    break;
                case "R3C9":
                    if(validateText(R3C9.getText().toString())==false) {
                        R1C1.setText("");
                    } 
                    changeStyle(R3C9);
                    break;       

                case "R4C1":
                    if(validateText(R4C1.getText().toString())==false) {
                        R4C1.setText("");
                    } 
                    changeStyle(R4C1);
                    break;
                case "R4C2":
                    if(validateText(R4C2.getText().toString())==false) {
                        R4C2.setText("");
                    } 
                    changeStyle(R4C2);
                    break;
                case "R4C3":
                    if(validateText(R4C3.getText().toString())==false) {
                        R4C3.setText("");
                    } 
                    changeStyle(R4C3);
                    break;
                case "R4C4":
                    if(validateText(R4C4.getText().toString())==false) {
                        R4C4.setText("");
                    } 
                    changeStyle(R4C4);
                    break;
                case "R4C5":
                    if(validateText(R4C5.getText().toString())==false) {
                        R4C5.setText("");
                    } 
                    changeStyle(R4C5);
                    break;
                case "R4C6":
                    if(validateText(R4C6.getText().toString())==false) {
                        R4C6.setText("");
                    } 
                    changeStyle(R4C6);
                    break;
                case "R4C7":
                    if(validateText(R4C7.getText().toString())==false) {
                        R4C7.setText("");
                    } 
                    changeStyle(R4C7);
                    break;
                case "R4C8":
                    if(validateText(R4C8.getText().toString())==false) {
                        R4C8.setText("");
                    } 
                    changeStyle(R4C8);
                    break;
                case "R4C9":
                    if(validateText(R4C9.getText().toString())==false) {
                        R1C1.setText("");
                    } 
                    changeStyle(R4C9);
                    break;       
                    
                case "R5C1":
                    if(validateText(R5C1.getText().toString())==false) {
                        R5C1.setText("");
                    } 
                    changeStyle(R5C1);
                    break;
                case "R5C2":
                    if(validateText(R5C2.getText().toString())==false) {
                        R5C2.setText("");
                    } 
                    changeStyle(R5C2);
                    break;
                case "R5C3":
                    if(validateText(R5C3.getText().toString())==false) {
                        R5C3.setText("");
                    } 
                    changeStyle(R5C3);
                    break;
                case "R5C4":
                    if(validateText(R5C4.getText().toString())==false) {
                        R5C4.setText("");
                    } 
                    changeStyle(R5C4);
                    break;
                case "R5C5":
                    if(validateText(R5C5.getText().toString())==false) {
                        R5C5.setText("");
                    } 
                    changeStyle(R5C5);
                    break;
                case "R5C6":
                    if(validateText(R5C6.getText().toString())==false) {
                        R5C6.setText("");
                    } 
                    changeStyle(R5C6);
                    break;
                case "R5C7":
                    if(validateText(R5C7.getText().toString())==false) {
                        R5C7.setText("");
                    } 
                    changeStyle(R5C7);
                    break;
                case "R5C8":
                    if(validateText(R5C8.getText().toString())==false) {
                        R5C8.setText("");
                    } 
                    changeStyle(R5C8);
                    break;
                case "R5C9":
                    if(validateText(R5C9.getText().toString())==false) {
                        R1C1.setText("");
                    } 
                    changeStyle(R5C9);
                    break;       

                case "R6C1":
                    if(validateText(R6C1.getText().toString())==false) {
                        R6C1.setText("");
                    } 
                    changeStyle(R6C1);
                    break;
                case "R6C2":
                    if(validateText(R6C2.getText().toString())==false) {
                        R6C2.setText("");
                    } 
                    changeStyle(R6C2);
                    break;
                case "R6C3":
                    if(validateText(R6C3.getText().toString())==false) {
                        R6C3.setText("");
                    } 
                    changeStyle(R6C3);
                    break;
                case "R6C4":
                    if(validateText(R6C4.getText().toString())==false) {
                        R6C4.setText("");
                    } 
                    changeStyle(R6C4);
                    break;
                case "R6C5":
                    if(validateText(R6C5.getText().toString())==false) {
                        R6C5.setText("");
                    } 
                    changeStyle(R6C5);
                    break;
                case "R6C6":
                    if(validateText(R6C6.getText().toString())==false) {
                        R6C6.setText("");
                    } 
                    changeStyle(R6C6);
                    break;
                case "R6C7":
                    if(validateText(R6C7.getText().toString())==false) {
                        R6C7.setText("");
                    } 
                    changeStyle(R6C7);
                    break;
                case "R6C8":
                    if(validateText(R6C8.getText().toString())==false) {
                        R6C8.setText("");
                    } 
                    changeStyle(R6C8);
                    break;
                case "R6C9":
                    if(validateText(R6C9.getText().toString())==false) {
                        R1C1.setText("");
                    } 
                    changeStyle(R6C9);
                    break;       

                case "R7C1":
                    if(validateText(R7C1.getText().toString())==false) {
                        R7C1.setText("");
                    } 
                    changeStyle(R7C1);
                    break;
                case "R7C2":
                    if(validateText(R7C2.getText().toString())==false) {
                        R7C2.setText("");
                    } 
                    changeStyle(R7C2);
                    break;
                case "R7C3":
                    if(validateText(R7C3.getText().toString())==false) {
                        R7C3.setText("");
                    } 
                    changeStyle(R7C3);
                    break;
                case "R7C4":
                    if(validateText(R7C4.getText().toString())==false) {
                        R7C4.setText("");
                    } 
                    changeStyle(R7C4);
                    break;
                case "R7C5":
                    if(validateText(R7C5.getText().toString())==false) {
                        R7C5.setText("");
                    } 
                    changeStyle(R7C5);
                    break;
                case "R7C6":
                    if(validateText(R7C6.getText().toString())==false) {
                        R7C6.setText("");
                    } 
                    changeStyle(R7C6);
                    break;
                case "R7C7":
                    if(validateText(R7C7.getText().toString())==false) {
                        R7C7.setText("");
                    } 
                    changeStyle(R7C7);
                    break;
                case "R7C8":
                    if(validateText(R7C8.getText().toString())==false) {
                        R7C8.setText("");
                    } 
                    changeStyle(R7C8);
                    break;
                case "R7C9":
                    if(validateText(R7C9.getText().toString())==false) {
                        R1C1.setText("");
                    } 
                    changeStyle(R7C9);
                    break;       
                    
                case "R8C1":
                    if(validateText(R8C1.getText().toString())==false) {
                        R8C1.setText("");
                    } 
                    changeStyle(R8C1);
                    break;
                case "R8C2":
                    if(validateText(R8C2.getText().toString())==false) {
                        R8C2.setText("");
                    } 
                    changeStyle(R8C2);
                    break;
                case "R8C3":
                    if(validateText(R8C3.getText().toString())==false) {
                        R8C3.setText("");
                    } 
                    changeStyle(R8C3);
                    break;
                case "R8C4":
                    if(validateText(R8C4.getText().toString())==false) {
                        R8C4.setText("");
                    } 
                    changeStyle(R8C4);
                    break;
                case "R8C5":
                    if(validateText(R8C5.getText().toString())==false) {
                        R8C5.setText("");
                    } 
                    changeStyle(R8C5);
                    break;
                case "R8C6":
                    if(validateText(R8C6.getText().toString())==false) {
                        R8C6.setText("");
                    } 
                    changeStyle(R8C6);
                    break;
                case "R8C7":
                    if(validateText(R8C7.getText().toString())==false) {
                        R8C7.setText("");
                    } 
                    changeStyle(R8C7);
                    break;
                case "R8C8":
                    if(validateText(R8C8.getText().toString())==false) {
                        R8C8.setText("");
                    } 
                    changeStyle(R8C8);
                    break;
                case "R8C9":
                    if(validateText(R8C9.getText().toString())==false) {
                        R1C1.setText("");
                    } 
                    changeStyle(R8C9);
                    break;       
                    
                case "R9C1":
                    if(validateText(R9C1.getText().toString())==false) {
                        R9C1.setText("");
                    } 
                    changeStyle(R9C1);
                    break;
                case "R9C2":
                    if(validateText(R9C2.getText().toString())==false) {
                        R9C2.setText("");
                    } 
                    changeStyle(R9C2);
                    break;
                case "R9C3":
                    if(validateText(R9C3.getText().toString())==false) {
                        R9C3.setText("");
                    } 
                    changeStyle(R9C3);
                    break;
                case "R9C4":
                    if(validateText(R9C4.getText().toString())==false) {
                        R9C4.setText("");
                    } 
                    changeStyle(R9C4);
                    break;
                case "R9C5":
                    if(validateText(R9C5.getText().toString())==false) {
                        R9C5.setText("");
                    } 
                    changeStyle(R9C5);
                    break;
                case "R9C6":
                    if(validateText(R9C6.getText().toString())==false) {
                        R9C6.setText("");
                    } 
                    changeStyle(R9C6);
                    break;
                case "R9C7":
                    if(validateText(R9C7.getText().toString())==false) {
                        R9C7.setText("");
                    } 
                    changeStyle(R9C7);
                    break;
                case "R9C8":
                    if(validateText(R9C8.getText().toString())==false) {
                        R9C8.setText("");
                    } 
                    changeStyle(R9C8);
                    break;
                case "R9C9":
                    if(validateText(R9C9.getText().toString())==false) {
                        R1C1.setText("");
                    } 
                    changeStyle(R9C9);
                    break;       

            
            }
        }

        private void changeStyle(TextField cell) {
            if(!hasGameStarted){
                if(cell.getText().isEmpty()) {
                    cell.setStyle("-fx-background-color:  white; -fx-border-color: silver; -fx-text-fill: black; -fx-font-size: 17;");
                } else {
                    cell.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
                }                
            }
        }        

        private boolean validateText(String strContent) {
            int intValue = 0;
            if(strContent.length() > 0) {
                try {
                    intValue = Integer.parseInt(strContent);
                    if(intValue < 1 || intValue > 9) {
                        JOptionPane.showMessageDialog(null, "Values can only between 1- 9", "Incorrect value", JOptionPane.ERROR_MESSAGE);                
                        return false;
                    }
                    return true;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Incorrect value entered: " + e.getMessage(),"Incorrect value", JOptionPane.ERROR_MESSAGE); 
                    return false;
                }                
            }
            return true;
        }
    }

    private void populateOptions() {
        String strStyle = "";
        boolean [] blnOptions = new boolean[9];  // This list will feed the observable list 
                         // for list view. It will show possible values.
        ObservableList<Integer> options = FXCollections.observableArrayList();

        // reset the boolean array so that in subsequent uses, it does not provide misleading results

        int intValue = 0;
        boolean blnBlankCell = true;
        options.removeAll();         

        for (TextField t : target) {
            for(int i = 0; i < strTargetCells.length; i++) {
                if(t.getId().contains(strTargetCells[i])) {
                    strStyle = t.getStyle();
                    if(!strStyle.contains("-fx-background-color: white;")) {
                        try {
                           intValue = Integer.parseInt(t.getText());
                           blnBlankCell = false;
                        } catch (NumberFormatException nfe) {
                            System.err.println("Number Format Exception: " + nfe.getMessage());
                            intValue=0;
                            blnBlankCell = true;
                        }
                        if(intValue != 0 && blnBlankCell == false) {
                            blnOptions[intValue-1] = true;
                        }                           
                    }
                    break;
                }                                    
            }
        }
        
        for (int i = 0; i < 9; i++) {
            if(!blnOptions[i]) {
                options.add(i + 1);
            }
        }
        lst_options.getItems().clear();
        lst_options.setItems(options);  
    }    
}

