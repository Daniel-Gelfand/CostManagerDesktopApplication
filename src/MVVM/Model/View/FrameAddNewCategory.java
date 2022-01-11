package MVVM.Model.View;

import MVVM.Model.Category;
import MVVM.Model.IViewModel;

import javax.swing.*;
import java.awt.*;

public class FrameAddNewCategory {

    private JFrame frameAddCategory;
    private JPanel panelAddCategory;
    private JButton buttonAdd;
    private JTextField textFieldAdd;
    private IViewModel viewModel;

    public FrameAddNewCategory(IViewModel viewModel) {

        this.viewModel = viewModel;

        frameAddCategory = new JFrame();
        panelAddCategory = new JPanel();
        buttonAdd = new JButton("Add category");
        textFieldAdd = new JTextField(15);

        frameAddCategory.setSize(300, 100);
        frameAddCategory.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameAddCategory.setLayout(new BorderLayout());

        //panelAddCategory.setPreferredSize(new Dimension(100,100));
        panelAddCategory.setBackground(Color.BLUE);
        panelAddCategory.add(textFieldAdd);
        panelAddCategory.add(buttonAdd);

        buttonAdd.addActionListener(e -> addNewCategory());

        frameAddCategory.add(panelAddCategory, BorderLayout.CENTER);
        frameAddCategory.setVisible(true);


    }

    private void addNewCategory() {

        Category category = new Category(textFieldAdd.getText());
        viewModel.addNewCategory(category);
    }

    public void toDispose()
    {
        frameAddCategory.dispose();
    }

    public static void main(String[] args) {

    }
}
