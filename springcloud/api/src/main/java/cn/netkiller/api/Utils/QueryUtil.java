package cn.netkiller.api.Utils;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public class QueryUtil {
//    public static PageRequest buildPageRequest(int pageNumber, int pageSize, String sortType){
//        Sort sort = null;
//        if("auto".equals(sortType)) {
//            sort = new Sort(Direction.DESC, "ctime");
//        } else {
//            sort = new Sort(Direction.DESC, "ctime");
//        }
//        
//        return new PageRequest(pageNumber, pageSize, sort);
//    }
//    
//    public static <T> Specification<T> buildSpecification(Map<String, Object> searchParams, Class<T> domainClass){
//        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
//        Specification<T> spec = DynamicSpecifications.bySearchFilter(filters.values(), domainClass);
//        return spec;
//    }
}
