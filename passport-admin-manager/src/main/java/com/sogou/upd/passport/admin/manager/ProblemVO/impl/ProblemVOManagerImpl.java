package com.sogou.upd.passport.admin.manager.ProblemVO.impl;

import com.sogou.upd.passport.admin.manager.ProblemVO.ProblemVOManager;
import com.sogou.upd.passport.admin.model.problemVO.ProblemVO;
import com.sogou.upd.passport.model.problem.Problem;
import com.sogou.upd.passport.model.problem.ProblemType;
import com.sogou.upd.passport.service.problem.ProblemService;
import com.sogou.upd.passport.service.problem.ProblemTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: chenjiameng Date: 13-6-4 Time: 下午5:12 To change this template
 * use File | Settings | File Templates.
 */
@Component
public class ProblemVOManagerImpl implements ProblemVOManager {
    private static final Logger logger = LoggerFactory.getLogger(ProblemVOManagerImpl.class);

    @Autowired
    private ProblemService problemService;
    @Autowired
    private ProblemTypeService problemTypeService;


    /*

     */
    @Override
    public List<ProblemVO> queryProblemVOList(Integer status, Integer clientId, Integer typeId,
                                              Date startDate, Date endDate, String title, String content, Integer start, Integer end) throws Exception {
        try {
            List<Problem> list = problemService.queryProblemList(status, clientId, typeId, startDate,
                    endDate, title, content, start, end);
            List<ProblemVO> resultList = new ArrayList<ProblemVO>();
            for (Problem problem : list) {
                ProblemType problemType = problemTypeService.getProblemTypeById(problem.getTypeId());
                ProblemVO problemVO = new ProblemVO(problemType.getTypeName(), problem);
                resultList.add(problemVO);
            }
            return resultList;

        } catch (Exception e) {
            logger.error("queryProblemVOList failed", e);
            return null;
        }
    }
}
