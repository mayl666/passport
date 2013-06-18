package com.sogou.upd.passport.admin.manager.problem;

import com.sogou.upd.passport.admin.BaseTest;
import com.sogou.upd.passport.admin.model.problem.ProblemAnswer;
import com.sogou.upd.passport.common.result.Result;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * User: ligang201716@sogou-inc.com
 * Date: 13-6-7
 * Time: 下午2:02
 */
public class ProblemAnswerManagerTest extends BaseTest {

    @Autowired
    private ProblemAnswerManager problemAnswerManager;


    private static final int clientId = 1100;
    private static final String username = "18600369478";
    private static final String ip = "192.168.226.174";
    private static final String pwd = "123456";
//    private static String passpword = Coder.encryptMD5("spz1986411");
    @Test
    public void testAccountLogin() {
        try {
//            WebAddProblemParameters webAddProblemParameters = new WebAddProblemParameters();
//            webAddProblemParameters.setPassportId("18612532596@sohu.com");
//            webAddProblemParameters.setContent("搜狗通行证很好");
//            webAddProblemParameters.setEmail("jiamengchen@126.com");
//            webAddProblemParameters.setTitile("标题党");
//            webAddProblemParameters.setTypeId(1);
            ProblemAnswer problemAnswer = new ProblemAnswer();
            problemAnswer.setAnsContent("你好，这个问题我们已经收到了，会马上处理");
            problemAnswer.setAnsPassportId("chenjiameng@sogou-inc.com");
            problemAnswer.setAnsTime(new Date());
            problemAnswer.setProblemId(1);
            Result result = problemAnswerManager.addProblemAnswer(problemAnswer,"jiamengchen@126.com");
            System.out.println("testAccountLogin:"+result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
