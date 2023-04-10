package ru.kata.spring.boot_security.demo.Exception;


public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String msg) {
        super(msg);
    }

}
