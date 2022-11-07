package view;

import store.Store;

public class TBIView extends View {
    @Override
    public String display(Store store) {
        return "To Be Implemented";
    }

    @Override
    public String prompt(Store store) {
        return "Enter anything";
    }

    @Override
    public View handleInput(Store store, String input) {
        return new view.IndexView();
    }
}
