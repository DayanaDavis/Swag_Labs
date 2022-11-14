package com.swagLabs.utilities;

import com.github.javafaker.Faker;

public class RandomUtility {
    Faker faker=new Faker();
    public String userName(){
        String name=faker.name().username();
        return name;
    }
    public String passWord(){
        String password=faker.internet().password();
        return password;
    }
    public int randomDigit(int min,int max){
        int num=faker.number().numberBetween(min,max);
        return num;
    }
}
