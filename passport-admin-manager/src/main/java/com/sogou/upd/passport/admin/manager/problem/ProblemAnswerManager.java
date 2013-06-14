package com.sogou.upd.passport.admin.manager.problem;

import com.sogou.upd.passport.admin.model.problem.ProblemAnswer;
import com.sogou.upd.passport.common.result.Result;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenjiameng
 * Date: 13-6-7
 * Time: 下午1:50
 * To change this template use File | Settings | File Templates.
 */
public interface ProblemAnswerManager {

    /**
     * @param problemAnswer
     * @return
     * @throws Exception
     */
    public Result insertProblemAnswer(ProblemAnswer problemAnswer, String ip) throws Exception;

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public List<ProblemAnswer> getAnswerListByProblemId(long id) throws Exception;

    /**
     * 获取VO列表
     * @param id
     * @return
     * @throws Exception
     */
    public Result getAnswerVOList(long id, String passportId) throws Exception;

}
