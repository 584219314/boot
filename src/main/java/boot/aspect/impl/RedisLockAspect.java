package boot.aspect.impl;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import boot.aspect.RedisLock;
import boot.util.RedisUtil;

@Aspect
@Component
public class RedisLockAspect {
	final static Logger log = LoggerFactory.getLogger(RedisLockAspect.class);

	@Autowired
	RedisUtil redisUtil;

	@Pointcut("@annotation(boot.aspect.RedisLock)")
	public void redisLock() {

	}

	@Before("redisLock()")
	public void before(JoinPoint joinPoint) {
		MethodSignature sign = (MethodSignature) joinPoint.getSignature();
		Method method = sign.getMethod();
		RedisLock redisLock = method.getAnnotation(RedisLock.class);
		boolean result = false;
		while(!result){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = redisUtil.setnx(redisLock.keyName(), "lock", 10L);
		}
	}

	@Around("redisLock()")
	public Object AroundCall(ProceedingJoinPoint joinPoint) {
		System.out.println("环绕通知之开始");
		Object result = null;
		try {
			result = joinPoint.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("环绕通知之结束");
		return result;
	}

	@After("redisLock()")
	public void after(JoinPoint joinPoint) {
		MethodSignature sign = (MethodSignature) joinPoint.getSignature();
		Method method = sign.getMethod();
		RedisLock redisLock = method.getAnnotation(RedisLock.class);
		redisUtil.del(redisLock.keyName());
	}
}
