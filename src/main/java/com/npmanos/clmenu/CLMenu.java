package com.npmanos.clmenu;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class CLMenu {
    private final String name;
    private final List<MenuOption> options;
    private final boolean isSubmenu;

    private Console in;

    private CLMenu(String name, List<MenuOption> options, boolean isSubmenu) {
        this.name = name;
        this.options = options;
        this.isSubmenu = isSubmenu;

        in = System.console();
    }

    private CLMenu(String name, List<MenuOption> options) {
        this(name, options, false);
    }

    public static final class Builder {
        private final ArrayList<MenuOption> options = new ArrayList<>();
        private final String menuName;

        public Builder(String menuName) {
            this.menuName = menuName;
        }

        public Builder addOption(String description, Runnable action) {
            options.add(new MenuOption(options.size() + 1, description, action));
            return this;
        }

        public Builder addSubmenu(CLMenu submenu) {
            CLMenu markedSubmenu = new CLMenu(submenu.name, submenu.options, true);
            options.add(new MenuOption(options.size() + 1, markedSubmenu.name, markedSubmenu::display));
            return this;
        }

        public CLMenu build() {
            List<MenuOption> options = new ArrayList<>(this.options);

            return new CLMenu(menuName, unmodifiableList(options));
        }
    }

    public void display() {
        while (true) {
            clear();
            System.out.println(name);
            System.out.println();

            for (MenuOption option : options) {
                System.out.println(option.getOptionNum() + ". " + option.getDescription());
            }
            if (isSubmenu) {
                System.out.println("0. Return");
            } else {
                System.out.println("0. Quit");
            }

            int selection = select();

            if (selection == 0) {
                return;
            }

            options.get(selection).getAction().run();
        }
    }

    private int select() {
        System.out.println();
        System.out.print("Select an option: ");
        int selection;
        try {
            selection = Integer.parseInt(in.readLine());
        } catch (NumberFormatException e) {
            System.out.println();
            System.out.println("INVALID SELECTION!");
            selection = select();
        }

        if (selection < 0 || selection > options.size()) {
            System.out.println();
            System.out.println("INVALID SELECTION!");
            selection = select();
        }

        return selection;
    }

    private void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
