package com.sogou.upd.passport.admin.dao.blackList;

import com.sogou.upd.passport.admin.model.blackList.BlackList;
import com.sogou.upd.passport.admin.model.problem.ProblemAnswer;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: shipengzhi Date: 13-4-17 Time: 下午3:55 To change this template
 * use File | Settings | File Templates.
 */
@DAO
public interface BlackListDAO {

    /**
     * 对应数据库表名称
     */
    String TABLE_NAME = " module_user_blacklist ";

    /**
     * 所有字段列表
     */
    String
            ALL_FIELD =
            " id, userid, account_type, expire_time, create_time,update_time,status ";

    /**
     * 除了id之外所有字段列表
     */
    String
            ALL_FIELD_EXCEPTID =
            " userid, account_type, expire_time,update_time,status ";
    /**
     * 值列表
     */
    String
            VALUE_FIELD_EXCEPTID =
            " :module_user_blacklist.userid, :module_user_blacklist.account_type, :module_user_blacklist.expire_time, :module_user_blacklist.update_time  ";


    /**
     * 插入一条反馈回答
     */
    @SQL(
            "insert into " +
                    TABLE_NAME +
                    "(" + ALL_FIELD_EXCEPTID + ") "
                    + "values (" + VALUE_FIELD_EXCEPTID + ",1)")
    public int insertBlackList(@SQLParam("module_user_blacklist") BlackList BlackList) throws DataAccessException;


    @SQL(
            "update " +
                    TABLE_NAME +
                    "set " +
                    "#if(:module_user_blacklist.status != null){status=:module_user_blacklist.status,} " +
                    "#if(:module_user_blacklist.update_time != null){update_time=:module_user_blacklist.update_time} " +
                    "where id = :module_user_blacklist.id "
            )
    public int updateBlackList(@SQLParam("module_user_blacklist") BlackList BlackList) throws DataAccessException;

    @SQL(
            "update " +
                    TABLE_NAME +
                    "set status = 0 " +
                    "where UNIX_TIMESTAMP(NOW())> expire_time"
    )
    public int updateBlackListExpire() throws DataAccessException;

    @SQL("select " +
            ALL_FIELD +
            " from " +
            TABLE_NAME +
            " where 1=1 "
            + "#if(:userid != null){and userid like CONCAT('%',:userid , '%') }"
            + " order by status DESC,create_time DESC "
            + " #if((:start != null)&&(:end !=null)){ limit :start,:end }"
    )
    public List<BlackList> getBlackList(@SQLParam("userid") String userid,
                                        @SQLParam("start") Integer start,
                                        @SQLParam("end") Integer end
    ) throws DataAccessException;

    @SQL("select " +
            ALL_FIELD +
            " from " +
            TABLE_NAME +
            " where status = 1 "
            + "#if(:userid != null){and userid = :userid }"
    )
    public BlackList getBlackListByUserID(@SQLParam("userid") String userid) throws DataAccessException;

    @SQL("select " +
            ALL_FIELD +
            " from " +
            TABLE_NAME +
            " where 1 = 1 "
            + "#if(:id != null){and id = :id}"
    )
    public BlackList getBlackListByID(@SQLParam("id") String id) throws DataAccessException;

    @SQL("select " +
            ALL_FIELD +
            " from " +
            TABLE_NAME +
            " where status = 1 and UNIX_TIMESTAMP(NOW())< expire_time"
    )
    public List<BlackList> getBlackListByValid() throws DataAccessException;


    @SQL("select count(id)" +
            " from " +
            TABLE_NAME +
            " where 1=1 "
            + "#if(:userid != null){and userid like CONCAT('%',:userid , '%') }"
    )
    public int getBlackListCount(@SQLParam("userid") String userid) throws DataAccessException;
}
