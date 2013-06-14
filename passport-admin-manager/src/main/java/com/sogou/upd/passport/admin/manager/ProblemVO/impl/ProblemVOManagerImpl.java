package com.sogou.upd.passport.admin.manager.ProblemVO.impl;

import com.sogou.upd.passport.admin.manager.ProblemVO.ProblemVOManager;
import com.sogou.upd.passport.admin.model.problemVO.ProblemVO;
import com.sogou.upd.passport.admin.service.problem.ProblemAnswerService;
import com.sogou.upd.passport.service.problem.ProblemService;
import com.sogou.upd.passport.service.problem.ProblemTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: chenjiameng Date: 13-6-4 Time: 下午5:12 To change this template
 * use File | Settings | File Templates.
 */
@Component
public class ProblemVOManagerImpl implements ProblemVOManager {
    private static final Logger logger = LoggerFactory.getLogger(ProblemVOManagerImpl.class);

//    @Autowired
//    private ProblemService problemService;
//    @Autowired
//    private ProblemTypeService problemTypeService;
//    @Autowired
//    private ProblemAnswerService problemAnswerService;



    /*

     */
    @Override
    public List<ProblemVO> queryProblemVOList(Integer status, Integer clientId, Integer typeId,
                                              Date startDate, Date endDate, String content, Integer start, Integer end) throws Exception {
//        List<Problem> list = problemService.queryProblemList(status, clientId, typeId, startDate,
//                endDate, content, start, end);
//        List<ProblemVO> resultList = new ArrayList<ProblemVO>();
//        for (Problem problem : list) {
//            Account account = accountManager.queryAccountByPassportId(problem.getPassportId());
//            if (account != null) {
//                String typeName = problemTypeService.getTypeNameById(problem.getTypeId());
//                List<ProblemAnswer> problemAnswerList =  problemAnswerService.getAnswerListByProblemId(problem.getId());
//                ProblemVO problemVO = new ProblemVO(account.getMobile(), typeName,problemAnswerList, problem);
//                resultList.add(problemVO);
//            } else {
//                logger.error("反馈id:" + problem.getId() + ";用户passportId:" + problem.getPassportId() + "在account中未能找到！");
//            }
//        }
//        return resultList;
        return null;

    }
}
