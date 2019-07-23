package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author yeming
 * @date 2019/7/23 22:41
 */
public interface SpitDao extends MongoRepository<Spit,String> {
}
