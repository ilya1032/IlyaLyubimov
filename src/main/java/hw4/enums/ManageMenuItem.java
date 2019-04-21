package hw4.enums;

public enum ManageMenuItem {
    MANAGE_USERS("Manage Users"),
    MANAGE_PROJECTS("Manage Projects");

    private String itemName;

    ManageMenuItem(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }
}
