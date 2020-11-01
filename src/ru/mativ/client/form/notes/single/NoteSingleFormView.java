package ru.mativ.client.form.notes.single;

public interface NoteSingleFormView {

    void showError(String errorText);

    void setData(NoteSingleFormModel model);

    void dataSaved(NoteSingleFormModel model);
}
