package com.sogou.upd.passport.admin.model.problemVO;

import com.sogou.upd.passport.admin.model.problem.ProblemAnswer;
import com.sogou.upd.passport.model.problem.Problem;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: chenjiameng Date: 13-6-4 Time: 下午4:59 To change this template
 * use File | Settings | File Templates.
 */
public class ProblemVO extends Problem {
    private String mobile;
    private String typeName;
    private List<ProblemAnswer> answerList;

    public ProblemVO(String mobile, String typeName,List<ProblemAnswer> answerList, Problem problem) {
        this.mobile = mobile;
        this.typeName = typeName;
        this.answerList =  answerList;

        this.setId(problem.getId());
        this.setContent(problem.getContent());
        this.setTypeId(problem.getTypeId());
        this.setSubTime(problem.getSubTime());
        this.setClientId(problem.getClientId());
        this.setPassportId(problem.getPassportId());
        this.setStatus(problem.getStatus());
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<ProblemAnswer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<ProblemAnswer> answerList) {
        this.answerList = answerList;
    }
}
