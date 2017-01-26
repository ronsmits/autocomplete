package no.tornadofx;

/**
 * Created by ronsmits on 26/01/2017.
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * This class is a TextField which implements an "autocomplete" functionality, Based on a {@link Function} to provide
 * the list of possible candidates
 *
 * @author Ron Smits
 * @author Caleb Brinkman
 */
public class AutoCompleteTextField extends TextField {
    /**
     * The popup used to select an entry.
     */
    private ContextMenu entriesPopup;
    private Function<String, List<String>> searcher;

    /**
     * Construct a new AutoCompleteTextField.
     *
     */
    public AutoCompleteTextField(Function<String, List<String>> searcher) {
        super();
        this.searcher = searcher;
        entriesPopup = new ContextMenu();
        textProperty().addListener((observableValue, s, s2) -> {
            if (getText().length() == 0) {
                entriesPopup.hide();
            } else {
                List<String> searchResult = this.searcher.apply(getText());
                if (searchResult.size() > 0) {
                    populatePopup(searchResult);
                    if (!entriesPopup.isShowing()) {
                        entriesPopup.show(AutoCompleteTextField.this, Side.BOTTOM, 0, 0);
                    }
                } else {
                    entriesPopup.hide();
                }
            }
        });

        focusedProperty().addListener((observableValue, aBoolean, aBoolean2) -> entriesPopup.hide());

    }


    /**
     * Populate the entry set with the given search results.  Display is limited to 10 entries, for performance.
     *
     * @param searchResult The set of matching strings.
     */
    private void populatePopup(List<String> searchResult) {
        List<CustomMenuItem> menuItems = new LinkedList<>();
        // If you'd like more entries, modify this line.
        int maxEntries = 10;
        int count = Math.min(searchResult.size(), maxEntries);
        for (int i = 0; i < count; i++) {
            final String result = searchResult.get(i);
            Label entryLabel = new Label(result);
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            item.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    setText(result);
                    entriesPopup.hide();
                }
            });
            menuItems.add(item);
        }
        entriesPopup.getItems().clear();
        entriesPopup.getItems().addAll(menuItems);

    }
}