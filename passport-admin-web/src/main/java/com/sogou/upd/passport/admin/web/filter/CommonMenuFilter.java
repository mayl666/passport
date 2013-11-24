package com.sogou.upd.passport.admin.web.filter;

import com.google.gson.Gson;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import com.sogou.game.cms.common.CommonConf;

public class CommonMenuFilter implements Filter {

  // 访问权限接口。就两个可以访问,没有passport限制.其他接口没有暴露.
  public static final String GetUserMenuListUrl = "http://login_center_api.game.sogou-inc.com/getUserMenuList.do";

  public static final String GetUserMenuHtmlUrl = "http://login_center_api.game.sogou-inc.com/getUserMenuHtml.do";

  public static final String UrlErrorPage = "/url_error.jsp";

  // 设置缓存时间为1分钟。1分钟重新查询一次
  private static long cacheTime = 0L;

  // 系统分类.如新统计系统menuTypeId是1, CMS V3系统的ID是6,passport后台管理系统 是26
  private static final String menuTypeId = "26";

  public void doFilter(ServletRequest request, ServletResponse response,
                       FilterChain chain) throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    String url = httpRequest.getRequestURI();

    String serverName = "";
    request.setAttribute("server_name", serverName);

    // 不包括搜索接口
    if (url.indexOf("/web/searchContent") >= 0) {
      chain.doFilter(request, response);
      return;
    }

    // 菜单html碎片
    String userMenuHtml = (String) httpRequest.getSession().getAttribute(
            "UserMenuHtmlCache");

    // 设置缓存数据
    GetJsonType getJsonType = (GetJsonType) httpRequest.getSession()
            .getAttribute("GetJsonTypeCache");

    System.out.println(cacheTime);
    System.out.println(System.currentTimeMillis() / 60000);
    // System.out.println("userMenuHtml:" + userMenuHtml);
    System.out.println("getJsonType:" + getJsonType);

    // 获得用户Email
    String userEmail = getPassportEmail(httpRequest);
    System.out.println("userEmail:" + userEmail);

    // 3分钟更新一次缓存.***************开始***************
    if (cacheTime != System.currentTimeMillis() / 60000
        || getJsonType == null || userMenuHtml == null) {
      // 如果缓存时间不同查询数据库
      cacheTime = System.currentTimeMillis() / 60000;
      // 解析json UserMenuList.
      {

        // 获得json接口数据.
        String str = getUrl(GetUserMenuListUrl + "?email=" + userEmail
                            + "&menuTypeId=" + menuTypeId);

        // 字符替换处理
        str = commReplace(str);

        Gson gson = new Gson();

        // System.out.println(str);
        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<GetJsonType>() {
        }.getType();

        try {
          // 转换后的json.
          getJsonType = gson.fromJson(str, type);
        } catch (Exception e) {
          e.printStackTrace();
        }
        // 用户名称
      }

      // 获得 userMenuHtml
      {
        String str = getUrl(GetUserMenuHtmlUrl + "?email=" + userEmail
                            + "&menuTypeId=" + menuTypeId);

        // 字符替换处理
        userMenuHtml = commReplace(str);

        // System.out.println(str);
      }

      // 设置用户缓存.
      httpRequest.getSession().setAttribute("GetJsonTypeCache",
                                            getJsonType);
      httpRequest.getSession().setAttribute("UserMenuHtmlCache",
                                            userMenuHtml);
    }
    // 3分钟更新一次缓存.***************结束***************

    // ###############################缓存查询方法结束###############################
    // 设置菜单HTMl
    httpRequest.setAttribute("UserMenuHtml", userMenuHtml);
    // 设置用户名
    httpRequest.setAttribute("UserName", getJsonType.getUserName());
    //设置用户邮箱
    httpRequest.setAttribute("UserPassportId", userEmail);
    // 其他系统菜单
    httpRequest.setAttribute("MenuTypeList", getJsonType.getMenuTypeList());

