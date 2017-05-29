 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>我的订单</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript">
<s:if test="[2].actionErrors[0]!=null">
$(document).ready(function(){ 
		alert("<s:property value="[2].actionErrors[0]"/>")
	}); 
</s:if>
</script>
</head>
<body>
<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="http://localhost:8080/mango/">
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.gif" alt="传智播客">
			</a>
		</div>
	</div>
	<div class="span9">
				<div class="headerAd">
					<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障">
				</div>	
	</div>
	<%@ include file="header.jsp" %>
	<%@ include file="navigationbar.jsp" %>
</div>	
<div class="container cart">
		<div class="span24">
			<div class="step step1">
			</div>
			<s:iterator value="#orderPageBean.list" >
			<div style="border: 2px solid #000000;padding: 5px">
				订单号: <strong id="effectivePrice"><s:property value="oid"/> </strong><br/>
				下单时间:<strong id="effectivePrice"><s:date name="ordertime" format="yyyy-MM-dd HH:mm:ss" /></strong><br/>
				订单状态: <strong><font color="red">
					<s:if test="state==0">
						未支付:交易将在<s:property value="@com.zhku.shopsystem.utils.TimeUtils@getSurplusTimeMsg(ordertime)"/>被关闭
					</s:if>
					<s:elseif test="state==1">
						未发货
					</s:elseif>
					<s:elseif test="state==2">
						已发货
					</s:elseif>
					<s:elseif test="state==3">
						确认收货
					</s:elseif>
					</font>
					</strong>
				
				<table>
					<tbody>
					<tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
					</tr>
					<s:iterator value="orderItems" >
						<tr>
							<td width="60">
								<input type="hidden" name="id" value="22">
								<a href="${pageContext.request.contextPath }/product_detail?pid=<s:property value="product.pid"/>"><img src="${pageContext.request.contextPath }/loadProductImage.action?pid=<s:property value="product.pid"/>"/></a>
							</td>
							<td width="200">
								<a href="${pageContext.request.contextPath }/product_detail?pid=<s:property value="product.pid"/>"><s:property value="product.pname"/></a>
							</td>
							<td>
								￥<s:property value="product.shop_price"/>
							</td>
							<td class="quantity" width="60">
								<input type="text" name="quantity" value="<s:property value="quantity" />"  maxlength="4" onpaste="return false;"  readonly="readonly">
							</td>
							<td width="140">
								<span class="subtotal">￥<s:property value="subtotal"/></span>
							</td>
						</tr>
						</s:iterator>
				</tbody></table>
				<div class="total">
					订单金额: <strong id="effectivePrice">￥<s:property value="total"/>元</strong>
				</div>
				<s:if test="state==0">
						<div class="bottom">
							<a href="${pageContext.request.contextPath }/order_deleteUnpayOrder.action?oid=<s:property value="oid"/>" id="clear" class="clear">删除订单</a>
							<a href="${pageContext.request.contextPath }/order_detail.action?oid=<s:property value="oid"/>" id="submit" class="submit">支付</a>
						</div>
					</s:if>
					<s:elseif test="state==1">
					</s:elseif>
					<s:elseif test="state==2">
						<div class="bottom">
							<a href="${pageContext.request.contextPath }/order_firmOrder?oid=<s:property value="oid"/>"  id="submit" class="submit">确认订单</a>
						</div>
					</s:elseif>
					<s:elseif test="state==3">
					</s:elseif>
			</div><br/>
			</s:iterator>
		<div class="pagination">
			<!-- 分页条目栏 -->
			
			
			<s:if test="#orderPageBean.totalPage!=null&&#orderPageBean.totalPage!=0">
			
				<s:if test="#orderPageBean.currentPage==1">
					<span class="firstPage">&nbsp;</span>
					<span class="previousPage">&nbsp;</span>
				</s:if>
				<s:else>
					<a class="firstPage" href="${pageContext.request.contextPath}/order_orderListPage.action?page=1">&nbsp;</a>
					<a class="previousPage" href="${pageContext.request.contextPath}/order_orderListPage.action?page=<s:property value="#orderPageBean.currentPage-1"/>">&nbsp;</a>
				</s:else>
				
			</s:if>
			
			<s:if test="#orderPageBean.totalPage<=5">
				<s:iterator begin="1" end="#orderPageBean.totalPage" step="1" var="i">
					<s:if test="#i == #orderPageBean.currentPage">
						<span class="currentPage"><s:property value="#i"/></span>
					</s:if>
					<s:else>
						<a href="${pageContext.request.contextPath}/order_orderListPage.action?page=<s:property value="#i"/>">  <s:property value="#i"/></a>
					</s:else>
				 </s:iterator>
			</s:if>
			<s:else>
				<s:if test="#orderPageBean.currentPage<=3">
				<s:iterator begin="1" end="5" step="1" var="i">
					<s:if test="#i == #orderPageBean.currentPage">
						<span class="currentPage"><s:property value="#i"/></span>
					</s:if>
					<s:else>
						<a href="${pageContext.request.contextPath}/order_orderListPage.action?page=<s:property value="#i"/>">  <s:property value="#i"/></a>
					</s:else>
				 </s:iterator>			
				</s:if>		
				<s:elseif test="#orderPageBean.currentPage>3&&#orderPageBean.currentPage<#orderPageBean.totalPage-3+1">
				<s:iterator begin="#orderPageBean.currentPage-2" end="#orderPageBean.currentPage+2" step="1" var="i">
					<s:if test="#i == #orderPageBean.currentPage">
						<span class="currentPage"><s:property value="#i"/></span>
					</s:if>
					<s:else>
						<a href="${pageContext.request.contextPath}/order_orderListPage.action?page=<s:property value="#i"/>">  <s:property value="#i"/></a>
					</s:else>
				 </s:iterator>
				</s:elseif>
				<s:else>
					<s:iterator begin="#orderPageBean.totalPage-5+1" end="#orderPageBean.totalPage" step="1" var="i">
					<s:if test="#i == #orderPageBean.currentPage">
						<span class="currentPage"><s:property value="#i"/></span>
					</s:if>
					<s:else>
						<a href="${pageContext.request.contextPath}/order_orderListPage.action?page=<s:property value="#i"/>">  <s:property value="#i"/></a>
					</s:else>
				 </s:iterator>
				</s:else>
			</s:else>
			
			
			
			<s:if test="#orderPageBean.totalPage!=null&&#orderPageBean.totalPage!=0">
				<s:if test="#orderPageBean.currentPage==#orderPageBean.totalPage">
					<span class="nextPage">&nbsp;</span>
					<span class="lastPage">&nbsp;</span>
				</s:if>
				<s:else>
					<a class="nextPage" href="${pageContext.request.contextPath}/order_orderListPage.action?page=<s:property value="#orderPageBean.currentPage+1"/>">&nbsp;</a>
					<a class="lastPage" href="${pageContext.request.contextPath}/order_orderListPage.action?page=<s:property value="#orderPageBean.totalPage"/>">&nbsp;</a>
				</s:else>
			</s:if>
		</div>
	</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="${pageContext.request.contextPath}/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
</div>	</div>
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
						<a target="_blank">支付方式</a>
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
</body>
</html>
