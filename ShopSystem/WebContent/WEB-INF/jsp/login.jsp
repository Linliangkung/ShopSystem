<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0080)http://localhost:8080/mango/login.jhtml?redirectUrl=%2Fmango%2Fcart%2Flist.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>会员登录</title>
<meta name="author" content="Mango Team">
<meta name="copyright" content="Mango">
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css">
<!-- 表单验证信息错误提示样式 -->
<link href="${pageContext.request.contextPath}/css/error.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.7.2.js"></script>
<!-- 表单校验插件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-validate/jquery.validate.min.js"></script>

<script type="text/javascript">
	
	$(document).ready(function(){

		$('#loginForm').validate({
			onkeyup:false,
			onclick:false,
			onfocusout:false,
			rules:{
				username:{
					required: true					
				},
				password:{
					required: true
				},
				captcha:{
					required:true,
					minlength:4,
					maxlength:4
				}
			},
			messages: {
			 	username:{
			 		required:"请输入用户名"
			 	},
			 	password:{
			 		required:"请输入密码"
		 		},
		 		captcha:{
					required:"请输入验证码",
					minlength:"验证码长度必须为4位",
					maxlength:"验证码长度必须为4为"
				}
			},
			errorPlacement: function(error, element) {
					
				if(element.attr('name')=='captcha'){
					alert(error.html());
					element.attr('class','text captcha');
				}else{
					error.insertAfter(element);
					element.attr('class','text');
					}
				}
		});
		
		_change();
		
	});
	
	//点击换一张,更换图片
	function _change() {
	        $("#captchaImage").attr('src', '${pageContext.request.contextPath}/Kaptcha.jpg?' + Math.floor(Math.random() * 100));
	}
	//点击图片本身更换图片
	$(function(){
		    $('#captchaImage').click(function() {
		        $(this).attr('src', '${pageContext.request.contextPath}/Kaptcha.jpg?' + Math.floor(Math.random() * 100));
		    })
		});
	</script>

</head>
<body>

<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="http://localhost:8080/mango/">
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.gif" alt="zhku">
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
					<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障">
</div>	</div>
	<%@ include file="header.jsp" %>
	<%@ include file="navigationbar.jsp" %>
	<div class="span24">
		<div class="tagWrap">
			<ul class="tag">
						<li class="icon" style="background: url(http://storage.shopxx.net/demo-image/3.0/tag/hot.gif) right no-repeat;">
							<a >热销</a>
						</li>
						<li class="icon" style="background: url(http://storage.shopxx.net/demo-image/3.0/tag/new.gif) right no-repeat;">
							<a>最新</a>
						</li>
			</ul>
			<div class="hotSearch">
					热门搜索:
						<a>水蜜桃</a>
						<a>西瓜</a>
						<a>紫薯</a>
						<a>大米</a>
						<a>玉米</a>
						<a>茄子</a>
						<a>辣椒</a>
						<a>圣女果</a>
						<a>鱿鱼丝</a>
			</div>
			<div class="search">
			
				<form id="productSearchForm"  method="get">
					<input name="keyword" class="keyword" value="商品搜索" maxlength="30">
					<button type="submit">搜索</button>
				</form>
			</div>
		</div>
	</div>
</div>	<div class="container login">
		<div class="span12">
<div class="ad">
					<img src="${pageContext.request.contextPath}/image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">
</div>		</div>
		<div class="span12 last">
			<div class="wrap">
				<div class="main">
					<div class="title">
						<strong>会员登录</strong>USER LOGIN
					</div>
					<form id="loginForm"  method="post" action="${pageContext.request.contextPath }/user_login.action">
						<table>
							<tbody><tr>
								<th>
										用户名/E-mail:
								</th>
								<td>
									<input type="text" id="username" name="username" class="text" maxlength="20" value="${param.username}"> <label class="error"><s:property value="fieldErrors.username[0]"/> </label> 
								</td>
							</tr>
							<tr>
								<th>
									密&nbsp;&nbsp;码:
								</th>
								<td>
									<input type="password" id="password" name="password" class="text" maxlength="20" autocomplete="off"><label class="error"><s:property value="fieldErrors.password[0]"/> </label> 
								</td>
							
							</tr>
							 <tr>
									<th>
										验证码:
									</th>
									<td>
										<span class="fieldSet">
											  <input type="text" id="captcha" name="captcha" class="text captcha" maxlength="4" autocomplete="off"><img id="captchaImage" class="captchaImage"  > <a style="color: blue" href="javascript:_change()">换一张</a>
											  <label class="error"><s:property value="fieldErrors.captcha[0]"/> </label>
										</span>
									</td>
							</tr>
								
							<tr>
								<th>&nbsp;
									
								</th>
								<td>
									<label>
										<input type="checkbox" id="isRememberUsername" name="autologin" value="true" <s:if test="autologin">checked="checked"</s:if>/>自动登录
									</label>
									<!--  <label>
										&nbsp;&nbsp;<a >找回密码</a>
									</label>
									-->
								</td>
							</tr>
									
							<tr>
								<th>&nbsp;
									
								</th>
								<td >
									<input  type="submit" class="submit" value="登 录"/> <s:if test="actionErrors.size()!=0"><label class="error"><s:property value="actionErrors[0]"></s:property></label></s:if>
								</td>
							</tr>
							<tr class="register">
								<th>&nbsp;
									
								</th>
								<td>
									<dl>
										<dt>还没有注册账号？</dt>
										<dd>
											立即注册即可体验在线购物！
											<a href="${pageContext.request.contextPath}/user_registPage.action">立即注册</a>
										</dd>
									</dl>
								</td>
							</tr>
						</tbody></table>
					</form>
				</div>
			</div>
		</div>
	</div>
<div class="container footer">
	<div class="span24">
	  <div class="footerAd"><img src="${pageContext.request.contextPath}/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势" /></div>	
	</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a >关于我们</a>
						|
					</li>
					<li>
						<a>联系我们</a>
						|
					</li>
					<li>
						<a>招贤纳士</a>
						|
					</li>
					<li>
						<a>法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式  </a>
						|
					</li>
					<li>
						<a  target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a>服务声明</a>
						|
					</li>
					<li>
						<a>广告声明</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2013 Mango商城 版权所有</div>
	</div>
</div>
</body></html>