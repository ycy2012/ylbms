package com.ylbms.base.location.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.base.location.dao.LocationDao;
import com.ylbms.base.location.model.Location;
import com.ylbms.system.utils.UserUtils;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-6
 */
@Service
@Transactional
public class LocationService {

	@Autowired
	LocationDao locationDao;

	/**
	 * get all location Infos
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Location> getAllLocation() {
		Object locations = UserUtils.getCache("location");
		if (null == locations) {
			locations = locationDao.getAll();
			UserUtils.putCache("location", locations);
		}
		return (List<Location>) locations;
	}

	/**
	 * save location
	 * 
	 * @param location
	 */
	@Transactional(readOnly = false)
	public void saveLocation(Location location) {
		locationDao.save(location);
		UserUtils.removeCache("location");
	}

	/**
	 * getLoactionById
	 * 
	 * @param id
	 * @return
	 */
	public Location getLocationById(Long id) {
		return locationDao.get(id);
	}

	/**
	 * delete method
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public void deleteLocation(Long id) {
		locationDao.delete(id);
		UserUtils.removeCache("location");
	}
}
