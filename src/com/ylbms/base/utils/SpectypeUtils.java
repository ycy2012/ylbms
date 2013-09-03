package com.ylbms.base.utils;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ylbms.base.single.model.SpectypeInfo;
import com.ylbms.base.single.service.SpectypeService;
import com.ylbms.common.utils.CacheUtils;
import com.ylbms.common.utils.spring.SpringContextHolder;

/**
 * 规格型号工具
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-9-3
 */
public class SpectypeUtils {
	private static SpectypeService spectypeService = SpringContextHolder
			.getBean(SpectypeService.class);
	private static final String CACHE_SPECTYPE_MAP = "spectypeMap";

	/**
	 * 取得规格信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getSpectypeList() {
		List<Map<String, Object>> spectypeList = (List<Map<String, Object>>) CacheUtils
				.get(CACHE_SPECTYPE_MAP);
		if (null == spectypeList || spectypeList.isEmpty()) {
			spectypeList = Lists.newArrayList();
			List<SpectypeInfo> list = spectypeService.getAllSpectype();
			for (SpectypeInfo s : list) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("value", s.getSpeId());
				map.put("text", s.getSpeName());
				spectypeList.add(map);
			}
			CacheUtils.put(CACHE_SPECTYPE_MAP, spectypeList);
		}
		return spectypeList;
	}
}
