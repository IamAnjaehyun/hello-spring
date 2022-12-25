package com.example.filter.dto;

import lombok.*;

// @Getter // GET메서드
// @Setter // SET메서드
// ㄴ> @Data 쓰면 안써도 됩니당
@Data //hashcode / get / set / toString
@NoArgsConstructor //기본생성자 = User()
@AllArgsConstructor //전체생성자 = User(String, int)
public class User {

    private String name;
    private int age;
}
