package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yeming
 * @date 2019/8/28 22:50
 */
public interface NoFriendDao extends JpaRepository<NoFriend,String> {
    NoFriend getByUseridAndFriendid(String userid,String friendid);
}
