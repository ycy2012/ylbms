package com.ylbms.base.bill.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.base.bill.dao.BillHeadDao;
import com.ylbms.base.bill.dao.BillTbodyDao;
import com.ylbms.base.bill.model.BillHeadModel;
import com.ylbms.base.bill.model.BillTbodyModel;
import com.ylbms.base.single.model.SingleInfo;
import com.ylbms.base.single.model.StateInfo;
import com.ylbms.base.single.service.SingleInfoService;
import com.ylbms.system.utils.UserUtils;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-19
 */
@Service
@Transactional
public class BillService {

	@Autowired
	BillHeadDao billHDao;

	@Autowired
	BillTbodyDao billTbodyDao;

	@Autowired
	SingleInfoService singleService;

	/**
	 * 保存单据
	 * 
	 * @param singles
	 * @param bhm
	 * @param newState
	 */
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public void saveBillHeadAndBody(List<SingleInfo> singles,
			BillHeadModel bhm, String newState,String wzInfo) {
		bhm.setSxDate(new Date());
		bhm.setCreateUser(UserUtils.getUser().getFullname());
		List<BillTbodyModel> list = new ArrayList<BillTbodyModel>(); // 保存对象用的
		for (int i = 0, len = singles.size(); i < len; i++) {
			BillTbodyModel btm = new BillTbodyModel();
			btm.setMid(singles.get(i).getMid() == null ? "" : singles.get(i).getMid());
			btm.setOldState(singles.get(i).getState() == null ? "" : singles.get(i).getState().getId());
			btm.setNewState(newState == null ? "" : newState);
			btm.setOldWz(singles.get(i).getLocation() == null ? "" : singles.get(i).getLocation());
			btm.setNewWz(bhm.getAcceptLocation() == null ? "" : bhm.getAcceptLocation());
			btm.setRemark(singles.get(i).getRemark() == null ? "" : singles.get(i).getRemark());
			btm.setBillId(bhm);
			list.add(btm);
		}
		bhm.setBillTbody(list);
		billHDao.save(bhm);
		// 更新单件信息
		updateSingle(singles, newState, wzInfo);
	}

	/**
	 * update singleInfo
	 * 
	 * @param single
	 */
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public void updateSingle(List<SingleInfo> singles, String newState,String wzInfo) {
		for (SingleInfo s : singles) {
			SingleInfo single=singleService.getSingleById(s.getMid());
			single.setState(new StateInfo("010"));
			single.setQy_Time(new Date());
			single.setLocation(wzInfo);
			singleService.updateSingleInfo(single);
		}
	}

}
