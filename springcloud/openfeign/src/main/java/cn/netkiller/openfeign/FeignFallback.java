package cn.netkiller.openfeign;

public class FeignFallback implements TestFeign {
	@Override
	public String hi(String name) {
		return "sorry,熔断介入";
	}

}
