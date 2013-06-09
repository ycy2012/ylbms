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

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-6
 */
@Service
@Transactional
public class LocationService {

	private static final Log log = LogFactory.getLog(LocationService.class);

	@Autowired
	LocationDao locationDao;

	public List<Location> getAllLocation() {
		return locationDao.getAll();
	}

	/**
	 * save location
	 * 
	 * @param location
	 */
	@Transactional(readOnly = false)
	public void saveLocation(Location location) {
		locationDao.save(location);
	}

	public Location getLocationById(Long id) {
		return locationDao.get(id);
	}

	@Transactional(readOnly=false,rollbackFor=RuntimeException.class)
	public void deleteLocation(Long id){
		locationDao.delete(id);
	}
	/**
	 * 
	 * @return
	 */
	public List<Location> lookUpData() {
		return locationDao.getAll();
	}
}
