package view.admin;

import view.Menu;
import view.TBIView;

public class IndexView extends Menu {
    public IndexView() {
        super(new Menu.Item("Create movie listing", new CreateMovieView()),
                new Menu.Item("Update movie listing", new TBIView()),
                new Menu.Item("Remove movie listing", new TBIView()),
                new Menu.Item("Create showtime", new TBIView()),
                new Menu.Item("Update showtime", new TBIView()),
                new Menu.Item("Remove showtime", new TBIView()),
                new Menu.Item("Configure system settings", new TBIView()));
    }
}
