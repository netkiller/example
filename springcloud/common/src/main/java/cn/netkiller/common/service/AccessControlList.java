package cn.netkiller.common.service;

public interface AccessControlList {

	boolean isIpaddrBlocked(long interval, int attempts);
	boolean isMobileBlocked(String mobile, long interval, int attempts);

}
