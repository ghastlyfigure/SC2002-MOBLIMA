package view;

import store.Store;

public abstract class View {
    public abstract String display(Store store);

    public abstract String prompt(Store store);

    public abstract View handleInput(Store store, String input);

    @Override
    public boolean equals(Object other) {
       return this.getClass() == other.getClass();
    }

    public static int parseIndex(String input, int lo, int hi) {
        int idx;
        IllegalArgumentException e = new IllegalArgumentException(
                String.format("Invalid index provided, please enter a number between %d and %d", lo, hi));

        try {
            idx = Integer.parseInt(input.trim());
        } catch (NumberFormatException nfe) {
            throw e;
        }

        if (!(1 <= idx && idx <= hi)) {
            throw e;
        }

        return idx;
    }
}
