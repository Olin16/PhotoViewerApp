import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.stage.FileChooser;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import java.io.FileInputStream;

public class Main extends Application  {
    ArrayList<Image> images = new ArrayList();
int currentIndex;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("ImageView Experiment 1");
        currentIndex = 0;


        Image image1 = new Image(new FileInputStream("panda.png"));
        images.add(image1);

        Image image2 = new Image(new FileInputStream("dog.png"));
        images.add(image2);

        Image image3 = new Image(new FileInputStream("cat.png"));
        images.add(image3);

        Image image4 = new Image(new FileInputStream("redpanda1.jpeg"));
        images.add(image4);

        Image image5 = new Image(new FileInputStream("squirrel.png"));
        images.add(image5);

        int maximumIndex = images.size()-1;

        ImageView imageView = new ImageView(image1);

        FileChooser fileChooser = new FileChooser();

        Button button3 = new Button("Select File");
        button3.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            Image pop = null;
            try {
                pop = new Image(new FileInputStream(selectedFile));
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            images.add(pop);
            imageView.setImage(images.get(maximumIndex+1));

        });


        ChoiceBox choiceBox = new ChoiceBox();

        choiceBox.getItems().add("Panda");
        choiceBox.getItems().add("Dog");
        choiceBox.getItems().add("Cat");
        choiceBox.getItems().add("Red Panda");
        choiceBox.getItems().add("Squirrel");

        choiceBox.setOnAction((event) -> {
            int selectedIndex = choiceBox.getSelectionModel().getSelectedIndex();
            Object selectedItem = choiceBox.getSelectionModel().getSelectedItem();

            System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
            System.out.println("   ChoiceBox.getValue(): " + choiceBox.getValue());


            imageView.setImage(images.get(selectedIndex));
        });


        Button button1 = new Button("Foward");

        button1.setOnAction(actionEvent -> {
            currentIndex++;

            Image nextImage = images.get(currentIndex);
            imageView.setImage(nextImage);

            if(currentIndex == maximumIndex) {
                currentIndex = currentIndex - 1;
            }
            System.out.println(currentIndex);
        });


        Button button2 = new Button("Back");

        button2.setOnAction(actionEvent -> {
            currentIndex--;

            Image nextImage = images.get(currentIndex);
            imageView.setImage(nextImage);

            if(currentIndex == 0) {
                currentIndex = currentIndex + 1;
            }
            System.out.println(currentIndex);
        });

        HBox animals = new HBox(imageView, button1,button2, choiceBox, button3);

        Scene scene = new Scene(animals, 1400, 1400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
