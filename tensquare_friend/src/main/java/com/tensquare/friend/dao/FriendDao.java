package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author yeming
 * @date 2019/8/28 22:49
 */
public interface FriendDao extends JpaRepository<Friend,String> {
    Friend getByUseridAndFriendid(String userid,String friendid);

    @Modifying
    @Query(value = "UPDATE tb_friend set islike = ? WHERE userid = ? AND friendid = ?",nativeQuery = true)
    void updateIsLike(String isLike,String userid,String friendid);
}
