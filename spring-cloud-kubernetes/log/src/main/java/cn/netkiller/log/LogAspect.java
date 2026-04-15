//package cn.netkiller.log;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.aspectj.apache.bcel.classfile.Method;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import lombok.extern.java.Log;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Aspect
//@Component
//public class LogAspect {
//	private static final Logger log = LoggerFactory.getLogger(LogTest.class);
//
//	public LogAspect() {
//		// TODO Auto-generated constructor stub
//	}
//
//	@Pointcut("@annotation(cn.netkiller.log.MarketLog)") 
//    public void logPointCut(){}
//
//    //环绕通知, 围绕着方法执行
//    @Around("logPointCut()")
//    public Object around(ProceedingJoinPoint pjp) throws Throwable {
//        //在pjp.proceed()之前会运行的逻辑任务，相当于@Before
//        System.out.println("==========@Around1==========");
//
//        //如果写了@Around标注的方法，只有返回了obj，才会开始往下运行，依次调用@Before，@After等方法，@AfterReturning的response才获取返回的结果，否则为null
//        //pjp.proceed()括号中为调用对应接口时填入的参数，如果不填写，则默认原来的参数，（可以在此修改参数）
//
//        //获取参数
//        Object[] objects = pjp.getArgs();
//        log.info("原先参数==》" + objects[0].toString());
//        objects[0] = "lalalala";
//        Object obj=pjp.proceed(objects);
//
//        //在pjp.proceed()之后会运行的逻辑任务，类似于@After
//        System.out.println("==========@Around2==========");
//        return obj;
//    }
//
//    //前置通知, 在方法执行之前执行
//    @Before("logPointCut()")
//    public void before(JoinPoint joinPoint) {
//        System.out.println("==========@Before==========");
//        String methodName = joinPoint.getSignature().getName();
//        Method method = currentMethod(joinPoint,methodName);
//        Log logInfo = method.getAnnotation(Log.class);
//        //@Log中的module内容
//        String module = logInfo.module();
//        //@Log中的description内容
//        String description = logInfo.description();
//        //@Log中的module字段
//        log.info("模块==》" + module + "       " + "描述==》" + description);
//
//        // =====接收到请求，记录请求内容=====
//        //获取请求的request信息
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        // 记录下请求内容
//        //路径
//        log.info("路径==》" + request.getRequestURL().toString());
//        //请求方法
//        log.info("请求方法==》" + request.getMethod());
//        //ip和端口
//        log.info("ip:端口==》" + request.getRemoteAddr() + ":" + request.getServerPort());
//        //类方法
//        log.info("类方法==》" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        //请求参数
//        StringBuilder requestLog = new StringBuilder();
//        Signature signature = joinPoint.getSignature();
//        String[] paramNames = ((MethodSignature) signature).getParameterNames();
//        Object[] paramValues = joinPoint.getArgs();
//        int paramLength = null == paramNames ? 0 : paramNames.length;
//        if (paramLength == 0) {
//            requestLog.append("{}");
//        } else {
//            requestLog.append("[");
//            for (int i = 0; i < paramLength - 1; i++) {
//                requestLog.append(paramNames[i]).append("=").append(JSON.toJSONString(paramValues[i])).append(",");
//            }
//            requestLog.append(paramNames[paramLength - 1]).append("=").append(JSON.toJSONString(paramValues[paramLength - 1])).append("]");
//        }
//        log.info("请求参数==》" + requestLog);
//    }
//
//    //后置通知, 在连接点正常执行完成后执行
//    @After("logPointCut()")
//    public void after() {
//        System.out.println("==========@After==========");
//    }
//
//    //异常通知, 在方法抛出异常之后执行
//    @AfterThrowing("logPointCut()")
//    public void beferException() {
//        System.out.println("==========@AfterThrowing==========");
//    }
//
//    //返回通知, 在方法返回结果之后执行
//    //returning = "response"，response为接口返回的结果
//    @AfterReturning(returning = "response", pointcut = "logPointCut()")
//    public void doAfter(JoinPoint joinPoint, Object response) throws Exception {
//        System.out.println("==========@AfterReturning==========");
//        log.info("返回结果==》" + response.toString());
//    }
//
//    /**
//     * 获取当前执行的方法
//     *
//     * @param joinPoint  连接点
//     * @param methodName 方法名称
//     * @return 方法
//     */
//    private Method currentMethod(JoinPoint joinPoint, String methodName) {
//        /**
//         * 获取目标类的所有方法，找到当前要执行的方法
//         */
//        Method[] methods = joinPoint.getTarget().getClass().getMethods();
//        Method resultMethod = null;
//        for (Method method : methods) {
//            if (method.getName().equals(methodName)) {
//                resultMethod = method;
//                break;
//            }
//        }
//        return resultMethod;
//    }
//
//
////	@Around("@annotation(MarketLog) && args(myLog)")
////	public Object around(ProceedingJoinPoint point, MarketLog myLog) throws Throwable {
////		String className = point.getTarget().getClass().getName();
////		String methodName = point.getSignature().getName();
////		String value = myLog.value();
////		System.out.println("Begin");
////		logger.info("类名：{}，方法名：{}，注解值：{}", className, methodName, value);
////		logger.info("方法之前执行");
////		long startTime = System.currentTimeMillis();
////		Object proceed = point.proceed();
////		long endTime = System.currentTimeMillis();
////		long time = endTime - startTime;
////		logger.info("方法之后执行");
////		logger.info("方法耗时：{}", time);
////		System.out.println("End");
////		return proceed;
////	}
//}
