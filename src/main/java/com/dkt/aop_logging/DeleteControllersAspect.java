package com.dkt.aop_logging;

import com.dkt.models.Log;
import com.dkt.passingObjects.resp;
import com.dkt.repositories.LogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.logging.Logger;

@Aspect
@Component
public class DeleteControllersAspect {

    @Autowired
    private LogRepository logRepository;

    @AfterReturning(pointcut = "execution(* com.dkt.controllers.*.delete*(..))", returning = "response")
    public void executeDelete(JoinPoint joinPoint, resp response){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().toString();
        JSONObject params = new JSONObject();
        params.put("id", joinPoint.getArgs()[0]);

        Log log = new Log(new Date(),username, role, joinPoint.getSignature().toShortString(), params.toString(), response.status);
        System.out.println(log);
        logRepository.save(log);
    }


//    @Before("execution(* com.dkt.controllers.*.*(*))")
//    public void callGET(JoinPoint joinPoint){

//        Date date = new Date();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        String role = authentication.getAuthorities().iterator().next().toString();

//        Log log = new Log(date, username, role, joinPoint.getSignature().getName());
//        System.out.println(log);
//        logRepository.save(log);
//        System.out.println(joinPoint.getThis() + "; " + joinPoint.getKind() + "; " + joinPoint.getSourceLocation() + "; " + joinPoint.getTarget() + "; " + joinPoint.getStaticPart());
//    }

}
