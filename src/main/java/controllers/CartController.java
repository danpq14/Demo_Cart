package controllers;


import model.Cart;
import model.CartItem;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.ProductService;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

@Controller
public class CartController {

    @Autowired
    HttpSession session;

    @Autowired
    ProductService productService;

    @RequestMapping("/addToCart/{id}")
    public String addToCart(@PathVariable Long id, Model model) {
        Object object = session.getAttribute("cart");
        Product product = productService.findProductByID(id);
        if (object == null) {
            Cart cart = new Cart();
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
//
            List<CartItem> list = new ArrayList<>();
            list.add(cartItem);
            cart.setCartItem(list);
            session.setAttribute("cart", cart);
            model.addAttribute("cart", cart);
        }
        else if (object != null){
            boolean isExistInCart = false;
            Cart cart = (Cart) object;
            List<CartItem> cartItemList = cart.getCartItem();
            for (CartItem item : cartItemList) {
                if (item.getProduct().getId() == product.getId()) {
                    item.setQuantity(item.getQuantity()+1);
                    cart.setCartItem(cartItemList);
                    session.setAttribute("cart", cart);
                    isExistInCart = true;
                    break;
                }
            }
            if (!isExistInCart) {
                CartItem cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setQuantity(1);
                cartItemList.add(cartItem);
                cart.setCartItem(cartItemList);
            }
            session.setAttribute("cart", cart);
        }
        return "redirect:/showListProduct";
    }

    @GetMapping("/showCart")
    public String showCart(Model model){
        Object o = session.getAttribute("cart");
        Cart cart = (Cart) o;
        List<CartItem> list = cart.getCartItem();
        model.addAttribute("cartItemList", list);
        return "cart";
    }

    @RequestMapping("/updateCart")
    public String updateCart(@RequestParam(name="quanty") int[] quanty, Model model){
       Object o = session.getAttribute("cart");
       Cart cart = (Cart) o;
       List<CartItem> list = cart.getCartItem();
       for (int i =0; i< list.size(); i++) {
           list.get(i).setQuantity(quanty[i]);
       }
       cart.setCartItem(list);
       session.setAttribute("cart", cart);
       model.addAttribute("cartItemList", cart.getCartItem());
       return "cart";
    }
}
