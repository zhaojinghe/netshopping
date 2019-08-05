<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/common/pre/header.jsp" %>
    <script src="${ctx}/statics/js/backend/backend.js"></script>
</head>
<body>
<%@ include file="/common/backend/searchBar.jsp" %>
<!--End Header End-->
<div class="i_bg bg_color">
    <!--Begin 用户中心 Begin -->
    <div class="m_content">
        <%@ include file="/common/backend/leftBar.jsp" %>
        <div class="m_right" id="content">
            <div class="mem_tit">
             
                        添加用户
  
            </div>
            <br>
            <form action="${ctx}/adduser?action=addUser" method="post" id="userAdd" onsubmit="return checkUser();">
                <table border="0" class="add_tab" style="width:930px;" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="135" align="right">用户姓名</td>
                        <td colspan="3" style="font-family:'宋体';">
                            <input type="text" value="${user.loginname}" class="add_ipt" name="loginname"/>
                            <input type="hidden" value="${user.id}" name="id">
                        </td>
                    </tr>
                    <tr>
                        <td width="135" align="right">真实姓名</td>
                        <td>
                            <input type="text" value="${user.username}" class="add_ipt" name="username"/>
                        </td>
                    </tr>
                   
                        <tr>
                            <td width="135" align="right">密码</td>
                            <td>
                                <input type="password" value="" class="add_ipt" name="password"/>
                            </td>
                        </tr>
                        <tr>
                            <td width="135" align="right">确认密码</td>
                            <td>
                                <input type="password" value="" class="add_ipt" name="repPassword"/>
                            </td>
                        </tr>
                  
                    <tr>
                        <td width="135" align="right">身份证号</td>
                        <td>
                            <input type="text" value="${user.identitycode}" class="add_ipt" name="identitycode"
                                   id="identityCode"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="135" align="right">电子邮箱</td>
                        <td>
                            <input type="text" value="${user.email}" class="add_ipt" name="email" id="email"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="135" align="right">手机</td>
                        <td>
                            <input type="text" value="${user.mobile}" class="add_ipt" name="mobile" id="mobile"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="135" align="right">用户类型</td>
                        <td>
                             <select name="type">
                            
                                <option value="1" selected='selected'>管理员</option>
                                <option value="0" selected="selected">普通用户</option>
                            </select>
                        </td>
                    </tr>
                     <tr>
                        <td></td>
                        <td>
                       
                                
                          <input type="submit" value="添加用户" class="s_btn" />
                             
                          
                        </td>
                    </tr>
                    </table>
                    </form>
           
            <%@ include file="/common/pre/footer.jsp" %>
</div>
</body>
</html>


