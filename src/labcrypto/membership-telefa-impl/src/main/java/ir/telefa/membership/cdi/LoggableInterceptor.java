package ir.telefa.membership.cdi;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Loggable
@Interceptor
public class LoggableInterceptor {
    @AroundInvoke
    public Object logMethod(InvocationContext invocationContext) throws Exception {
        return invocationContext.proceed();
    }
}
