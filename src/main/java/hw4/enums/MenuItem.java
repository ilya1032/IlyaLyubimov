package hw4.enums;

public enum MenuItem {

    MY_VIEW("My View"),
    VIEW_ISSUES("View Issues"),
    REPORT_ISSUES("Report Issue"),
    MANAGE("Manage");

    private String itemName;

    MenuItem(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }
}
