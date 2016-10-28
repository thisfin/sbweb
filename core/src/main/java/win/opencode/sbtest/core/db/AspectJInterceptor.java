package win.opencode.sbtest.core.db;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by wenyou on 2016/9/21.
 */
@Aspect
@Component
@Slf4j
public class AspectJInterceptor {
    @Pointcut("execution(public * win.opencode.sbtest.core.db.BaseDAO.*(..)) || execution(public * win.opencode.sbtest.biz.dao.*.*(..))")
    public void dAOPointcut() {
    }

    @Around("dAOPointcut()")
    public Object watchDAOPointcut(ProceedingJoinPoint joinPoint)
            throws DBException {
        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new DBException(e);
        } catch (Throwable throwable) {
            log.error(throwable.getMessage(), throwable);
            throw new DBException(throwable);
        }
    }
}
