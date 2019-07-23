package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.List;

/**
 * @author yeming
 * @date 2019/7/23 22:41
 */
@Service
@Transactional
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    public List<Spit> findAll(){
       return spitDao.findAll();
    }

    public Spit findById(String spitId){
        return spitDao.findById(spitId).get();
    }

    public void save(Spit spit){
        spit.set_id(idWorker.nextId()+"");
        spitDao.save(spit);
    }

    public void update(Spit spit){
        spitDao.save(spit);
    }

    public void deleteById(String spitId){
        spitDao.deleteById(spitId);
    }
}
