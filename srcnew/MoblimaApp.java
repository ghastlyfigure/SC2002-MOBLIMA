import store.Store;
import view.IndexView;
import view.TBIView;
import view.View;

import java.util.ArrayList;
import java.util.Scanner;

public class MoblimaApp {
    private static final String BACK_CMD = "-";
    private static final String HELP_MSG = String.format(
            "Welcome to MOBLIMA! At any point, enter the command '%s' to go to the previous view or Ctrl-C to exit the program",
            BACK_CMD);
    private static final String DIVIDER = "===============================================";
    private static final View DEFAULT_VIEW = new IndexView();

    public static void run() {
        System.out.println(HELP_MSG);

        Store store = new Store();
        Scanner scanner = new Scanner(System.in);
        ArrayList<View> viewHistory = new ArrayList<>();
        View currView = DEFAULT_VIEW;
        while (true) {
            System.out.println(DIVIDER);
            System.out.println(currView.display(store));
            System.out.println();
            System.out.printf("%s : ", currView.prompt(store));

            String input = scanner.nextLine();

            if (input.trim().equals(BACK_CMD)) {
                if (viewHistory.isEmpty()) {
                    break;
                }
                currView = viewHistory.get(viewHistory.size() - 1);
                viewHistory.remove(viewHistory.size() - 1);
            } else {
                View nextView;
                try {
                    nextView = currView.handleInput(store, input);
                } catch (IllegalArgumentException e) {
                    System.out.printf("\n!! %s !!\n", e.getMessage());
                    continue;
                }

                if (!nextView.equals(currView) && !(currView instanceof TBIView)) {
                    viewHistory.add(currView);
                }
                currView = nextView;
            }
        }
    }
}
