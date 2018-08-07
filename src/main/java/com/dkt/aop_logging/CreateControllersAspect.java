package com.dkt.aop_logging;

import com.dkt.models.Log;
import com.dkt.passingObjects.resp;
import com.dkt.repositories.LogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class CreateControllersAspect {

    @Autowired
    private LogRepository logRepository;

    @AfterReturning(pointcut = "execution(* com.dkt.controllers.*.create*(..))", returning = "response")
    public void executeCreate(JoinPoint joinPoint, resp response){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().toString();
        String method = joinPoint.getSignature().toShortString();
        String param = joinPoint.getArgs()[0].toString();
        boolean result = response.status;
        Log log = new Log(new Date(), username, role, method, param, result);
        System.out.println(log);
        logRepository.save(log);
    }

}
