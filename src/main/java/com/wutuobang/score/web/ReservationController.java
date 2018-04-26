package com.wutuobang.score.web;

import com.wutuobang.common.utils.ResultParam;
import com.wutuobang.score.model.AcceptAddressModel;
import com.wutuobang.score.model.AcceptDateConfModel;
import com.wutuobang.score.model.CompanyInfoModel;
import com.wutuobang.score.model.IdentityInfoModel;
import com.wutuobang.score.service.IAcceptAddressService;
import com.wutuobang.score.service.IAcceptDateConfService;
import com.wutuobang.score.service.IIdentityInfoService;
import com.wutuobang.shiro.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by majiancheng on 2018/4/14.
 */
@Controller
@RequestMapping(value = "/reservation")
public class ReservationController {

    @Autowired
    private IIdentityInfoService identityInfoService;

    @Autowired
    private IAcceptAddressService acceptAddressService;

    @Autowired
    private IAcceptDateConfService acceptDateConfService;

    /**
     * 预约地点页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/reserveLocation/{id}.html")
    public ModelAndView reserveLocation(HttpServletRequest request, @PathVariable("id") Integer id) {
        if (id == null) {
            return new ModelAndView("500", "result", ResultParam.PARAM_ERROR_RESULT);
        }
        ModelAndView mv = new ModelAndView("reservation/reserveLocation.html");

        try {
            IdentityInfoModel identityInfo = identityInfoService.getById(id);
            List<AcceptAddressModel> acceptAddressList = acceptAddressService.find(new HashMap<String, Object>());
            CompanyInfoModel companyInfo = ShiroUtils.getUserEntity();

            mv.addObject("identityInfo", identityInfo);
            mv.addObject("acceptAddressList", acceptAddressList);
            mv.addObject("companyInfo", companyInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("500", "result", ResultParam.SYSTEM_ERROR_RESULT);
        }

        return mv;
    }

    /**
     * 预约时间页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/reserveTime/{id}.html")
    public ModelAndView reserveTime(HttpServletRequest request, @PathVariable("id") Integer id) {
        if (id == null) {
            return new ModelAndView("500", "result", ResultParam.PARAM_ERROR_RESULT);
        }
        ModelAndView mv = new ModelAndView("reservation/reserveTime.html");

        try {
            IdentityInfoModel identityInfo = identityInfoService.getById(id);
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("batchId", identityInfo.getBatchId());
            param.put("addressId", identityInfo.getAcceptAddressId());
            param.put("currDate", new Date());

            List<AcceptDateConfModel> acceptDateConfs = acceptDateConfService.find(param);

            mv.addObject("acceptDateConfs", acceptDateConfs);
            mv.addObject("identityInfo", identityInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("500", "result", ResultParam.SYSTEM_ERROR_RESULT);
        }

        return mv;
    }

}
