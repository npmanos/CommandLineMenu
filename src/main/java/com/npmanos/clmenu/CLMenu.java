package com.npmanos.clmenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Collections.unmodifiableList;

/**
 * A simple interactive command line menu.
 */
public class CLMenu {
    private final String name;
    private final List<MenuOption> options;
    private final boolean isSubmenu;

    private Scanner in;

    private CLMenu(String name, List<MenuOption> options, boolean isSubmenu) {
        this.name = name;
        this.options = options;
        this.isSubmenu = isSubmenu;

        in = new Scanner(System.in); // Scanner using System.in for user input
    }

    private CLMenu(String name, List<MenuOption> options) {
        this(name, options, false);
    }

    /**
     * Displays menu options and prompts user to select.
     *
     * <p>When option is selected, runnable is executed and user is returned
     * to the menu. The menu is displayed repeatedly until user selects return/quit.
     */
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

            options.get(selection - 1).getAction().run();
        }
    }

    /**
     * Take user input, and ensure input is a number and one of the options.
     *
     * @return Selected option number
     */
    private int select() {
        System.out.println();
        System.out.print("Select an option: ");
        int selection;
        try {
            selection = Integer.parseInt(in.nextLine());
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

    /**
     * Build a new {@link CLMenu}.
     */
    public static final class Builder {
        private final ArrayList<MenuOption> options = new ArrayList<>();
        private final String menuName;

        /**
         * Create a new {@link CLMenu.Builder}.
         *
         * @param menuName The name to display in the header of the menu.
         */
        public Builder(String menuName) {
            this.menuName = menuName;
        }

        /**
         * Add an option to the menu.
         *
         * @param description Option title to display
         * @param action      {@link Runnable} to execute when option is selected
         * @return This builder for chaining
         */
        public Builder addOption(String description, Runnable action) {
            options.add(new MenuOption(options.size() + 1, description, action));
            return this;
        }

        /**
         * Add another {@link CLMenu} as a submenu. When user selects this option,
         * the submenu will be displayed. When user exits the submenu, they will
         * be returned to this menu.
         *
         * @param submenu {@link CLMenu} to add as a submenu
         * @return This builder for chaining
         */
        public Builder addSubmenu(CLMenu submenu) {
            CLMenu markedSubmenu = new CLMenu(submenu.name, submenu.options, true);
            options.add(new MenuOption(options.size() + 1, markedSubmenu.name, markedSubmenu::display));
            return this;
        }

        /**
         * Builds {@link CLMenu} with specified options.
         */
        public CLMenu build() {
            List<MenuOption> options = new ArrayList<>(this.options);

            return new CLMenu(menuName, unmodifiableList(options));
        }
    }

    private void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
