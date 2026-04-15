package cn.netkiller.core;

import lombok.Data;

import java.util.List;

/**
 * 方法信息
 * @author xsx
 * @date 2019/6/19
 * @since 1.8
 */
@Data
public class MethodInfo {
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 参数列表
     */
    private List<String> paramNames;
    /**
     * 方法行号
     */
    private Integer lineNumber;

    /**
     * 构造
     * @param methodName 方法名称
     * @param paramNames 参数列表
     * @param lineNumber 方法行号
     */
    MethodInfo(String methodName, List<String> paramNames, Integer lineNumber) {
        this.methodName = methodName;
        this.paramNames = paramNames;
        this.lineNumber = lineNumber;
    }

	public List<String> getParamNames() {
		// TODO Auto-generated method stub
		return this.paramNames;
	}
}
