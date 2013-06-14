package com.sogou.upd.passport.admin.service.problem.impl;

import com.google.common.base.Strings;
import com.sogou.upd.passport.admin.dao.problem.ProblemAnswerDAO;
import com.sogou.upd.passport.admin.model.problem.ProblemAnswer;
import com.sogou.upd.passport.admin.service.problem.ProblemAnswerService;
import com.sogou.upd.passport.common.CacheConstant;
import com.sogou.upd.passport.common.utils.RedisUtils;
import com.sogou.upd.passport.exception.ServiceException;
import com.sogou.upd.passport.service.problem.impl.ProblemServiceImpl;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenjiameng
 * Date: 13-6-7
 * Time: 下午12:29
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ProblemAnswerServiceImpl implements ProblemAnswerService {
    private static final Logger logger = LoggerFactory.getLogger(ProblemServiceImpl.class);

    @Autowired
    private ProblemAnswerDAO problemAnswerDAO;


    @Override
    public int insertProblemAnswer(ProblemAnswer problemAnswer) throws ServiceException {
        try {
            int row = problemAnswerDAO.insertProblemAnswer(problemAnswer);
            return row;
        } catch (Exception e) {
            throw new ServiceException();
        }
    }

    @Override
    public List<ProblemAnswer> getAnswerListByProblemId(long id) throws ServiceException {
        List<ProblemAnswer> list =  problemAnswerDAO.getAnswerListByProblemId(id);;
        return list;
    }

    @Override
    public int getAnswerSizeByProblemId(long problem_id)throws ServiceException {
        int count  = problemAnswerDAO.getAnswerSizeByProblemId(problem_id);
        return count;
    }

}
