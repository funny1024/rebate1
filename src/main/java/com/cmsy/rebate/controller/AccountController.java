package com.cmsy.rebate.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cmsy.rebate.config.Constant;
import com.cmsy.rebate.entity.*;
import com.cmsy.rebate.service.IAccountService;
import com.cmsy.rebate.service.ICommissionDetailedService;
import com.cmsy.rebate.service.ITUserRebateService;
import com.cmsy.rebate.service.IUserConsumptionService;
import com.cmsy.rebate.utils.HttpUtil;
import com.cmsy.rebate.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kaka_fun
 * @since 2018-11-13
 */
@Controller
public class AccountController {

    @Autowired
    IAccountService accountService;

    @Autowired
    ITUserRebateService userRebateService;

    @Autowired
    IUserConsumptionService userConsumptionService;

    @Autowired
    ICommissionDetailedService commissionDetailedService;

    /**
     * 微信登陆    绑定上下级关系
     * @param request Request
     */
    @RequestMapping(value = "/login_wechat")
    public ModelAndView loginWeChat(HttpServletRequest request, HttpServletResponse response) {
        // 得到传递参数
        LoginParam loginParam = null;
        try {
            String code = request.getParameter("code");
            String state = request.getParameter("state");
            // 玩家信息
            JSONObject userinfoJson = new JSONObject();
            if (!StringUtil.isEmpty(code)) {
                // 微信授权  得到返回结果
                String result = HttpUtil.get(
                        "https://api.weixin.qq.com/sns/oauth2/access_token?"
                                + "appid=" + Constant.WECHAT_OFFICE_APPID
                                + "&secret=" + Constant.WECHAT_OFFICE_APPSECRET
                                + "&code=" + code
                                + "&grant_type=authorization_code", "utf-8"
                );
                JSONObject jsonResult = JSON.parseObject(result);
                // 授权完成 正确返回
                if (jsonResult.containsKey("access_token")) {
                    // 检测access_token有效性   由于access_token有效期（目前为2个小时）较短，当access_token超时后，可以使用refresh_token进行刷新
                    String checkStatus = HttpUtil.get("https://api.weixin.qq.com/sns/auth?access_token=" + jsonResult.getString("access_token") + "&openid=" + jsonResult.getString("openid"), "utf-8");
                    System.out.println("check--------" + checkStatus); // {"errcode":0,"errmsg":"ok"}
                    JSONObject checkJson = JSON.parseObject(checkStatus);
                    // errcode为0 access_token 有效
                    if (checkJson.containsKey("errcode") && 0 == checkJson.getIntValue("errcode")) {
                        // 获取个人信息接口 返回个人信息
                        String userinfo = HttpUtil.get("https://api.weixin.qq.com/sns/userinfo?access_token=" + jsonResult.getString("access_token") + "&openid=" + jsonResult.getString("openid") + "&lang=zh_CN", "utf-8");
                        userinfoJson = JSON.parseObject(userinfo);
                        /**
                         * {
                         * "openid":"OPENID",
                         * "nickname":"NICKNAME",
                         * "sex":1,
                         * "province":"PROVINCE",
                         * "city":"CITY",
                         * "country":"COUNTRY",
                         * "headimgurl": "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
                         * "privilege":[
                         * "PRIVILEGE1",
                         * "PRIVILEGE2"
                         * ],
                         * "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
                         * }
                         */
                        System.out.println("userinfoJson--------" + userinfo);
                    } else { // access_token 无效
                        // 刷新或续期access_token使用  接口
                        String refresh = HttpUtil.get("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + Constant.WECHAT_OFFICE_APPID + "&grant_type=refresh_token&refresh_token=" + jsonResult.getString("refresh_token"), "utf-8");
                        jsonResult = JSON.parseObject(refresh);
                        // 重新获取个人信息
                        String userinfo = HttpUtil.get("https://api.weixin.qq.com/sns/userinfo?access_token=" + jsonResult.getString("access_token") + "&openid=" + jsonResult.getString("openid") + "&lang=zh_CN", "utf-8");
                        userinfoJson = JSON.parseObject(userinfo);
                        System.out.println("userinfoJson--------" + userinfo);
                    }
                }
            }
            // 用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。
            if (userinfoJson.containsKey("unionid")) {
                userinfoJson.put("parent", Integer.parseInt(state));
                loginAndBindParent(userinfoJson);
            }
//            return new ModelAndView("redirect:/user/person");

            //TODO
//            response.sendRedirect("http://xyxz.wi111.cn");
//            response.sendRedirect("http://jhxz.wi111.cn");
//            response.sendRedirect("https://fir.im/lansheng");
//            response.sendRedirect("https://fir.im/yile");
//            response.sendRedirect("https://fir.im/xingkongyuletest");
            response.sendRedirect("https://fir.im/huojianqipai");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * 登录并绑定
     * @param userinfoJson
     */
    public void loginAndBindParent(JSONObject userinfoJson) {

        Account user;
        if (userinfoJson.containsKey(userinfoJson.getString("unionid"))) {
            user = accountService.selectOne(new EntityWrapper<Account>().eq("account_name",userinfoJson.getString("unionid")));
        } else {
            user = accountService.selectOne(new EntityWrapper<Account>().eq("id",userinfoJson.getIntValue("userId")));
        }
        int playerId = 0;
        if (null == user && userinfoJson.containsKey("unionid")) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("account", userinfoJson.getString("unionid"));
            jsonObject.put("password", StringUtil.md5(userinfoJson.getString("unionid"), 32, false, "utf-8"));
            jsonObject.put("headUrl", userinfoJson.getString("headimgurl"));
            jsonObject.put("nick", userinfoJson.getString("nickname"));
            jsonObject.put("sex", userinfoJson.getIntValue("sex"));
            jsonObject.put("enc", StringUtil.md5(userinfoJson.getString("unionid") + "&_&" + StringUtil.md5(userinfoJson.getString("unionid"), 32, false, "utf-8") + "&_&" + userinfoJson.getString("headimgurl") + "&_&" + userinfoJson.getString("nickname") + "&_&" + userinfoJson.getIntValue("sex") + "&_&" + Constant.gameServerKey, 32, false, "utf-8"));
            // 向游戏服发送消息   创建账号
            String createAccResult = HttpUtil.urlConnection(Constant.gameServerUrl, "create_account=" + jsonObject.toJSONString());
            System.out.println("创建帐号返回：" + createAccResult);
            /**
             * {
             *     "error_code": 0,
             *     "data":{
             *         "playerId":100
             *     }
             *   }
             */
            if (!StringUtil.isEmpty(createAccResult)) {
                JSONObject result = JSONObject.parseObject(createAccResult);
                if (0 == result.getIntValue("error_code")) {
                    JSONObject data = result.getJSONObject("data");
                    playerId = data.getIntValue("playerId");
                    user = accountService.selectOne(new EntityWrapper<Account>().eq("id",userinfoJson.getIntValue("userId")));
                }
            }
        }
        if (null == user && 0 == playerId) {
            return;
        }
        if (null == user) {
            System.out.println("新账号绑定前");
            if (userinfoJson.containsKey("parent") && 0 != userinfoJson.getIntValue("parent") && playerId != userinfoJson.getIntValue("parent")) {
                bindParent(playerId, userinfoJson.getIntValue("parent"));
            }
            user = accountService.selectOne(new EntityWrapper<Account>().eq("id", playerId));
        } else {
            System.out.println("旧账号绑定前");
            if (userinfoJson.containsKey("parent") && 0 != userinfoJson.getIntValue("parent") && user.getId() != userinfoJson.getIntValue("parent")) {
                bindParent(user.getId(), userinfoJson.getIntValue("parent"));
            }
        }
        System.out.println("绑定后");
    }

    /**
     * 绑定上级
     */
    public void bindParent(Integer userId, int parentId) {
        // 在返利表查找当前用户
        TUserRebate currUser = userRebateService.selectOne(new EntityWrapper<TUserRebate>().eq("id", userId));
        if (null == currUser) {
            TUserRebate parentUser = userRebateService.selectOne(new EntityWrapper<TUserRebate>().eq("id", parentId));
            if (null != parentUser && null != parentUser.getLevel() && parentUser.getLevel() == 1) {
                // currUser = new TUserRebate(userId, parentId, parentUser.getB(), parentUser.getA(), 2, parentUser.getGroupName());
                currUser.setUserId(userId);
                currUser.setParentId(parentId);
                currUser.setB(parentUser.getB());
                currUser.setA(parentUser.getA());
                currUser.setLevel(2);
                currUser.setGroupName(parentUser.getGroupName());
                userRebateService.insert(currUser);
            }
        }

    }

    /**
     * 消费  返利
     * @param jsonArray
     * @return
     */
    @RequestMapping(value = "/consumption")
    @ResponseBody
    public JsonMessage consumption(String jsonArray) {
        JsonMessage jsonMessage = new JsonMessage();
        boolean notsave = true;
        int i = 0;
        while (notsave) {
            try {
                jsonMessage.setCode(0);
                consumption(JSON.parseArray(jsonArray));
                notsave = false;
            } catch (Exception e) {
                i++;
                System.out.println("返利错误重新返利" + i);
            }
            if (i >= 100) {
                jsonMessage.setData(1);
                notsave = false;
                System.out.println("返利数据"+  jsonArray);
            }
        }

        return jsonMessage;
    }

    /**
     * 返利方法
     * @param jsonArray
     */
    public void consumption(JSONArray jsonArray) {
        JSONArray notice = new JSONArray();
        //讯米
//        double m1 = 0.4 * 0.9 / 108;
//        double m2 = 0.12 * 0.9 / 108;
//        double m3 = 0.08 * 0.9 / 108;
        //TODO
        //心悦
//        double m1 = 0.4 * 0.83 / 110;
//        double m2 = 0.12 * 0.83 / 110;
//        double m3 = 0.08 * 0.83 / 110;
        //江湖3
//        double m1 = 0.4 * 0.83 * 0.98 / 100;
//        double m2 = 0.12 * 0.83 * 0.98 / 100;
//        double m3 = 0.08 * 0.83 * 0.98 / 100;
        //揽胜
//        double m1 = 0.35 / 100;
//        double m2 = 0.1 / 100;
//        double m3 = 0.05 / 100;
        //亦乐
//        double m1 = 0.42 * 0.83 / 110;
//        double m2 = 0.12 * 0.83 / 110;
//        double m3 = 0.06 * 0.83 / 110;
        //星空
//        double m1 = 0.42 * 0.83 / 104;
//        double m2 = 0.14 * 0.83 / 104;
//        double m3 = 0.09 * 0.83 / 104;
        //火箭
        double m1 = 0.40 * 0.83 /100;
        double m2 = 0.15 * 0.83 /100;
        double m3 = 0.05 * 0.83 /100;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            BigDecimal card = BigDecimal.valueOf(BigDecimal.valueOf(jsonObject.getFloatValue("card")).setScale(2, RoundingMode.HALF_UP).doubleValue());

            // 在返利表中查询得到当前消费的用户
            TUserRebate user = userRebateService.selectOne(new EntityWrapper<TUserRebate>().eq("id", jsonObject.getIntValue("userId")));
            // 创建一个消费信息
            UserConsumption userConsumption = new UserConsumption();
            userConsumption.setConsumption(card);
            userConsumption.setId(StringUtil.uuid());
            userConsumption.setCreateDate(new Date());
            userConsumption.setVersion(0);
            // 将该消费信息入库
            userConsumptionService.insert(userConsumption);
            if (null == user) {
                continue;
            }
            // 更新该用户的佣金信息，并将该数据入库
            user.setTotalConsumption(user.getTotalConsumption().add(card));
            user.setTodayConsumption(user.getTodayConsumption().add(card));
            userRebateService.updateById(user);

            // 声明其上级
            TUserRebate parent_1;
            if (user.getLevel() == 1) {
                // 玩家为一级代理
                parent_1 = user;
            } else if (null == user.getParentId()) {
                // 没有上级
                continue;
            } else {
                // 找到其上级
                parent_1 = userRebateService.selectById(new EntityWrapper<TUserRebate>().eq("id", user.getParentId()));
            }
            if (null == parent_1 || parent_1.getLevel() != 1) {
                continue;
            }
            // 保存玩家佣金详细信息(commission_detail表)，并更新其上级的佣金信息(user_rebate表)
            saveCommAndUpdateUpperRebate(user, parent_1, card, m1);
            Integer parentId = parent_1.getParentId();
            if (null != parentId) {
                TUserRebate parent_2 = userRebateService.selectOne(new EntityWrapper<TUserRebate>().eq("id", parentId));
                // 保存玩家佣金详细信息(commission_detail表)，并更新其上级的佣金信息(user_rebate表)
                saveCommAndUpdateUpperRebate(user, parent_2, card, m2);
                parentId = parent_2.getParentId();
                if (null != parentId) {
                    TUserRebate parent_3 = userRebateService.selectOne(new EntityWrapper<TUserRebate>().eq("id", parentId));
                    // 保存玩家佣金详细信息(commission_detail表)，并更新其上级的佣金信息(user_rebate表)
                    saveCommAndUpdateUpperRebate(user, parent_3, card, m3);
                }
            }
        }
    }

    /**
     * 保存玩家佣金详细信息(commission_detail表)，并更新其上级的佣金信息(user_rebate表)
     */
    public void saveCommAndUpdateUpperRebate(TUserRebate user, TUserRebate parent, BigDecimal card, double rate){

        if (null != user && null != parent){
            // 构建一个佣金详细信息对象
            CommissionDetailed commissionDetailed = new CommissionDetailed();
            commissionDetailed.setFlowType(1);  // in_flow
            commissionDetailed.setUserId(parent.getUserId());  // 哪个用户将得到返利
            commissionDetailed.setCommission(BigDecimal.valueOf(rate).multiply(card).setScale(2, RoundingMode.HALF_UP));
            commissionDetailed.setDescription(user.getUserId() + "消耗" + card.doubleValue()); // 消费信息
            commissionDetailed.setFromUser(user.getUserId()); // 返利来自哪个用户的消费行为
            commissionDetailedService.insert(commissionDetailed); // 保存这个佣金详情信息

            // 然后需要在返利表中更新其上级的佣金信息
            parent.setCommission(parent.getCommission().add(commissionDetailed.getCommission()).setScale(2, RoundingMode.HALF_UP));
            parent.setTodayCommission(parent.getTodayCommission().add(commissionDetailed.getCommission()).setScale(2, RoundingMode.HALF_UP));
            parent.setTotalCommission(parent.getTotalCommission().add(commissionDetailed.getCommission()).setScale(2, RoundingMode.HALF_UP));
            userRebateService.insertOrUpdate(parent);
        }else return;

    }

}

