public class Category extends TaskItem {

    private String name;

    public Category(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return "Category: " + name;
    }
}
