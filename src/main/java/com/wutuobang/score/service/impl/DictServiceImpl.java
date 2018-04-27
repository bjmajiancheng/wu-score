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

import com.wutuobang.score.constant.Constant;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wutuobang.score.model.DictModel;

import java.util.*;

import com.wutuobang.score.dao.*;
import com.wutuobang.score.service.*;

/**
 * @author davdian
 * @version 1.0
 * @since 1.0
 */

@Service("dictService")
public class DictServiceImpl implements IDictService {

    @Autowired
    private IDictDao dictDao;

    public int insert(DictModel dict) {
        if (dict == null) {
            return 0;
        }
        return dictDao.insert(dict);
    }

    public int update(DictModel dict) {
        if (dict == null) {
            return 0;
        }
        return dictDao.update(dict);
    }

    public DictModel getById(Integer id) {
        if (id == null) {
            return null;
        }
        return dictDao.getById(id);
    }

    public int removeById(Integer id) {
        if (id == null) {
            return 0;
        }
        return dictDao.delete(id);
    }

    public List<DictModel> find(Map<String, Object> param) {
        return dictDao.find(param);
    }

    /**
     * 根据字典名称集合获取所有字典集合信息
     *
     * @param aliasKeys
     * @return
     */
    public List<DictModel> findByAliasKeys(List<String> aliasKeys) {
        if (CollectionUtils.isEmpty(aliasKeys)) {
            return Collections.emptyList();
        }

        return this.find(Collections.singletonMap("aliasKeys", (Object) aliasKeys));
    }

    /**
     * 根据字典名称集合获取所有字典Map信息
     *
     * @param aliasKeys
     * @return
     */
    public Map<String, List<DictModel>> findMapByAliasKeys(List<String> aliasKeys) {
        if (CollectionUtils.isEmpty(aliasKeys)) {
            return Collections.emptyMap();
        }

        List<DictModel> dictModels = this.findByAliasKeys(aliasKeys);
        Map<String, List<DictModel>> dictModelMap = new HashMap<String, List<DictModel>>();
        for (DictModel dictModel : dictModels) {
            List<DictModel> tempDictModels = dictModelMap.get(dictModel.getAlias());
            if (CollectionUtils.isEmpty(tempDictModels)) {
                tempDictModels = new ArrayList<DictModel>();
            }

            tempDictModels.add(dictModel);
            dictModelMap.put(dictModel.getAlias(), tempDictModels);
        }

        return dictModelMap;
    }

    /**
     * 根据alias和value获取字典信息
     *
     * @param alias
     * @param value
     * @return
     */
    public DictModel findByAliasAndValue(String alias, Integer value) {
        Map<String, Object> param = new HashMap<String, Object>(2);
        param.put("alias", alias);
        param.put("value", value);
        List<DictModel> dictModels = this.find(param);
        if (CollectionUtils.isEmpty(dictModels)) {
            return null;
        }

        return dictModels.get(0);
    }

    /**
     * 根据别名和值获取字典信息
     *
     * @param alias
     * @param values
     * @return
     */
    public Map<Integer, DictModel> findByAliasAndValues(String alias, List<Integer> values) {
        Map<String, Object> param = new HashMap<String, Object>(2);
        param.put("alias", alias);
        param.put("values", values);
        List<DictModel> dictModels = this.find(param);
        if (CollectionUtils.isEmpty(dictModels)) {
            return Collections.emptyMap();
        }

        Map<Integer, DictModel> dictMap = new HashMap<Integer, DictModel>(2);
        for (DictModel dictModel : dictModels) {
            dictMap.put(dictModel.getValue(), dictModel);
        }

        return dictMap;
    }

}
