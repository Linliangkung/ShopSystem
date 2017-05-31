<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		
	</HEAD>
	<body>
		<br>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>订单列表</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<s:iterator  value="#orderPageBean.list">
								
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="left" width="10%" colspan="6">
										订单号:<s:property value="oid"/> &nbsp;&nbsp;&nbsp;&nbsp;
										订单金额:<s:property value="total"/> &nbsp;&nbsp;&nbsp;&nbsp;
										下单时间:<s:date name="ordertime" format="yyyy-MM-dd HH:mm:ss"/> &nbsp;&nbsp;&nbsp;&nbsp;
										订单状态:
										<s:if test="state==0">
											待支付
										</s:if>
										<s:elseif test="state==1">
											<a href="${ pageContext.request.contextPath }/seller/sellerOrder_delivery?oid=<s:property value="oid"/>"><font color="red">发货</font></a>
										</s:elseif>
										<s:elseif test="state==2">
											待收货
										</s:elseif>
										<s:elseif test="state==3">
											已确认
										</s:elseif>
										
									</td>
								</tr>
								<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												序号
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												图片
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												名称
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="8%">
												数量
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												单价
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="23%">
												小计 
											</td>
										</tr>
									<s:iterator  value="orderItems" status="status">
										
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												<s:property value="#status.count"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												<img width="45" height="50" src="${pageContext.request.contextPath }/loadProductImage.action?pid=<s:property value="product.pid"/>"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												<s:property value="product.pname"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="8%">
												<s:property value="quantity"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												<s:property value="product.shop_price"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="23%">
												<s:property value="subtotal"/>
											</td>
											
											
										</tr>
										</s:iterator>
									</s:iterator>	
									<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="18%" colspan="8">
										<s:if test="#orderPageBean.totalPage!=0">
										第 <s:property value="#orderPageBean.currentPage"/>/<s:property value="#orderPageBean.totalPage"/>页
										<s:if test="#orderPageBean.currentPage != 1">
											<a href="${ pageContext.request.contextPath }/seller/sellerOrder_listOrderPage.action?page=1&state=<s:property value="state"/>">首页</a> |
											<a href="${ pageContext.request.contextPath }/seller/sellerOrder_listOrderPage.action?page=<s:property value="#orderPageBean.currentPage-1"/>&state=<s:property value="state"/>">上一页</a> |
										</s:if>
										<s:if test="#orderPageBean.currentPage != #orderPageBean.totalPage">
											<a href="${ pageContext.request.contextPath }/seller/sellerOrder_listOrderPage.action?page=<s:property value="#orderPageBean.currentPage+1"/>&state=<s:property value="state"/>">下一页</a> |
											<a href="${ pageContext.request.contextPath }/seller/sellerOrder_listOrderPage.action?page=<s:property value="#orderPageBean.totalPage"/>&state=<s:property value="state"/>">尾页</a>
										</s:if>
										</td>
										</s:if>
									</td>
									
								</tr>
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>

