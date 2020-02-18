package com.npmanos.clmenu;

/**
 * POJO storing MenuOption data.
 */
class MenuOption {
    private int optionNum;
    private String description;
    private Runnable action;

    MenuOption(int optionNum, String description, Runnable action) {
        this.optionNum = optionNum;
        this.description = description;
        this.action = action;
    }

    /**
     * Get option number.
     */
    int getOptionNum() {
        return optionNum;
    }

    /**
     * Get option description.
     */
    String getDescription() {
        return description;
    }

    /**
     * Get runnable to execute when option is selected.
     */
    Runnable getAction() {
        return action;
    }
}
