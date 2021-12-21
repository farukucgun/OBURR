/**
 *@OmerFiratBekiroglu
 *java version 15.0.2
 */
package searchGUI;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchMenu {

    String url1 = "https://upload.wikimedia.org/wikipedia/commons/c/cc/Blackie_Lawless_of_W.A.S.P._in_performance_%282006%29.jpg";
    String url2 = "https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/9eca4562860609.5a9e4ce2021ba.jpg";
    Image imgx1 = new Image(url1);
    Image imgx2 = new Image(url2);

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private BorderPane borderPane;

    @FXML
    private ImageView img1,img2;

    @FXML
    private Label name1,name2;

    @FXML
    private Button searchExecutionButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private CheckBox sortTime;

    @FXML
    private Label time1,time2;

    @FXML
    void getSearch(ActionEvent event) {
        String str = searchTextField.getText();
        name1.setText(str);
        time1.setText(str);
        img1.setImage(imgx1);

        name2.setText(str);
        time2.setText(str);
        img2.setImage(imgx2);
    }

    @FXML
    void sortByTime(ActionEvent event) {

    }

    @FXML
    void openRecipe(Event event) {
        loadUI("recipiePage");
    }

    void loadUI (String ui){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui+".fxml"));
        } catch (IOException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
        borderPane.setCenter(root);
    }
}




