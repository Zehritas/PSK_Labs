package lt.biblioteka.biblioteka.interceptors;

import javax.interceptor.*;
import java.io.Serializable;

@Interceptor
@LogExecutionTime
public class ExecutionTimeInterceptor implements Serializable {

    @AroundInvoke
    public Object logExecutionTime(InvocationContext context) throws Exception {
        long start = System.currentTimeMillis();
        try {
            return context.proceed();
        } finally {
            long duration = System.currentTimeMillis() - start;
            System.out.println("[ExecutionTimeInterceptor] Method " +
                    context.getMethod().getName() + " took " + duration + "ms");
        }
    }
}
