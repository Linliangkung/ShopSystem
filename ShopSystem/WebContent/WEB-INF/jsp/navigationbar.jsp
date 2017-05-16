<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="span24">
		<ul class="mainNav">
					<li>
						<a href="${pageContext.request.contextPath}/index.action">首页</a>
						|
					</li>
					
					<s:iterator value="#session.categories">
						<li>
							<a ><s:property value="cname"/></a>
							|
						</li>
					</s:iterator>					
		</ul>
	</div>