package com.example.validation.dto;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.List;


public class User {
    @NotBlank //띄어쓰지 안돼
    private String name;

    @Max(value = 90, message = "이거 안넣어도됨 메세지 넣고싶으면 여기넣기") //max age정함
    private int age;
//    @Email
//    private String email;
//    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx")
//    private String phoneNumber;
//    @YearMonth
//    private String reqYearMonth; //yyyymm

    @Valid // validation 검사하려면 valid 꼭 붙이자!!
    private List<Car> cars;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", cars=" + cars +
                '}';
    }
}
