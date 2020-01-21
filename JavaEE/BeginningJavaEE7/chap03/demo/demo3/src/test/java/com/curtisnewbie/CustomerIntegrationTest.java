package com.curtisnewbie;

import com.curtisnewbie.model.Customer;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;


public class CustomerIntegrationTest {

    private static Validator validator;
    private static ValidatorFactory factory;

    @BeforeClass
    public static void init(){
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterClass
    public static void close(){
        factory.close();
    }

    @Test
    public void shouldRaiseConstraintViolationDueToInvalidEmail(){

        Customer customer = new Customer();
        customer.setFirstName("Newbie");
        customer.setLastName("Curtis");
        customer.setEmail("InvalidEmailBlablabla");
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        Assert.assertTrue("Should cause constraint violation", !violations.isEmpty());
    }

    @Test
    public void shouldNotRaiseConstraintViolation(){
        Customer customer = new Customer();
        customer.setFirstName("Newbie");
        customer.setLastName("Curtis");
        customer.setEmail("curtisnewbie@gmail.com");
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        Assert.assertTrue("Should not cause constraint violation", violations.isEmpty());
    }
}
