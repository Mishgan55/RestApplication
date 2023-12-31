package khorsun.spring.FirstRestApplication.DTO;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PersonDTO {
    @Column(name = "name")
    @NotEmpty(message = "name should not be empty")
    @Size(min = 2,max = 30, message = "Name characters should be between 2 and 30")
    private String name;
    @Column(name="age")
    @Min(value = 0,message = "Age should be greater then 0")
    private int age;
    @Column(name = "email")
    @Email(message = "Enter valid email")
    @NotEmpty(message = "Email should not be empty")
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
