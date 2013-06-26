package com.sogou.upd.passport.admin.model.problemVO;

import com.sogou.upd.passport.admin.common.parameter.ProblemStateEnum;
import com.sogou.upd.passport.model.problem.Problem;

/**
 * Created with IntelliJ IDEA. User: chenjiameng Date: 13-6-4 Time: 下午4:59 To change this template
 * use File | Settings | File Templates.
 */
public class ProblemVO extends Problem {
    private String typeName;
    private String stateName;

    public ProblemVO(String typeName,Problem problem) {
        this.typeName = typeName;
        ProblemStateEnum problemStateEnum = ProblemStateEnum.getProblemState(problem.getStatus());
        if(ProblemStateEnum.ANSWERED.equals(problemStateEnum)){
            this.stateName = "已回复";
        }else if(ProblemStateEnum.CLOSED.equals(problemStateEnum)){
            this.stateName ="已关闭";
        }else {
            this.stateName ="未回复";
        }
        this.setId(problem.getId());
        this.setTitle(problem.getTitle());
        this.setEmail(problem.getEmail());
        this.setContent(problem.getContent());
        this.setTypeId(problem.getTypeId());
        this.setSubTime(problem.getSubTime());
        this.setClientId(problem.getClientId());
        this.setPassportId(problem.getPassportId());
        this.setStatus(problem.getStatus());
    }


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
