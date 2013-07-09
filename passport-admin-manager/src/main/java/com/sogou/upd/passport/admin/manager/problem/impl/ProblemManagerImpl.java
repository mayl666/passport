package com.sogou.upd.passport.admin.manager.problem.impl;

import com.sogou.upd.passport.admin.manager.problem.ProblemManager;
import com.sogou.upd.passport.common.result.APIResultSupport;
import com.sogou.upd.passport.common.result.Result;
import com.sogou.upd.passport.common.utils.ErrorUtil;
import com.sogou.upd.passport.service.problem.ProblemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: chenjiameng
 * Date: 13-6-7
 * Time: 下午1:50
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ProblemManagerImpl implements ProblemManager {

    private static Logger logger = LoggerFactory.getLogger(ProblemManagerImpl.class);
    private static final int PROBLEM_CLOSE_STATE = 2;

    @Autowired
    private ProblemService problemService;

    @Override
    public Result updateStatusById(long id, int status) throws Exception {
        Result result = new APIResultSupport(false);
        try {
             int row =problemService.updateStatusById(id, status);
             if(row >0){
                 result.setSuccess(true);
                 result.setMessage("更新问题状态成功");
             }else{
                 result.setCode(ErrorUtil.ERR_CODE_PROBLEM_CLOSE_FAILED);
             }
        }catch (Exception e) {
            logger.error("insertProblem fail,id:" + id, e);
            result.setCode(ErrorUtil.ERR_CODE_PROBLEM_CLOSE_FAILED);
            return result;
        }
        return result;
    }

    @Override
    public Result closeProblemById(long id) throws Exception{
        Result result = updateStatusById(id,PROBLEM_CLOSE_STATE);
        if(result.isSuccess()){
            result.setMessage("关闭问题成功");
        }
        return result;
    }

    @Override
    public int getProblemCount(Integer status,Integer clientId,Integer typeId,Date startDate,Date endDate,String title,  String content){
        return problemService.getProblemCount(status,clientId,typeId,startDate,endDate,title,content);
    }
}
