package ir.telefa.membership.cdi;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Auditable
@Interceptor
public class AuditableInterceptor {
    @AroundInvoke
    public Object auditMethod(InvocationContext invocationContext) throws Exception {
        return invocationContext.proceed();
    }
}
