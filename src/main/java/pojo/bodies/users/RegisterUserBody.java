package pojo.bodies.users;

import lombok.Builder;

@Builder
public class RegisterUserBody {
    private String userName;
    private String emailAddress;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String country;
    private String state;
    private String city;
    private String zipCode;
    private String mobileNumber;
}
