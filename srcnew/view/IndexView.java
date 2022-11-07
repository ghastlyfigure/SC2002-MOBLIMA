package view;

public class IndexView extends Menu {
    public IndexView() {
        super(new Menu.Item("Admin Login", new LoginView()),
                new Menu.Item("Movie-goer page", new view.user.IndexView()));
    }
}
