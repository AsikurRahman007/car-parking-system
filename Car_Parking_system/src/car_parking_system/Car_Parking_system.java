/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package car_parking_system;

import java.net.URL;
   import javafx.application.Application;
   import javafx.fxml.FXMLLoader;
   import javafx.scene.Parent;
   import javafx.scene.Scene;
   import javafx.stage.Stage;

   public class Car_Parking_system extends Application {

       @Override
       public void start(Stage primaryStage) throws Exception {
           URL resource = getClass().getResource("FXMLDocument.fxml"); // Adjust file name if different
           if (resource == null) {
               throw new IllegalStateException("Dasboard.fxml not found in classpath");
           }
           Parent root = FXMLLoader.load(resource);
           primaryStage.setTitle("Car Parking System");
           primaryStage.setScene(new Scene(root));
           primaryStage.show();
       }

       public static void main(String[] args) {
           launch(args);
       }
   }
