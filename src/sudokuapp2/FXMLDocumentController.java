/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokuapp2;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 *
 * @author Chakravarty
 */
public class FXMLDocumentController implements Initializable {
    
    private boolean hasGameStarted;
    
    // PANE
    @FXML
    private Pane mainPane;

    @FXML
    private ComboBox<String> cmb_selectMethod;
    
    // ROW 1
    @FXML
    private TextField R1C1;
    @FXML
    private TextField R1C2;
    @FXML
    private TextField R1C3;
    @FXML
    private TextField R1C4;
    @FXML
    private TextField R1C5;
    @FXML
    private TextField R1C6;
    @FXML
    private TextField R1C7;
    @FXML
    private TextField R1C8;
    @FXML
    private TextField R1C9;

    // ROW 2
    @FXML
    private TextField R2C1;
    @FXML
    private TextField R2C2;
    @FXML
    private TextField R2C3;
    @FXML
    private TextField R2C4;
    @FXML
    private TextField R2C5;
    @FXML
    private TextField R2C6;
    @FXML
    private TextField R2C7;
    @FXML
    private TextField R2C8;
    @FXML
    private TextField R2C9;
    
    // ROW 3
    @FXML
    private TextField R3C1;
    @FXML
    private TextField R3C2;
    @FXML
    private TextField R3C3;
    @FXML
    private TextField R3C4;
    @FXML
    private TextField R3C5;
    @FXML
    private TextField R3C6;
    @FXML
    private TextField R3C7;
    @FXML
    private TextField R3C8;
    @FXML
    private TextField R3C9;
    
    // ROW 4
    @FXML
    private TextField R4C1;
    @FXML
    private TextField R4C2;
    @FXML
    private TextField R4C3;
    @FXML
    private TextField R4C4;
    @FXML
    private TextField R4C5;
    @FXML
    private TextField R4C6;
    @FXML
    private TextField R4C7;
    @FXML
    private TextField R4C8;
    @FXML
    private TextField R4C9;

    // ROW 5
    @FXML
    private TextField R5C1;
    @FXML
    private TextField R5C2;
    @FXML
    private TextField R5C3;
    @FXML
    private TextField R5C4;
    @FXML
    private TextField R5C5;
    @FXML
    private TextField R5C6;
    @FXML
    private TextField R5C7;
    @FXML
    private TextField R5C8;
    @FXML
    private TextField R5C9;    
    
    // ROW 6
    @FXML
    private TextField R6C1;
    @FXML
    private TextField R6C2;
    @FXML
    private TextField R6C3;
    @FXML
    private TextField R6C4;
    @FXML
    private TextField R6C5;
    @FXML
    private TextField R6C6;
    @FXML
    private TextField R6C7;
    @FXML
    private TextField R6C8;
    @FXML
    private TextField R6C9;    

    // ROW 7
    @FXML
    private TextField R7C1;
    @FXML
    private TextField R7C2;
    @FXML
    private TextField R7C3;
    @FXML
    private TextField R7C4;
    @FXML
    private TextField R7C5;
    @FXML
    private TextField R7C6;
    @FXML
    private TextField R7C7;
    @FXML
    private TextField R7C8;
    @FXML
    private TextField R7C9;        

    // ROW 8
    @FXML
    private TextField R8C1;
    @FXML
    private TextField R8C2;
    @FXML
    private TextField R8C3;
    @FXML
    private TextField R8C4;
    @FXML
    private TextField R8C5;
    @FXML
    private TextField R8C6;
    @FXML
    private TextField R8C7;
    @FXML
    private TextField R8C8;
    @FXML
    private TextField R8C9;          

    // ROW 9
    @FXML
    private TextField R9C1;
    @FXML
    private TextField R9C2;
    @FXML
    private TextField R9C3;
    @FXML
    private TextField R9C4;
    @FXML
    private TextField R9C5;
    @FXML
    private TextField R9C6;
    @FXML
    private TextField R9C7;
    @FXML
    private TextField R9C8;
    @FXML
    private TextField R9C9;          

   
    @FXML
    private void startGame(Event e) {
        // Check to make sure Select Method has been selected ...
        hasGameStarted = true;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hasGameStarted = false;
                
        R1C1.addEventHandler(MouseEvent.MOUSE_EXITED, new MouseExitedEventHandler());
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

        List<TextField> myCells = getNodesOfType(mainPane, TextField.class);
        Iterator<TextField> it = myCells.iterator();
        while (it.hasNext()) {
            String text = it.next().getText().toString();
            if(text.length()>0) {
                System.out.println("Content: " + text.toString());                
            }
        }
        
       String[] strOptions = {
           "Manual",
           "Simple",
           "AI"
       };
        try {
            cmb_selectMethod.getItems().addAll(strOptions);            
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
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
    
    /**
     * This inner class adds an event handler to each of the text boxes so that when
     * they are clicked, the fx:id of the text box is shown below
     */
    private class MouseExitedEventHandler implements EventHandler<Event> {
        @Override
        public void handle(Event e) {
            System.out.println("Cell: " + ((Control)e.getSource()).getId());

            String controlName = ((Control)e.getSource()).getId().toString();
            switch (controlName) {
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
            if(cell.getText().isEmpty()) {
                cell.setStyle("-fx-background-color:  white; -fx-border-color: silver; -fx-text-fill: black; -fx-font-size: 17;");
            } else {
                cell.setStyle("-fx-background-color:  RGB(229,231,231); -fx-border-color: silver; -fx-text-fill: red; -fx-font-size: 20;");
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
}

