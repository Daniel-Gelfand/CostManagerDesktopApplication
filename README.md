
![Logo](https://www.linkpicture.com/q/facebook_cover_photo_1.png
)


# Project Title

CostManager desktop application that allows its users to track their expenses. The application should allow its users the following operations:

1. Adding a new cost while specifying the category to which that cost will be added, the sum, the currency, and a small text describing that cost.
2. Adding new categories to a small list of categories that should be already defined.
3. Getting a detailed report that lists all costs in a specific period of time the user selects.

## The Architecture - MVVM design pattern

- The methods we define in IViewModel should return void, and their signature cannot include a throws clause. They should be asynchronous methods.
- The methods we define in IModel should include throws clause in their signature. That throws clause should use the specific exception type that was defined for our project. -   They shouldn’t include throws for other exception types. (code: MVVM2)
- The implementation of the methods that were defined in IViewModel include the use of try and catch when calling methods on the IModel object, and in case of exception the       catch block includes a call for showing the proper message to the user.
- Whenever the implementation of the methods that were defined in IViewModel include code that interacts with the IView, that interaction should be performed using the           
  SwingUtilities.invokeLater() method.
- The View object should hold the reference for the ViewModel object. The ViewModel object should hold the references for the View object and the Model object.
- The UI developed using a LayoutManager object that controls the size and the location of every component. The only exception for this guideline can be small dialog/frame   
  windows (e.g. login small window).



## Preview

![alt text](https://www.linkpicture.com/q/register.jpg)

![alt text](https://www.linkpicture.com/q/login_3.jpg)

![alt text](https://www.linkpicture.com/q/mainmenu_1.jpg)

![alt text](https://www.linkpicture.com/q/addnewcost.jpg)

![alt text](https://www.linkpicture.com/q/AddNewCategory.jpg)

![alt text](https://www.linkpicture.com/q/Reports.jpg)



## Authors

- [@Daniel-Gelfand](https://github.com/Daniel-Gelfand)
- [@matanbare](https://github.com/matanbare)

## Clone 

```bash
  git clone https://github.com/Daniel-Gelfand/CostManagerDesktopApplication.git
```

## 2021
