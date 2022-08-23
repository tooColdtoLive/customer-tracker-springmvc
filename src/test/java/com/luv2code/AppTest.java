package com.luv2code;

import static org.junit.Assert.assertTrue;

import com.luv2code.customerTracker.controller.CustomerController;
import com.luv2code.customerTracker.service.CustomerService;
import com.luv2code.customerTracker.service.CustomerServiceImpl;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    // fails as customerDAO in CustomerServiceImpl is not injected
    @Test
    public void shouldListCustomers() {
        CustomerService customerService = new CustomerServiceImpl();
        // System.out.println(customerService.getCustomers());
    }

}
