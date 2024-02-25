package com.example.guser.controller;
import javafx.scene.control.ListCell;

public class ColumnListViewCell extends ListCell<String> {

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
        } else {
            // Customize how the list view cell displays the item
            setText(item);
        }
    }
}
