package pop;

/**
 * Created by leet on 18-7-27.
 */

public class MenuItem {
    public int iconResId;
    public int itemId;
    public String itemTitle;

    public MenuItem(int itemId, String itemTitle) {
        this.itemId = itemId;
        this.itemTitle = itemTitle;
    }

    public MenuItem(int iconResId, int itemId, String itemTitle) {
        this.iconResId = iconResId;
        this.itemId = itemId;
        this.itemTitle = itemTitle;
    }

    public int getIconResId() {
        return iconResId;
    }

    public int getItemId() {
        return itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }
}