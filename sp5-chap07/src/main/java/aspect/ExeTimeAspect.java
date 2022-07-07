package aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
// @Order(1)
public class ExeTimeAspect {

    // @Pointcut : 공통기능을 적용할 대상
    // "execution(public * chap07..*(..))": chap07 패키지와 하위 패키지에 위치한 타임의 public 메서드를 Pointcut으로 설정
    @Pointcut("execution(public * chap07..*(..))")
    private void publicTarget() {
    }

    // @Around("publicTarget()") : publicTarget() 메서드에 정의한 pointcut에 공통기능을 적용한다는 것을 의미미
   @Around("publicTarget()")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        try {
            //Around Advice에서 사용할 공통 기능 메서드는 대부분 파라미터로 전달받은 ProceedingJoinPoint의 proceed() 메서드만 호출하면 된다.
            Object result = joinPoint.proceed();
            return result;
        } finally {
            long finish = System.nanoTime();
            Signature sig = joinPoint.getSignature();
            System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n",
                    joinPoint.getTarget().getClass().getSimpleName(),
                    sig.getName(), Arrays.toString(joinPoint.getArgs()),
                    (finish - start));
        }
    }

}