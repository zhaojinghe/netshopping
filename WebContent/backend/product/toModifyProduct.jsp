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
            </div>
            
            <br>
            <form action="${ctx}/modify" method="post" id="productAdd" onsubmit="return checkProduct();">
            <table border="0" class="add_tab" style="width:930px;" cellspacing="0" cellpadding="0">
                <tr>
                  <input type="hidden"  value="${productList.categorylevel1id}" id="level1id">
                      <input type="hidden"  value="${productList.categorylevel2id}" id="level2id">
                       <input type="hidden"  value="${productList.categorylevel3id}" id="level3id">
                    <td width="135" align="right">一级分类</td>
                    <td colspan="3" style="font-family:'宋体';">
                        <select name="categorylevel1id" style="background-color:#f6f6f6;"
                                id="productCategoryLevel1" onchange="selectCategoryLevel(this.value);">
                            <option value="${productList.categorylevel1id}" selected="selected">请选择...</option>
                       
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="135" align="right">二级分类</td>
                    <td>
                        <select name="categorylevel2id" style="background-color:#f6f6f6;"
                                id="productCategoryLevel2" onchange="selectCategoryLevel2(this.value);">
                            <option value="" selected="selected">请选择...</option>
                           
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="135" align="right">三级分类</td>
                    <td>
                        <select name="categorylevel3id" style="background-color:#f6f6f6;"
                                id="productCategoryLevel3">
                            <option value="" selected="selected"></option>
                        
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="135" align="right">商品名称</td>
                    <td>
                        <input type="text" value="${productList.name}" class="add_ipt" name="name" id="name"/>（必填）
                        <input type="hidden" name="id" value="${productList.id}" id="id">
                    </td>
                </tr>
                <tr>
                    <td width="135" align="right">商品图片</td>
                    <td>
                        <c:if test="${product.fileName!=null && product.fileName!=''}">
                            <img src="${ctx}/files/${productList.fileName}" width="50" height="50"/>
                        </c:if>
                        <input type="file" class="text" name="photo" /><span></span>
                    </td>
                </tr>
                <tr>
                    <td width="135" align="right">单价</td>
                    <td>
                        <input type="text" value="${productList.price}" class="add_ipt" name="price" id="price"/>
                    </td>
                </tr>
                <tr>
                    <td width="135" align="right">库存</td>
                    <td>
                        <input type="text" value="${productList.stock}" class="add_ipt" name="stock" id="stock"/>
                    </td>
                </tr>
                <tr>
                    <td width="135" align="right">描述</td>
                    <td>
                        <textarea name="description">${productList.description}</textarea>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                    
                   
                                <input type="submit" value="确定修改" class="s_btn">
                       
                    </td>
                </tr>
            </table>
            </form>
        </div>
    </div>
    <%@ include file="/common/pre/footer.jsp" %>
</div>
<script  src="${ctx}/statics/static/js/jquery/jquery-1.12.4.min.js"></script>
<script  src="${ctx}/statics/static/js/jquery/jquery.cookie.js"></script>
<script  src="${ctx}/statics/static/js/jquery/jquery.form.js"></script>
<script  src="${ctx}/statics/static/js/common/base.js"></script>
<script  src="${ctx}/statics/static/js/common/normal.js"></script>
<script  src="${ctx}/statics/static/js/moment/moment.min.js"></script>
<script  src="${ctx}/statics/static/js/numeral/chs.min.js"></script>
<script  src="${ctx}/statics/static/js/numeral/numeral.min.js"></script>


<script >
var Base ={"path":"${ctx}/"};


 $(function () {
	 var  id=$("#level1id").val();

    $.get("querybyid".parseUrl(Base.path),{id:id}).always(init);
    var  id=$("#level2id").val();
	  
	 $.get("querybyid".parseUrl(Base.path),{id:id}).always(init1);
	  var  id=$("#level3id").val();
		 $.get("querybyid".parseUrl(Base.path),{id:id}).always(init2);

});

   function init(data) {

      var result=$.dealJSONObj(data);
   
      $('#productCategoryLevel1').selectRenderByArray(result.data, "id", "name", "", "");
   }
   

   function init1(data) {

	      var result=$.dealJSONObj(data);

	      $('#productCategoryLevel2').selectRenderByArray(result.data, "id", "name", "", "");
	   }
   

	   function init2(data) {

		      var result=$.dealJSONObj(data);

		      $('#productCategoryLevel3').selectRenderByArray(result.data, "id", "name", "", "");
		   }  
		   
		   $(function () {
			   
			    $.get("query1".parseUrl(Base.path)).always(init);


			});

			   function init(data) {

			      var result=$.dealJSONObj(data);

			      $('#productCategoryLevel1').selectRenderByArray(result.data, "id", "name", "", "");
			   }
			   
			   function selectCategoryLevel(data){
				   
				 $.get("query2".parseUrl(Base.path),{level1Id:data}).always(init1);
			   }
			   function init1(data) {

				      var result=$.dealJSONObj(data);

				      $('#productCategoryLevel2').selectRenderByArray(result.data, "id", "name", "", "");
				   }
			   
			   function selectCategoryLevel2(data){
				   
					 $.get("query3".parseUrl(Base.path),{level2Id:data}).always(init2);
				   }
				   function init2(data) {

					      var result=$.dealJSONObj(data);

					      $('#productCategoryLevel3').selectRenderByArray(result.data, "id", "name", "", "");
					   }
</script>
</body>
</html>


