package com.eg.yafi.util;

import com.eg.yafi.config.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ActiveUserResolver {

    public CustomUserDetails getActiveUser() {
        UserDetails userDetails =  (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //if (userDetails instanceof C)
        //CustomPrincipal customPrincipal =  (CustomPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

       // return customPrincipal;

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof CustomUserDetails)
            System.out.println("*****CUSTOMPRINCIPAL*****");
        else
            System.out.println("*****NO_CUSTOM*****");

        return (CustomUserDetails)userDetails;
    }

    public Long getUserId() {

        Long userId = getActiveUser().getUserId();

        return userId;
    }
}
