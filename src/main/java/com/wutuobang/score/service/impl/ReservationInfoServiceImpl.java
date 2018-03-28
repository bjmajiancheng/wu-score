/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wutuobang.score.model.ReservationInfoModel;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

@Service("reservationInfoService")
public class ReservationInfoServiceImpl implements IReservationInfoService{
	@Autowired
	private IReservationInfoDao reservationInfoDao;

	public int insert(ReservationInfoModel reservationInfo) {
		if(reservationInfo == null) {
			return 0;
		}
		return reservationInfoDao.insert(reservationInfo);
	}
	
	public int update(ReservationInfoModel reservationInfo) {
		if(reservationInfo == null) {
			return 0;
		}
		return reservationInfoDao.update(reservationInfo);
	}
	
	public ReservationInfoModel getById(Integer id) {
		if(id == null) {
			return null;
		}
		return reservationInfoDao.getById(id);
	}

	public int removeById(Integer id) {
		if(id == null) {
			return 0;
		}
		return reservationInfoDao.delete(id);
	}	

	public List<ReservationInfoModel> find(Map<String, Object> param) {
		return reservationInfoDao.find(param);
	}
	
}
