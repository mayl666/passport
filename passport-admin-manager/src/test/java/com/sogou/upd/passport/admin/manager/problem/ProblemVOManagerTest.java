package com.sogou.upd.passport.admin.manager.problem;

import com.sogou.upd.passport.admin.BaseTest;
import com.sogou.upd.passport.admin.manager.ProblemVO.ProblemVOManager;
import com.sogou.upd.passport.admin.model.problem.ProblemAnswer;
import com.sogou.upd.passport.admin.model.problemVO.ProblemVO;
import com.sogou.upd.passport.common.result.Result;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * User: ligang201716@sogou-inc.com
 * Date: 13-6-7
 * Time: 下午2:02
 */
public class ProblemVOManagerTest extends BaseTest {

    @Autowired
    private ProblemVOManager problemVOManager;


    private static final int clientId = 1100;
    private static final String username = "18600369478";
    private static final String ip = "192.168.226.174";
    private static final String pwd = "123456";


//Integer status,Integer clientId,Integer typeId,
//Date startDate,Date endDate,String title,String content,Integer start,Integer end
    @Test
    public void testAccountLogin() {
        try {
            List<ProblemVO> list = problemVOManager.queryProblemVOList(0,null,null,null,null,null,null,null,null);
            System.out.println("testAccountLogin:"+list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
