 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>购物车</title>
<meta name="author" content="Mango Team">
<meta name="copyright" content="Mango">
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
			<s:iterator value="#cartBean.cart" >
				<s:property value="value[0].product.seller.sname"/>:
				<table>
					<tbody>
					<tr>
						<th>图片</th>
						<th>商品</th>
						<th>库存</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
						<th>操作</th>
					</tr>
					<s:iterator value="value" >
						<tr>
							<td width="60">
								<input type="hidden" name="id" value="22">
								<a href="${pageContext.request.contextPath }/product_detail?pid=<s:property value="product.pid"/>"><img src="${pageContext.request.contextPath }/loadProductImage.action?pid=<s:property value="product.pid"/>"/></a>
							</td>
							<td width="200">
								<a href="${pageContext.request.contextPath }/product_detail?pid=<s:property value="product.pid"/>"><s:property value="product.pname"/></a>
							</td>
							<td>
								<s:property value="product.pnum"/>
							</td>
							<td>
								￥<s:property value="product.shop_price"/>
							</td>
							<td class="quantity" width="60">
								<input type="text" name="quantity" value="<s:property value="quantity" />"  maxlength="4" onpaste="return false;"  readonly="readonly">
								<div>
									<a href="${pageContext.request.contextPath }/cart_increse?ciid=<s:property value="ciid"/>"><span class="increase">&nbsp;</span></a>
									<a href="${pageContext.request.contextPath }/cart_decrease?ciid=<s:property value="ciid"/>"><span class="decrease">&nbsp;</span></a>
								</div>
							</td>
							<td width="140">
								<span class="subtotal">￥<s:property value="product.shop_price*quantity"/></span>
							</td>
							<td>
								<a href="${pageContext.request.contextPath }/cart_delete?ciid=<s:property value="ciid"/>" class="delete">删除</a>
							</td>
						</tr>
						</s:iterator>
				</tbody></table>
			</s:iterator>
				<dl id="giftItems" class="hidden" style="display: none;">
				</dl>
				<div class="total">
					商品金额: <strong id="effectivePrice">￥<s:property value="#cartBean.totalMoney"/></>元</strong>
				</div>
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/cart_deleteAll" id="clear" class="clear">清空购物车</a>
					<a href="./会员登录.htm" id="submit" class="submit">提交订单</a>
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
