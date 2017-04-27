package com.guigarage.shell;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;
import jdk.jshell.VarSnippet;

import java.util.List;


public class ShellFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        JShell shell = JShell.builder().build();

        TextField textField = new TextField();
        Button evalButton = new Button("eval");
        ListView<String> listView = new ListView<>();

        evalButton.setOnAction(e -> {
            List<SnippetEvent> events = shell.eval(textField.getText());
            events.stream().map(event -> convert(event)).filter(s -> s != null).forEach(s -> listView.getItems().add(s));
        });

        BorderPane pane = new BorderPane();
        pane.setTop(new HBox(textField, evalButton));
        pane.setCenter(listView);
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static String convert(SnippetEvent e) {
        if (e.snippet() instanceof VarSnippet) {
            return ((VarSnippet) e.snippet()).typeName() + " " + ((VarSnippet) e.snippet()).name() + " " + e.value();
        }
        return null;
    }

    public static void main(String[] args) {
        launch();
    }
}
