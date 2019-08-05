/**
 * Created by  on 2016/5/3.
 */
//登录的方法
function login(){
	
    var loginname=$("#loginname").val();
    var password=$("#password").val();
  
    $.ajax({
        url:contextPath+"/Login",
        method:"post",
        data:{loginname:loginname,password:password,action:"login"},
        success:function(data){
        	 var result=$.dealJSONObj(data);
        	
            if(result.code==0){
                window.location.href=contextPath+"/Home?action=index";
            }else{
                showMessage(result.msg)
            }
        }
    })
}