    // 统计的url是 /pages/admin/开始的
    if (url.indexOf("/admin/") == 0
        && url.indexOf("/admin/userMain.do") < 0) {
      // /pages/admin/index.jsp 不包括首页
      if (getJsonType != null && getJsonType.getMenuList() != null) {
        boolean haveRight = false;

        // 用户菜单
        for (Menu userMenu : getJsonType.getMenuList()) {
          // 判断系统是否有权限
          if (userMenu != null && userMenu.getUrl() != null
              && url.equals(userMenu.getUrl())) {
            // 有权限
            haveRight = true;
            // break;循环全部
          }
        }

        // 判断用户是否没有权限//查询全部菜单.
        if (!haveRight) {
          haveRight = true;
          for (Menu userMenu : getJsonType.getAllMenuList()) {
            // 判断系统是否没有有权限
            if (userMenu != null && userMenu.getUrl() != null
                && url.equals(userMenu.getUrl())) {
              // 有权限
              haveRight = false;
              // break;循环全部
            }
          }
        }

        if (haveRight) {

          if (response != null && request != null) {
            chain.doFilter(request, response);
          }
          return;
        } else {
          httpResponse.sendRedirect(UrlErrorPage);
          return;
        }
      } else {
        httpResponse.sendRedirect(UrlErrorPage);
        return;
      }
    } else {
      if (response != null && request != null) {
        chain.doFilter(request, response);
      }
      return;
    }

  }

  public static final boolean isWindows = System.getProperty("os.name") != null
                                          && System.getProperty("os.name").toLowerCase().contains("windows");

  private String commReplace(String str) throws UnsupportedEncodingException {
    // 转换字符编码
    try {
      if (str == null) {// 如果是null返回
        return "";
      }
      if (!isWindows) {
       // str = new String(str.getBytes("ISO8859_1"), "GBK");
      }
      str = str.replaceAll("\u003d", "=");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return str;
  }

  /**
   * 获得sogou-inc passprot 的email
   */
  String getPassportEmail(HttpServletRequest httpRequest) {
      return "liuling"  + "@sogou-inc.com";
//    if (isWindows) {
//      // 如果是windows返回测试,系统的用户
////      return "test_" + menuTypeId + "@sogou-inc.com";
//      return "liuling"  + "@sogou-inc.com";
//    }
//    String cookieStr = httpRequest.getHeader("Cookie");
//    // 字符串截取.
//    cookieStr = StringUtils.substringBetween(cookieStr, "jpassport-sp={",
//                                             "}");
//    String userEmail = "";
//    if (StringUtils.isNotBlank(cookieStr)) {
//      String[] strArray = cookieStr.split(",");
//      for (String strTemp : strArray) {
//        // System.out.println(strTemp);
//        String[] strName = strTemp.split(":");
//        // System.out.println(strName[0] + "/" + strName[1]);
//        // 循环查找cookie里面的内容.
//        if (strName != null && strName.length == 2
//            && strName[0] != null && strName[1] != null
//            && strName[0].equals("username")) {
//          userEmail = strName[1];
//        }
//      }
//    }
//    return userEmail;
  }

  public void init(FilterConfig filterConfig) throws ServletException {

  }

  public void destroy() {

  }

  /**
   * 转换json类型
   */
  public class GetJsonType implements java.io.Serializable {

    // 用户名称
    private String userName;
    // 返回菜单权限
    private List<Menu> menuList;
    // 所有菜单权限
    private List<Menu> allMenuList;
    // 返回系统类型
    private List<MenuType> menuTypeList;

    public String getUserName() {
      return userName;
    }

    public void setUserName(String userName) {
      this.userName = userName;
    }

    public List<Menu> getMenuList() {
      return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
      this.menuList = menuList;
    }

    public List<MenuType> getMenuTypeList() {
      return menuTypeList;
    }

    public void setMenuTypeList(List<MenuType> menuTypeList) {
      this.menuTypeList = menuTypeList;
    }

    public List<Menu> getAllMenuList() {
      return allMenuList;
    }

    public void setAllMenuList(List<Menu> allMenuList) {
      this.allMenuList = allMenuList;
    }

  }

  // 内部类,Json转换用.
  public class Menu implements java.io.Serializable {

    private Long id;

    private String name;// 菜单名

    private String roleName;// 角色名称,也就是那个菜单有哪些权限。通过url传入参数，并且要校验

    private String url;// 链接

    private Long menuTypeId;// 系统分类

    // 父节点 子菜单通过parentId 获得
    private Long parentId;

    private Long level;// 新增level字段 判断层级关系

    private String menuRole;// 针对菜单的权限配置，里面用正则表示，

    private Long orderId;// 排序id

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getRoleName() {
      return roleName;
    }

    public void setRoleName(String roleName) {
      this.roleName = roleName;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public Long getMenuTypeId() {
      return menuTypeId;
    }

    public void setMenuTypeId(Long menuTypeId) {
      this.menuTypeId = menuTypeId;
    }

    public Long getParentId() {
      return parentId;
    }

    public void setParentId(Long parentId) {
      this.parentId = parentId;
    }

    public Long getLevel() {
      return level;
    }

    public void setLevel(Long level) {
      this.level = level;
    }

    public String getMenuRole() {
      return menuRole;
    }

    public void setMenuRole(String menuRole) {
      this.menuRole = menuRole;
    }

    public Long getOrderId() {
      return orderId;
    }

    public void setOrderId(Long orderId) {
      this.orderId = orderId;
    }

    @Override
    public String toString() {
      return "UserMenu [id=" + id + ", name=" + name + ", roleName="
             + roleName + ", url=" + url + ", menuTypeId=" + menuTypeId
             + ", parentId=" + parentId + ", level=" + level
             + ", menuRole=" + menuRole + ", orderId=" + orderId + "]";
    }

  }

  // 内部类,Json转换用.
  public class MenuType implements java.io.Serializable {

    private String name;// 名称

    private String url;// 链接

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    @Override
    public String toString() {
      return "MenuType [name=" + name + ", url=" + url + "]";
    }

  }

  public static String getUrl_WIN(String url) {
    // （1）构造HttpClient的实例
    HttpClient httpClient = new HttpClient();

    // （2）创建Get方法的实例
    GetMethod getMethod = new GetMethod(url);

    //设置页面编码
    getMethod.addRequestHeader("Content-Type", "text/html; charset=GBK");
//    getMethod.getParams().setContentCharset("GBK");
    System.out.println("get url:" + url);

    // 使用系统提供的默认的恢复策略
    getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                                       new DefaultHttpMethodRetryHandler());
    try {
      // （4）执行postMethod
      int statusCode = httpClient.executeMethod(getMethod);
      if (statusCode != HttpStatus.SC_OK) {
        System.err.println("Method failed: "
                           + getMethod.getStatusLine());
      }
      // （6）读取内容
      // 打印结果页面
      InputStream in = getMethod.getResponseBodyAsStream();
      //这里的编码规则要与上面的相对应
      BufferedReader br = new BufferedReader(new InputStreamReader(in,"gbk"));
      String tempbf;
      StringBuffer html = new StringBuffer(100);
      while ((tempbf = br.readLine()) != null) {
        html.append(tempbf +"\n");
      }
//      System.out.println("html:"+html.toString());
      return html.toString();
    } catch (HttpException e) {
      // 发生致命的异常，可能是协议不对或者返回的内容有问题
      System.out.println("Please check your provided http address!");
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // 释放连接
      getMethod.releaseConnection();
    }
    return "";
  }

    public static String getUrl_Linux(String url) {
        // （1）构造HttpClient的实例
        HttpClient httpClient = new HttpClient();

        // （2）创建Get方法的实例
        GetMethod getMethod = new GetMethod(url);

        System.out.println("get url:" + url);

        // 使用系统提供的默认的恢复策略
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler());
        try {
            // （4）执行postMethod
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: "
                        + getMethod.getStatusLine());
            }
            // （6）读取内容
            byte[] responseBody = getMethod.getResponseBody();
            // （7） 处理内容
            return new String(responseBody);
        } catch (HttpException e) {
            // 发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("Please check your provided http address!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放连接
            getMethod.releaseConnection();
        }
        return "";
    }

    public static String getUrl(String url) {
        if (isWindows) {
            return getUrl_WIN(url);
        } else{
            return getUrl_Linux(url);
        }
    }

}