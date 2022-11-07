package view.user;

import view.Menu;
import view.TBIView;

public class IndexView extends Menu {
    public IndexView() {
        super(new Menu.Item("List Movies", new MovieListView()),
                new Menu.Item("View Booking History", new TBIView()));
    }
}
