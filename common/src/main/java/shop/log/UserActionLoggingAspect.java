package shop.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import shop.dto.UserDto;

@Aspect
@Component
public class UserActionLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(UserActionLoggingAspect.class);

    @Pointcut("execution(* shop.service.UserService.*(..))")
    public void userServiceMethods() {}

    @AfterReturning(pointcut = "userServiceMethods()", returning = "result")
    public void registerCreateUser(JoinPoint joinPoint, Object result) {
        if (result instanceof UserDto){
            logger.info("Пользователь зарегистрирался: {}", ((UserDto) result).getLogin());
        }

    }
}
