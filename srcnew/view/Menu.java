package view;

import store.Store;

public class Menu extends View {
    public static class Item {
        public String displayString;
        public View view;

        public Item(String displayString, View view) {
            this.displayString = displayString;
            this.view = view;
        }

        @Override
        public String toString() {
            return this.displayString;
        }
    }

    private Item[] items;

    public Menu(Item... items) {
        this.items = items;
    }

    @Override
    public String display(Store store) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.items.length; i++) {
            sb.append(String.format("%d. %s\n", i + 1, this.items[i]));
        }
        return sb.toString();
    }

    @Override
    public String prompt(Store store) {
        return String.format("Enter a number between 1 and %d", this.items.length);
    }

    @Override
    public View handleInput(Store store, String input) {
        int idx = this.parseIndex(input, 1, this.items.length);
        return this.items[idx - 1].view;
    }
}
