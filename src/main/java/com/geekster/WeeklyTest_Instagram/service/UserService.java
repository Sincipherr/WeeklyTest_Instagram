package com.geekster.WeeklyTest_Instagram.service;

import com.geekster.WeeklyTest_Instagram.Repository.IUserRepo;
import com.geekster.WeeklyTest_Instagram.dto.SignInInput;
import com.geekster.WeeklyTest_Instagram.dto.SignUpInput;
import com.geekster.WeeklyTest_Instagram.model.AuthenticationToken;
import com.geekster.WeeklyTest_Instagram.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.xml.bind.DatatypeConverter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    IUserRepo ur;

    @Autowired
    AuthenticationService as;

    public String signup(SignUpInput signupdto) {
        User user=ur.findFirstByEmail(signupdto.getUserEmail());
        if(user!=null){
            throw new IllegalStateException("User already exist..Sign in instead..!");
        }
        String encryptedpassword=null;
        try {
            encryptedpassword=encryptedpassword(signupdto.getUserPassword());
        }catch (Exception e){
            e.printStackTrace();
        }
        user=new User(signupdto.getUserFirstName(),signupdto.getUserLastName(),
                signupdto.getUserAge(),signupdto.getUserEmail(),
                signupdto.getUserPhoneNumber(),encryptedpassword);
        ur.save(user);

        AuthenticationToken token=new AuthenticationToken(user);
        as.savetoken(token);
        return "User registered successfully";
    }

    private String encryptedpassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5= MessageDigest.getInstance("MD5");
        md5.update(userPassword.getBytes());
        byte[] digested=md5.digest();
        String hash= DatatypeConverter.printHexBinary(digested);
        return hash;
    }

    public String signin(SignInInput signindto) {
        User user=ur.findFirstByEmail(signindto.getUserEmail());
        if(user==null){
            throw new IllegalStateException("Invalid user..Sign up instead..");
        }
        String encryptedpassword=null;
        try {
            encryptedpassword=encryptedpassword(signindto.getUserPassword());
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        boolean isvalid=encryptedpassword.equals(user.getPassword());
        if(!isvalid){
            throw new IllegalStateException("signup instead..!");
        }
        AuthenticationToken auth=as.gettoken(user);
        return "User signed in successfully.."+auth.getToken()+" is generated..!";
    }

    @Transactional
    public String updatedetails(Integer id, String email) {
        ur.updateuser(id,email);
        return "User details updated successfully...";
    }
}
//    public String updateEmployee(Long employeeId, Employee employee) {
//        Optional<Employee> list = employeeRepo.findById(employeeId);
//        employee.setEmployeeId(employeeId);
//
//        if(list.isEmpty()){
//            return "Employee with employeeId "+ employeeId + " not found";
//        }else{
//            employeeRepo.save(employee);
//            return "Employee with employeeId "+ employeeId + " updated successfully";
//        }
//}
