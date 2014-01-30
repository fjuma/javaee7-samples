package org.javaee7.session.activation.beans;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

//@RequestScoped
@SessionScoped
public class TestBean implements Serializable {

    private String message = "Hello";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
