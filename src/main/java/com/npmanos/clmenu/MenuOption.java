package com.npmanos.clmenu;

class MenuOption {
    private int optionNum;
    private String description;
    private Runnable action;

    MenuOption(int optionNum, String description, Runnable action) {
        this.optionNum = optionNum;
        this.description = description;
        this.action = action;
    }

    public int getOptionNum() {
        return optionNum;
    }

    public String getDescription() {
        return description;
    }

    public Runnable getAction() {
        return action;
    }
}
