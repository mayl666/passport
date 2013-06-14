package com.sogou.upd.passport.admin.web.controller;


import com.sogou.upd.passport.admin.manager.ProblemVO.ProblemVOManager;
import com.sogou.upd.passport.admin.manager.problem.ProblemAnswerManager;
import com.sogou.upd.passport.admin.model.problem.ProblemAnswer;
import com.sogou.upd.passport.admin.model.problemVO.ProblemVO;
import com.sogou.upd.passport.admin.web.BaseController;
import com.sogou.upd.passport.common.result.Result;
import com.sogou.upd.passport.common.utils.DateUtil;

import com.sogou.upd.passport.manager.problem.ProblemManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: chenjiameng Date: 13-5-27 Time: 下午7:37 To change this template
 * use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin")
public class ProblemAdminController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ProblemAdminController.class);

    private static final Integer PAGE_SIZE = 50;

    @Autowired
    private ProblemVOManager problemVOManager;

    @Autowired
    private ProblemAnswerManager problemAnswerManager;

    @Autowired
    private ProblemManager problemManager;
    /*
     反馈查询页面
    */
    @RequestMapping(value = "/adminProblem/queryProblem", method = RequestMethod.GET)
    public String queryProblem(@RequestParam("status") int status, @RequestParam("clientId") int clientId, @RequestParam("typeId") int typeId,
                               @RequestParam("startDateStr") String startDateStr, @RequestParam("endDateStr") String endDateStr,
                               @RequestParam("content") String content, @RequestParam("pageNum") int pageNum, Model model) throws Exception {
        Integer queryStatus = null, queryClientId = null, queryTypeId = null, queryStart = null, queryEnd = null;
        Date startDate = null, endDate = null;
        String queryContent = null;
        if (status > 0) {
            queryStatus = status;
        }
        if (clientId > 0) {
            queryClientId = clientId;
        }
        if (typeId > 0) {
            queryTypeId = typeId;
        }

        if ((!startDateStr.equals("-1")) && (!endDateStr.equals("-1"))) {
            startDate = DateUtil.parse(startDateStr, DateUtil.DATE_FMT_3);
            endDate = DateUtil.parse(endDateStr, DateUtil.DATE_FMT_3);
        }
        if (!content.equals("-1")) {
            queryContent = content;
        }
        if (pageNum > 1) {
            queryStart = (pageNum - 1) * PAGE_SIZE + 1;
            queryEnd = pageNum * PAGE_SIZE;
        } else {
            queryStart = 0;
            queryEnd = PAGE_SIZE;
        }

        List<ProblemVO> problemVOList = problemVOManager.queryProblemVOList(queryStatus, queryClientId, queryTypeId,
                startDate, endDate, queryContent, queryStart, queryEnd);


        model.addAttribute("problemList", problemVOList);
        return "/pages/admin/account/accountAdmin.jsp";
    }

    @RequestMapping(value = "/adminProblem/addProblemAnswer", method = RequestMethod.GET)
    @ResponseBody
    public Object addProblemAnswer(HttpServletRequest request,@RequestParam("problemId") int problemId, @RequestParam("ansPassportId") String ansPassportId, @RequestParam("ansContent") String ansContent) throws Exception{
        ProblemAnswer problemAnswer = new ProblemAnswer();
        problemAnswer.setProblemId(problemId);
        problemAnswer.setAnsPassportId(ansPassportId);
        problemAnswer.setAnsContent(ansContent);
        problemAnswer.setAnsTime(new Date());
        Result result = problemAnswerManager.insertProblemAnswer(problemAnswer,getIp(request));
        return result.toString();
    }

    @RequestMapping(value = "/adminProblem/updateProblemStatus", method = RequestMethod.GET)
    @ResponseBody
    public Object updateProblemStatus(@RequestParam("problemId") int problemId, @RequestParam("status") int status) throws Exception{
        return problemManager.updateStatusById(problemId,status);
    }

}
