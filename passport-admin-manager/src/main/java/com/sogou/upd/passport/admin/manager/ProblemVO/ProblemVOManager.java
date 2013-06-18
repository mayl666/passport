package com.sogou.upd.passport.admin.manager.ProblemVO;

import com.sogou.upd.passport.admin.model.problemVO.ProblemVO;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: chenjiameng Date: 13-6-4 Time: 下午5:11 To change this template
 * use File | Settings | File Templates.
 */
public interface ProblemVOManager {

  /**
   *
   * @param status
   * @param clientId
   * @param typeId
   * @param startDate
   * @param endDate
   * @param content
   * @return
   * @throws Exception
   */
  public List<ProblemVO> queryProblemVOList(Integer status,Integer clientId,Integer typeId,
                                            Date startDate,Date endDate,String title,String content,Integer start,Integer end) throws Exception;
}
