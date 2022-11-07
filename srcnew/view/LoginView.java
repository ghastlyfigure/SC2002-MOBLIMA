package view;

import store.Store;

public class LoginView extends View {
    private static final String PASSWORD = "password";

    @Override
    public String display(Store store) {
        return "- Admins Only -";
    }

    @Override
    public String prompt(Store store) {
        return "Enter admin password";
    }

    @Override
    public View handleInput(Store store, String input) {
        if (input.trim().equals(PASSWORD)) {
            return new view.admin.IndexView();
        }

        throw new IllegalArgumentException("Invalid admin password");
    }
}
