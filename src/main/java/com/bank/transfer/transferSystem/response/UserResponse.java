package com.bank.transfer.transferSystem.response;

import com.bank.transfer.transferSystem.entity.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponse {
    private String message;
    private User data;

    public UserResponse(String message, User data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public User getData() {
        return data;
    }
}
