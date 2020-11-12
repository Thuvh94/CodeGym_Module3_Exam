package controller;

import model.Category;
import model.Product;
import service.ICategoryServiceImpl;
import service.IProductServiceImpl;
import service.iCategoryService;
import service.iProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/Product")
public class ProductServlet extends HttpServlet {
    iProductService productService = new IProductServiceImpl();
    iCategoryService categoryService = new ICategoryServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createProduct(request, response);
                break;
            case "search":
                searchByName(request,response);
                break;
            case "edit":
                editProduct(request,response);
                break;
            default:

                break;
        }
    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "delete":
                deleteProduct(request,response);
                break;
            case "edit":
                showEditForm(request,response);
                break;
            default:
                displayProductList(request, response);
                break;
        }
    }


    private void displayProductList(HttpServletRequest request, HttpServletResponse response) {
        List<Product> productList = productService.findAll();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ListProduct.jsp");
        request.setAttribute("productList", productList);
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("CreateProduct.jsp");
        try {
            List<Category> categoryList = categoryService.findAll();
            request.setAttribute("categoryList",categoryList);
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("productName");
        String description = request.getParameter("productDesc");
        String color = request.getParameter("productColor");
        int price = Integer.parseInt(request.getParameter("productPrice"));
        int quantity = Integer.parseInt(request.getParameter("productQuantity"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        Category category = categoryService.findById(categoryId);
        Product product = new Product(name,price,quantity,color,description,category);
        productService.add(product);
        displayProductList(request,response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.delete(id);
        displayProductList(request,response);

    }

    private void searchByName(HttpServletRequest request, HttpServletResponse response) {
        String searchName = request.getParameter("searchName");
        System.out.println(searchName);
        List<Product> productList = productService.findByName(searchName);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ListProduct.jsp");
        request.setAttribute("productList", productList);
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("EditProduct.jsp");
        try {
            List<Category> categoryList = categoryService.findAll();
            request.setAttribute("product",product);
            request.setAttribute("categoryList",categoryList);
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("productName");
        String description = request.getParameter("productDesc");
        String color = request.getParameter("productColor");
        int price = Integer.parseInt(request.getParameter("productPrice"));
        int quantity = Integer.parseInt(request.getParameter("productQuantity"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        Category category = categoryService.findById(categoryId);
        Product product = new Product(name,price,quantity,color,description,category);
        productService.update(id,product);
        displayProductList(request,response);
    }

}

