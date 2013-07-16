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
import com.ylbms.base.location.model.Location;
import com.ylbms.base.single.dao.SingleInfoDao;
import com.ylbms.base.single.model.SingleInfo;
import com.ylbms.base.single.model.StateInfo;
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
	SingleInfoDao singleDao;

	/**
	 * 保存单据
	 * @param singles 明细
	 * @param bhm 表头信息
	 * @param newState 新状态
	 * @param wzInfo 位置信息
	 */
	@Transactional(readOnly = false, rollbackFor =Exception.class)
	public void saveBillHeadAndBody(List<SingleInfo> singles,
			BillHeadModel bhm, String newState,Location wzInfo) {
		bhm.setSxDate(new Date());
		bhm.setCreateUser(UserUtils.getUser().getFullname());
		List<BillTbodyModel> list = new ArrayList<BillTbodyModel>(); // 保存对象用的
		for (int i = 0, len = singles.size(); i < len; i++) {
			BillTbodyModel btm = new BillTbodyModel();
			btm.setMid(singles.get(i).getMid() == null ? "" : singles.get(i).getMid());
			btm.setOldState(singles.get(i).getState() == null ? "" : singles.get(i).getState().getId());
			btm.setNewState(newState == null ? "" : newState);
			btm.setOldWz(singles.get(i).getLocation() == null ? "" : singles.get(i).getLocation().getId().toString());
			btm.setNewWz(bhm.getAcceptLocation() == null ? "" : bhm.getAcceptLocation().getId().toString());
			btm.setRemark(singles.get(i).getRemark() == null ? "" : singles.get(i).getRemark());
			btm.setBillId(bhm);
			list.add(btm);
		}
		bhm.setBillTbody(list);
		billHDao.save(bhm);
		// 更新单件信息
		/**
		 * 判断是不是安装记录
		 */
		if(newState!=null&&newState.equals(singles.get(0).getState().getId())){
			updateSingleByInstallNotes(singles);
		}else{
			updateSingle(singles, newState, wzInfo);
		}
	}

	/**
	 * update singleInfo otherInfos
	 * 
	 * @param single
	 */
	public void updateSingle(List<SingleInfo> singles, String newState,Location wzInfo) {
		for (SingleInfo s : singles) {
			SingleInfo single=singleDao.get(s.getMid());
			single.setState(new StateInfo(newState));
			single.setQyTime(new Date());
			single.setLocation(wzInfo);
			singleDao.save(single);
		}
	}
	/**
	 * 更新安装位置  ----根据安装记录填写信息更新
	 * @param singles
	 */
	public void updateSingleByInstallNotes(List<SingleInfo> singles){
		for (SingleInfo s : singles) {
			SingleInfo single=singleDao.get(s.getMid());
			single.setAzLocation(s.getAzLocation()==null?"":s.getAzLocation());
			single.setIsAnz("1");
			singleDao.save(single);
		}
	}

}
