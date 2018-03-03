/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
//import static saxparser.StackParser.root;

/**
 *
 * @author daviddean
 */
public class SAXParserController implements Initializable {
    
    @FXML
    private Label label;
    
    Stage stage;
    
    public void ready(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open XML File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));
        File file = fileChooser.showOpenDialog(stage);
        
        XMLNode root;
        
        try{
            root = StackParser.parse(file);
        } catch (Exception e) {
            throw e;
        }
        
        System.out.println(root);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
