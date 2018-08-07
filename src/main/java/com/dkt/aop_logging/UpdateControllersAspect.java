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
import java.util.Map;

@Aspect
@Component
public class UpdateControllersAspect {

    @Autowired
    private LogRepository logRepository;

    @AfterReturning(pointcut = "execution(* com.dkt.controllers.*.update*(..))", returning = "response")
    public void executeUpdate(JoinPoint joinPoint, resp response){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().toString();
        String method = joinPoint.getSignature().toShortString();

        String id = joinPoint.getArgs()[0].toString();
        Map<String, String> param = (Map<String, String>) joinPoint.getArgs()[1];
        param.put("id", id);

        boolean result = response.status;
        Log log = new Log(new Date(), username, role, method, param.toString(), result);
        System.out.println(log);
        logRepository.save(log);

    }

}
