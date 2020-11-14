package ru.mativ.client.form.home;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import ru.mativ.client.LaborCoast;
import ru.mativ.client.service.proxy.GreetingServiceProxy;
import ru.mativ.client.service.proxy.NoteServiceProxy;
import ru.mativ.shared.bean.NoteBean;

public class HomeForm extends Composite {
    private static final Logger Log = Logger.getLogger(HomeForm.class.getName());
    private static final GreetingServiceProxy greetingService = LaborCoast.getGreetingServiceProxy();
    private static final NoteServiceProxy noteService = LaborCoast.getNoteServiceProxy();

    public HomeForm() {
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
                greetingService.greetServer("Tiribonk", new AsyncCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        lbl.setText(result);
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        lbl.setText(caught.getMessage());
                    }
                });
            }
        });

        panel.add(btn);
        panel.add(lbl);
        panel.add(buildNoteServiceTest());
        return panel;
    }

    private Widget buildNoteServiceTest() {
        Button btn = new Button("getByMonth");
        btn.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                Date date = new Date();
                noteService.getByMonth(date, new AsyncCallback<List<NoteBean>>() {

                    @Override
                    public void onSuccess(List<NoteBean> result) {
                        for(NoteBean bean : result) {
                            Log.info(bean.toString());
                        }
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        Log.severe(caught.getMessage());
                    }
                });
            }
        });
        return btn;
    }
}
