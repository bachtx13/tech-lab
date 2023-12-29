package com.sem4project.sem4.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

public class PageableUtil {
    public static Pageable calculatePageable(long count, int pageNumber, int pageSize, String sortBy, String sortType) {
        int maxPage = (int) Math.ceil((double) count / pageSize);
        pageNumber--;
        pageNumber = Math.max(0, pageNumber);
        pageNumber = Math.min(pageNumber, maxPage);
        Sort sort = createSortFromString(sortBy, sortType);
        return PageRequest.of(pageNumber, pageSize, sort);
    }
    public static Sort createSortFromString(String sortBy, String sortType){
        Sort sort = Sort.by(Objects.requireNonNullElse(sortBy, "updatedAt"));
        if(sortType == null || sortType.equalsIgnoreCase("desc")){
            sort = sort.descending();
        } else{
            sort = sort.ascending();
        }
        return sort;
    }
}
