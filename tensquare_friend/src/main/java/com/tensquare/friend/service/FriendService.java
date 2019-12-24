package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yeming
 * @date 2019/8/28 22:53
 */
@Service
@Transactional
public class FriendService {
    @Autowired
    private FriendDao friendDao;
    @Autowired
    private NoFriendDao noFriendDao;

    //添加喜欢好友
    public Integer addFriend(String userid, String friendid) {
        Friend friend = friendDao.getByUseridAndFriendid(userid, friendid);
        if (friend != null){
            return 0;
        }
        friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);

        if (friendDao.getByUseridAndFriendid(friendid,userid) != null){
            //互相喜欢
            friendDao.updateIsLike("1",userid,friendid);
            friendDao.updateIsLike("1",friendid,userid);
        }
        return 1;
    }

    public Integer addNoFriend(String userid, String friendid) {
        NoFriend noFriend = noFriendDao.getByUseridAndFriendid(userid, friendid);
        if (noFriend != null){
            return 0;
        }
        noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
        return 1;
    }
}
