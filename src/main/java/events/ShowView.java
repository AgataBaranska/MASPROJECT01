package events;

import gui.RootPaneController;

public class ShowView {

    private RootPaneController.View view;

    public ShowView(RootPaneController.View view) {
        this.view = view;
    }

    public RootPaneController.View getView() {
        return view;
    }



}
