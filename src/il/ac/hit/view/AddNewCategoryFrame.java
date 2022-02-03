package il.ac.hit.view;

import il.ac.hit.model.Account;
import il.ac.hit.model.Category;
import il.ac.hit.IViewModel;

import javax.swing.*;
import java.awt.*;

/**
 * 'FrameAddNewCategory' :
 * This Class is AddNewCategory GUI frame screen .
 *
 * Methods:
 * (1) FrameAddNewCategory(IViewModel viewModel, Account account),
 * (2) initialization(),
 * (3) addNewCategory(),
 * (4) toDispose().
 *
 */
public class AddNewCategoryFrame {

    // AddNewCategory frame members
    private JFrame frameAddCategory;
    private JPanel panelAddCategory;
    private JButton buttonAdd;
    private JTextField textFieldAdd;
    private IViewModel viewModel;
    private Account account;

    public AddNewCategoryFrame(IViewModel viewModel, Account account) {

        // set the view model and the account that make request to add new category
        setViewModel(viewModel);
        setAccount(account);
        //init the frame and is controllers
        initialization();
    }

    /**
     * 'setIViewModel' is a method that sets IViewModel (MVVM Architecture)
     * @param viewModel vm
     */
    public void setViewModel(IViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * 'stAccount' is a method that sets account member
     * @param account account
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     *  'initialization()'
     * This method is initialization the frame and controllers.
     */
    private void initialization()
    {
        // set the frame and his controllers
        frameAddCategory = new JFrame("Add new category");
        panelAddCategory = new JPanel();
        textFieldAdd = new JTextField(15);

        // set the add new category button action and title
        buttonAdd = new JButton("Add category");
        buttonAdd.addActionListener(e -> addNewCategory());
        buttonAdd.setBackground(new Color(183,244,216));

        // add image icon to the frame
        Image icon = Toolkit.getDefaultToolkit().getImage("src/il/ac/hit/images/CostManagerIcon.jpg");
        frameAddCategory.setIconImage(icon);

        //set the panel color and adding him the right controllers
        panelAddCategory.setBackground(Color.BLUE);
        panelAddCategory.add(textFieldAdd);
        panelAddCategory.add(buttonAdd);

        //set frame details and adding to him the right controllers
        frameAddCategory.setSize(300, 100);
        frameAddCategory.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameAddCategory.setLayout(new BorderLayout());
        frameAddCategory.add(panelAddCategory, BorderLayout.CENTER);
        frameAddCategory.setLocationRelativeTo(null);
        frameAddCategory.setResizable(false);
        frameAddCategory.setVisible(true);
    }

    /**
     * 'addNewCategory()'
     * This method create category by user selection and then
     * ask from the view model to make turn to the model for adding the category.
     */
    private void addNewCategory() {
        Category category = new Category(textFieldAdd.getText());
        viewModel.addNewCategory(category, account);
    }

    /**
     * 'toDispose()'
     * This method help for the view to dispose the frame.
     */
    public void toDispose()
    {
        frameAddCategory.dispose();
    }

}
