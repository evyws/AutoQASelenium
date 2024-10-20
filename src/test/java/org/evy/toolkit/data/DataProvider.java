package org.evy.toolkit.data;

import com.github.javafaker.Faker;

/**
 * Data supplier Class
 */
public final class DataProvider {

    private static final Faker faker =new Faker();
    private DataProvider(){}


    @org.testng.annotations.DataProvider(name = "productDropdownData")
    public static Object[][]getProductCategoriesData(){
        return new Object[][]{
                {"Caps","Simple","taxons/caps/simple"},
                {"Caps","With pompons","taxons/caps/with-pompons"},
                {"Dresses","","taxons/fashion-category/dresses"},
                {"Jeans","Men","taxons/jeans/men"},
                {"Jeans","Women","taxons/jeans/women"},
                {"T-Shirts","Men","taxons/t-shirts/men"},
                {"T-Shirts","Women","taxons/t-shirts/women"},

        };
    }

    @org.testng.annotations.DataProvider(name = "productNameData")
    public static Object[][]getProductNameData(){
        return new Object[][]{
                {"Caps","Simple","Beautiful cap for woman","Beautiful cap for woman"},
                {"Caps","Simple","Simple cap","Simple cap"},
                {"Caps","With pompons","Basic winter hot cap","Basic winter hot cap"},
                {"Caps","With pompons","Regular cap with big pompon","Regular cap with big pompon"},
                {"Dresses","","Circle-skirt Dress","Circle-skirt Dress"},
                {"Dresses","","Sleeveless Dress","Sleeveless Dress"},
                {"Dresses","","Summer tunic","Summer tunic"},
                {"Jeans","Men","Basic regular","Basic regular"},
                {"Jeans","Men","Slim fit classic","Slim fit classic"},
                {"Jeans","Men","Regular Fit casual","Regular Fit casual"},
                {"Jeans","Men","Slim fit elegant","Slim fit elegant"},
                {"Jeans","Women","Ultra slim","Ultra slim"},
                {"Jeans","Women","Slim fit","Slim fit"},
                {"Jeans","Women","New age regular","New age regular"},
                {"Jeans","Women","Whole holes classic","Whole holes classic"},
                {"T-Shirts","Men","Slim fit men","Slim fit men"},
                {"T-Shirts","Men","Regular fit men","Regular fit men"},
                {"T-Shirts","Men","Slim fit V-neck men","Slim fit V-neck men"},
                {"T-Shirts","Women","Basic regular woman","Basic regular woman"},
                {"T-Shirts","Women","Slim fit woman","Slim fit woman"},
                {"T-Shirts","Women","Regular Fit V-neck woman","Regular Fit V-neck woman"},





        };
    }

    @org.testng.annotations.DataProvider(name = "productAttributeData")
    public static Object[][] getProductAttributeData(){
        return new Object[][]{
                {"T-Shirts","Men","Slim fit men","S","1","Item has been added to cart"},
                {"T-Shirts","Men","Slim fit men","M","1","Item has been added to cart"},
                {"T-Shirts","Men","Slim fit men","L","1","Item has been added to cart"},
                {"T-Shirts","Men","Slim fit men","XL","1","Item has been added to cart"},
                {"T-Shirts","Men","Slim fit men","XXL","1","Item has been added to cart"},

        };
    }

    @org.testng.annotations.DataProvider(name = "loginData")
    public static Object[][] getLoginData(){
        return new Object[][]{
                {"fashion@example.com","sylius","valid"},
                {"fashion@example.com","invalidPassword","invalid"},
                {"invalidEmail@example.com","sylius","invalid"},
                {"invalidEmail@example.com","invalidPassword","invalid"},
                {"","sylius","empty"},
                {"fashion@example.com","","empty"},
                {"","","empty"}
        };
    }

    @org.testng.annotations.DataProvider(name = "registrationData")
    public static Object[][]getRegistrationData(){
        String password=faker.internet().password(8,10,true);
        return new Object[][]{
                {faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),password,password,"valid","Thank you for registering, check your email to verify your account."},
                {faker.name().firstName(),faker.name().lastName(),"fashion@example.com",password,password,"invalid","This email is already used."},
                {faker.name().firstName(),faker.name().lastName(),"aa@example",password,password,"invalid","This email is invalid."},
                {faker.name().firstName(),faker.name().lastName(),"@example.com",password,password,"invalid","This email is invalid."},
                {faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),"123456","password","invalid","The entered passwords don't match"},
                {faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),"123","123","invalid","Password must be at least 4 characters long."},
                {"",faker.name().lastName(),"fashion@example.com",password,password,"invalid","Please enter your first name."},
                {faker.name().firstName(),"","fashion@example.com",password,password,"invalid","Please enter your last name."},
                {faker.name().firstName(),faker.name().lastName(),"",password,password,"invalid","Please enter your email."},
                {faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),"","","invalid","Please enter your password."},


        };
    }
}
