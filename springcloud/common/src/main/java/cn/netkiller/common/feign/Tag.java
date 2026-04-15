package cn.netkiller.common.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("restful-api-service")
public interface Tag {

}
