package com.tensquare.friend.controller;

import com.tensquare.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yeming
 * @date 2019/8/28 22:51
 */
@RestController
@RequestMapping("friend")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("like/{friendid}/{type}")
    public Result addFriend(@PathVariable("friendid")String friendid,@PathVariable("type")String type){
        Claims claims = (Claims) request.getAttribute("claims_user");
        if (claims == null){
            return new Result(false, StatusCode.LOGINERROR,"权限不足");
        }
        if (StringUtils.isEmpty(type)){
            return new Result(false, StatusCode.ERROR,"参数有误");
        }
        String userid = claims.getId();
        if (type.equals("1")){
            //喜欢
           Integer result = friendService.addFriend(userid,friendid);
           if (result == 0){
               return new Result(false, StatusCode.ERROR,"请勿重复添加好友");
           }
           if (result == 1){
               return new Result(true, StatusCode.OK,"添加好友成功");
           }
        }else if (type.equals("2")){
            //不喜欢
            Integer result = friendService.addNoFriend(userid,friendid);
            if (result == 0){
                return new Result(false, StatusCode.ERROR,"请勿重复添加好友");
            }
            if (result == 1){
                return new Result(true, StatusCode.OK,"添加非好友成功");
            }
        }
        return new Result(false, StatusCode.ERROR,"参数有误");
    }
}
