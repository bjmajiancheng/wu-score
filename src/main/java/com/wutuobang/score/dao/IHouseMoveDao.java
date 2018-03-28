/*
 * 北京果敢时代科技有限公司
 * 北京市朝阳区望京SOHO T3 B座1607
 * 邮编：100022
 * 网址：www.davdian.com
 */

package com.wutuobang.score.dao;
import com.wutuobang.score.model.HouseMoveModel;
import org.apache.ibatis.annotations.Param;
import java.util.*;
import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author  davdian
 * @version 1.0
 * @since 1.0
 */

public interface IHouseMoveDao {

	public int insert(HouseMoveModel houseMove);

	public int update(HouseMoveModel houseMove);

	public HouseMoveModel getById(@Param("id") Integer id);

	public int delete(@Param("id") Integer id);
	
	public List<HouseMoveModel> find(Map<String, Object> param);
	
}
