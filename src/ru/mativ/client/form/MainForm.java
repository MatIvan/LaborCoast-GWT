package ru.mativ.client.form;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.service.GreetingServiceAsync;
import ru.mativ.client.service.RequestService;

public class MainForm extends Composite {

    private static final GreetingServiceAsync greetingService = LaborCoast.getGreetingService();
    private static final RequestService requestService = RequestService.getInstance();

    public MainForm() {
        initWidget(build());
    }

    private Widget build() {
        VerticalPanel panel = new VerticalPanel();
        panel.add(new Label("Welcome !!!"));
        Button btn = new Button("Greet");
        final Label lbl = new Label();

        btn.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                requestService.send(greetingService.greetServer("Tiribonk", new AsyncCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        lbl.setText(result);
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        lbl.setText(caught.getMessage());
                    }
                }));
            }
        });

        panel.add(btn);
        panel.add(lbl);
        return panel;
    }
}
