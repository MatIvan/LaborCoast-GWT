package ru.mativ.client.form.notes.widgets.input;

import com.google.gwt.user.client.ui.CellPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public enum LabeledInputBoxType {
    VERTICAL_TEXT {
        @Override
        public CellPanel getPanel() {
            return new VerticalPanel();
        }

        @Override
        public TextBox getInput() {
            return new TextBox();
        }
    },
    HORIZONTAL_TEXT {
        @Override
        public CellPanel getPanel() {
            return new HorizontalPanel();
        }

        @Override
        public TextBox getInput() {
            return new TextBox();
        }
    },
    VERTICAL_PASS {
        @Override
        public CellPanel getPanel() {
            return new VerticalPanel();
        }

        @Override
        public TextBox getInput() {
            return new PasswordTextBox();
        }
    },
    HORIZONTAL_PASS {
        @Override
        public CellPanel getPanel() {
            return new HorizontalPanel();
        }

        @Override
        public TextBox getInput() {
            return new PasswordTextBox();
        }
    };

    public abstract CellPanel getPanel();

    public abstract TextBox getInput();
}
