package com.ravi.apac.assignment.model;

import java.time.LocalDate;
import java.util.List;
 
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
 
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ravi.apac.assignment.validator.CommPreference;
 
@Entity
public class User {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
     
    @NotEmpty(message = "First name is required")
    private String firstName;
      
    @NotEmpty(message = "Last name is required")
    private String lastName;
      
    @NotEmpty(message = "Email is required")
    @Email
    private String email;
     
    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
             message="Mobile number is invalid")
    private String mobilePhone;
     
    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
     
    @NotEmpty(message = "Communication preference is required")
    @CommPreference
    private String commPreference;
     
    @ElementCollection
    private List<@NotEmpty String> mobileDevices;
 
    public long getId() {
        return id;
    }
 
    public void setId(long id) {
        this.id = id;
    }
 
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getMobilePhone() {
        return mobilePhone;
    }
 
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
 
    public LocalDate getBirthday() {
        return birthday;
    }
 
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
 
    public String getCommPreference() {
        return commPreference;
    }
 
    public void setCommPreference(String commPreference) {
        this.commPreference = commPreference;
    }
 
    public List<String> getMobileDevices() {
        return mobileDevices;
    }
 
    public void setMobileDevices(List<String> mobileDevices) {
        this.mobileDevices = mobileDevices;
    }
     
}
