<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0080)http://localhost:8080/mango/login.jhtml?redirectUrl=%2Fmango%2Fcart%2Flist.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>商家登录</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css">
<!-- 表单验证信息错误提示样式 -->
<link href="${pageContext.request.contextPath}/css/error.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="container header">
	<div class="span5">
		<div class="logo">
			<a >
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.gif" alt="zhku">
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
					<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障">
</div>	</div>
</div>	<div class="container login">
		<div class="span12">
<div class="ad">
					<img src="${pageContext.request.contextPath}/image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">
</div>		</div>
		<div class="span12 last">
			<div class="wrap">
				<div class="main">
					<div class="title">
						<strong>商家登录</strong>SELLER LOGIN
					</div>
					<form id="loginForm"  method="post" action="${pageContext.request.contextPath }/seller/seller_login.action">
						<table>
							<tbody><tr>
								<th>
										账&nbsp;&nbsp;号:
								</th>
								<td>
									<input type="text" id="username" name="saccount" class="text" maxlength="20" value="${param.saccount}"> <label class="error"><s:property value="fieldErrors.username[0]"/> </label> 
								</td>
							</tr>
							<tr>
								<th>
									密&nbsp;&nbsp;码:
								</th>
								<td>
									<input type="password" id="password" name="spassword" class="text" maxlength="20" autocomplete="off"><label class="error"><s:property value="fieldErrors.password[0]"/> </label> 
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
										<dt>你想成为商户？</dt>
										<dd>
											立即注册商户,来管理自己的商城吧！
											<a href="${pageContext.request.contextPath}/seller/seller_registPage.action">立即注册</a>
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