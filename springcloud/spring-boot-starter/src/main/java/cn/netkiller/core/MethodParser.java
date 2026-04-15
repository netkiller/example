package cn.netkiller.core;

import javassist.ClassPool;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.LocalVariableAttribute;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法解析器
 * @author xsx
 * @date 2019/6/19
 * @since 1.8
 */
public class MethodParser {

    /**
     * 类池
     */
    private final static ClassPool POOL = ClassPool.getDefault();

    /**
     * 获取调用方法
     * @param className 全类名
     * @param methodName 方法名称
     * @return 返回对应方法
     * @throws NotFoundException 未知方法异常
     */
    public static CtMethod getMethod(String className, String methodName) throws NotFoundException {
        return POOL.get(className).getDeclaredMethod(methodName);
    }

    /**
     * 获取方法信息
     * @param className 全类名
     * @param methodName 方法名称
     * @return 返回方法信息
     */
    public static MethodInfo getMethodInfo(String className, String methodName) {
        try {
            return getMethodInfo(getMethod(className, methodName));
        } catch (Exception e) {
            return new MethodInfo(methodName, new ArrayList<>(0), -1);
        }
    }

    /**
     * 获取方法信息
     * @param method 方法对象
     * @return 返回方法信息
     */
    public static MethodInfo getMethodInfo(CtMethod method) {
        try {
            javassist.bytecode.MethodInfo methodInfo = method.getMethodInfo();
            int lineNumber = methodInfo.getLineNumber(0);
            List<String> paramNames;
            LocalVariableAttribute attribute = (LocalVariableAttribute) methodInfo.getCodeAttribute().getAttribute(LocalVariableAttribute.tag);
            if (attribute!=null) {
                int count = method.getParameterTypes().length;
                paramNames = new ArrayList<>(count);
                for (int i = 1; i <= count; i++) {
                    paramNames.add(attribute.variableName(i));
                }
            }else {
                paramNames = new ArrayList<>(0);
            }
            return new MethodInfo(method.getName(), paramNames, lineNumber);
        } catch (Exception e) {
            return new MethodInfo(method.getName(), new ArrayList<>(0), -1);
        }
    }
}
