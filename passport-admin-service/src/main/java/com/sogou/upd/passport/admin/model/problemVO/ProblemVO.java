package com.sogou.upd.passport.admin.model.problemVO;

import com.sogou.upd.passport.model.problem.Problem;

/**
 * Created with IntelliJ IDEA. User: chenjiameng Date: 13-6-4 Time: 下午4:59 To change this template
 * use File | Settings | File Templates.
 */
public class ProblemVO extends Problem {
    private String typeName;

    public ProblemVO(String typeName,Problem problem) {
        this.typeName = typeName;

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
}
