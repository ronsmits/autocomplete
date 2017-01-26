package no.tornadofx.testapp;

import java.io.InputStream;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import no.tornadofx.AutoCompleteTextField;

/**
 * Created by ronsmi on 1/26/2017.
 */
public class AutoCompleteTextFieldTestApp extends Application
{
    private JsonArray array;

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        array = readFile();

        Function<String, List<String>> searcher = (String enteredText) -> array
                .stream().map(it -> (JsonObject)it)
                .filter(listEntry -> listEntry.getString("name")
                    .toLowerCase()
                    .startsWith(enteredText.toLowerCase()))
                .map(o -> o.getString("name"))
                .collect(Collectors.toList());

                AutoCompleteTextField field = new AutoCompleteTextField(searcher);
                Scene scene = new Scene(field);
                primaryStage.setScene(scene);
                primaryStage.show();
    }

    private JsonArray readFile()
    {
        InputStream stream = getClass().getResourceAsStream("/countries.json");
        return Json.createReader(stream).readArray();
    }
}
