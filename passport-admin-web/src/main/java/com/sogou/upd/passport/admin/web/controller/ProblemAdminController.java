package com.sogou.upd.passport.admin.web.controller;


import com.google.common.base.Strings;
import com.sogou.upd.passport.admin.manager.ProblemVO.ProblemVOManager;
import com.sogou.upd.passport.admin.manager.form.ProblemQueryParam;
import com.sogou.upd.passport.admin.manager.problem.ProblemAnswerManager;
import com.sogou.upd.passport.admin.model.problem.ProblemAnswer;
import com.sogou.upd.passport.admin.model.problemVO.ProblemVO;
import com.sogou.upd.passport.admin.web.BaseController;
import com.sogou.upd.passport.common.result.Result;
import com.sogou.upd.passport.common.utils.DateUtil;

import com.sogou.upd.passport.manager.problem.ProblemManager;
import com.sogou.upd.passport.manager.problem.ProblemTypeManager;
import com.sogou.upd.passport.model.problem.ProblemType;
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

    @Autowired
    private ProblemTypeManager problemTypeManager;

    @RequestMapping(value = "/adminProblem/index", method = {RequestMethod.GET,RequestMethod.POST})
    public String addProblem(HttpServletRequest request, Model model)
            throws Exception {

        //获取问题类型列表
        List<ProblemType> typeList = problemTypeManager.getProblemTypeList();
        model.addAttribute("typeList", typeList);

        return "/pages/admin/feedback/problemAdmin.jsp";
    }



    /*
     反馈查询页面
    */
    @RequestMapping(value = "/adminProblem/queryProblem", method = RequestMethod.POST)
    public String queryProblem(ProblemQueryParam problemQueryParam,Model model) throws Exception {
        Integer queryStatus = null, queryClientId = null, queryTypeId = null, queryStart = null, queryEnd = null;
        Date startDate = null, endDate = null;
        String queryTitle=null,queryContent = null;
        if (problemQueryParam.getStatus() > 0) {
            queryStatus = problemQueryParam.getStatus() ;
        }
        if (problemQueryParam.getClientId() > 0) {
            queryClientId = problemQueryParam.getClientId();
        }
        if (problemQueryParam.getTypeId() > 0) {
            queryTypeId = problemQueryParam.getTypeId();
        }

        if ((!Strings.isNullOrEmpty(problemQueryParam.getStartDateStr())
                && (!Strings.isNullOrEmpty(problemQueryParam.getEndDateStr())))) {
            startDate = DateUtil.parse(problemQueryParam.getStartDateStr(), DateUtil.DATE_FMT_3);
            endDate = DateUtil.parse(problemQueryParam.getEndDateStr(), DateUtil.DATE_FMT_3);
        }
        if (!Strings.isNullOrEmpty(problemQueryParam.getContent())) {
            queryContent = problemQueryParam.getContent();
        }
        if (!!Strings.isNullOrEmpty(problemQueryParam.getTitle())) {
            queryTitle = problemQueryParam.getTitle();
        }
        if (problemQueryParam.getPageNum() > 1) {
            queryStart = (problemQueryParam.getPageNum() - 1) * PAGE_SIZE + 1;
            queryEnd = problemQueryParam.getPageNum() * PAGE_SIZE;
        } else {
            queryStart = 0;
            queryEnd = PAGE_SIZE;
        }

        List<ProblemVO> problemVOList = problemVOManager.queryProblemVOList(queryStatus, queryClientId, queryTypeId,
                startDate, endDate,queryTitle, queryContent, queryStart, queryEnd);


        model.addAttribute("problemVOList", problemVOList);
        return "forward:/admin/adminProblem/index";
    }

    @RequestMapping(value = "/adminProblem/addProblemAnswer", method = RequestMethod.POST)
    @ResponseBody
    public Object addProblemAnswer(HttpServletRequest request,@RequestParam("_problemId") int _problemId,
                                   @RequestParam("_email") String _email,@RequestParam("_ansPassportId") String _ansPassportId, @RequestParam("_ansContent") String _ansContent) throws Exception{
        ProblemAnswer problemAnswer = new ProblemAnswer();
        problemAnswer.setProblemId(_problemId);
        problemAnswer.setAnsPassportId(_ansPassportId);
        problemAnswer.setAnsContent(_ansContent);
        problemAnswer.setAnsTime(new Date());
        Result result = problemAnswerManager.addProblemAnswer(problemAnswer,_email);
        return result.toString();
    }

    @RequestMapping(value = "/adminProblem/updateProblemStatus", method = RequestMethod.POST)
    @ResponseBody
    public Object updateProblemStatus(@RequestParam("_problemId") int problemId, @RequestParam("_status") int status,@RequestParam("_ansPassportId") String ansPassportId) throws Exception{
        Result result = problemManager.updateStatusById(problemId,status);
        logger.info("changeProblemState:problemId="+problemId+",status="+status+",ansPassportId="+ansPassportId);
        return  result.toString();
    }

}
