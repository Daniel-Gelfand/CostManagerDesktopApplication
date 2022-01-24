package il.ac.hit;


/**
 * 'Category' :
 *
 * This Class encapsulate all details about Category.
 *
 * Methods:
 *  (1) Category(String category),
 *  (2) getCategory(),
 *  (3) setCategory(String category).
 */
public class Category {

    //This member is name of category.
    private String category;



    /**
     * 'Category' is a constructor.
     * In this constructor we set category name into Category.
     * @param category category name
     */
    public Category(String category) {
        setCategory(category);
    }



    /**
     * 'getCategory' is a method that gets the category.
     * @return category
     */
    public String getCategory() {
        return category;
    }



    /**
     * 'setCategory' is a method that sets the category.
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
