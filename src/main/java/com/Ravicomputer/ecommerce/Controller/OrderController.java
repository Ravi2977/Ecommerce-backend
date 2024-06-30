package com.Ravicomputer.ecommerce.Controller;

import com.Ravicomputer.ecommerce.Model.OrderModel;
import com.Ravicomputer.ecommerce.Model.OrdersModel;
import com.Ravicomputer.ecommerce.Model.Product;
import com.Ravicomputer.ecommerce.Repository.OrdersRepository;
import com.Ravicomputer.ecommerce.Repository.UserRepository;
import com.Ravicomputer.ecommerce.Services.AddressService;
import com.Ravicomputer.ecommerce.Services.EmailService;
import com.Ravicomputer.ecommerce.Services.OrderService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import jakarta.mail.MessagingException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

@RestController
@CrossOrigin(value = {"http://localhost:3000","https://ravi-computer-ecommerce.vercel.app"})
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AddressService addressService;
@Autowired
private EmailService emailService;
@Autowired
private UserRepository userRepository;
@Autowired
private OrdersRepository ordersRepository;
    @PostMapping("/addOrder")
    public ResponseEntity<OrderModel> addOrder(@RequestBody OrderModel orderModel) throws MessagingException, javax.mail.MessagingException {
        orderModel.setBillingAddress(addressService.getByUserId(orderModel.getUser().getId()));
        orderModel.setOrderDate(LocalDate.now());
        orderModel.setExpectedDeliveryDate(LocalDate.now().plusDays(10));
        orderModel.setNetPayableAmount(orderModel.getTotalAmount()+orderModel.getTotalAmount()*18/100);
        Set<Product> productsSet = new HashSet<>(orderModel.getProducts());
        String message  ="Your Order is placed it will delivered to you within 10 days your ordered Items are ";
        String messageToShopkeepr =" You have received an order of products be prepare on time. Delivery Address is "+orderModel.getBillingAddress().getLocality()+","+orderModel.getBillingAddress().getPinCode()+","+" and ordered items are";
        ArrayList<String> imageLink=new ArrayList<>();
        System.out.println(productsSet.size());
        for(Product product: productsSet){
            int count=0;
            for(int i=0;i<orderModel.getProducts().size();i++){
                if(orderModel.getProducts().get(i).getProductId()==product.getProductId()){
                    count++;
                }
            }
            message+=product.getProductName()+","+"of quantity "+count+",";
            messageToShopkeepr+=product.getProductName()+","+"of quantity "+count+",";
            imageLink.add(product.getProductImgUrl());
        }

        emailService.sendEmail("mauryaravi599@gmail.com","TEst Mail",message);
        emailService.sendEmailWithEmbeddedImages("mauryaravi599@gmail.com","Image testing",imageLink,message);
        emailService.sendEmailWithEmbeddedImages("ravicomputercompany@gmail.com","New Order",imageLink,messageToShopkeepr);
        return orderService.addOrder(orderModel);
    }

    @GetMapping("/byUserId/{id}")
    public List<OrderModel> getByUserId(@PathVariable long id){
        List<OrderModel> orders=orderService.getByUserId(id);
        for(int i=0;i<orders.size();i++){
            orders.get(i).getUser().setOrders(null);
            for(int j=0;j<orders.get(i).getProducts().size();j++){
                orders.get(i).getProducts().get(j).setProductReview(null);
            }
        }

        return orders;
    }
    @PostMapping("/create_order")
    public String createOrder(@RequestBody  Map<String, Object> order) throws RazorpayException {
        int amt = Integer.parseInt(order.get("amount").toString());
        RazorpayClient client = new RazorpayClient("rzp_test_daKBtgff3GpV4I", "Szf9Sb91O1eDFyyMHgV1aDoM");

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amt * 100);
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "txn_235425");

        // Creating order
        Order razorpayOrder = client.orders.create(orderRequest);

        // Save order details to the database
        OrdersModel ordersModel = new OrdersModel();
        ordersModel.setUser(userRepository.findByEmail(order.get("email").toString()));
        ordersModel.setOrderId(razorpayOrder.get("id"));
        ordersModel.setStatus("created");
        ordersModel.setAmount(razorpayOrder.get("amount").toString());
        ordersModel.setReceipt(razorpayOrder.get("receipt"));
        ordersRepository.save(ordersModel);

        return razorpayOrder.toString();
    }
}
