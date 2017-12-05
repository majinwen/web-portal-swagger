package com.toutiao.web.common.util;

import com.github.pagehelper.Page;
import com.toutiao.web.common.exceptions.NashRequestException;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


public class ResponseUtil {
	public static <T> List copyPropertiesToList(List<T> resource,Class target){
		List l2 ;
		if (resource instanceof Page) {
			Page origin = (Page) resource;

			Page<Object> p = new Page();

			p.setPageNum(origin.getPageNum());
			p.setPageSize(origin.getPageSize());
			p.setTotal(origin.getTotal());

			l2=p;

		}
		else {
			l2= new ArrayList();
		}
		if(!CollectionUtils.isEmpty(resource)) {
			try {
				for (Object n : resource) {
					Object o = target.newInstance();
					NashBeanUtils.copyProperties(o, n);
					l2.add(o);
//				l2.add(ConvertUtils.convert(n, target));
				}
			} catch (Exception e) {
				throw new NashRequestException("0000", e.getMessage());
			}
		}
		return l2;
	}

}
