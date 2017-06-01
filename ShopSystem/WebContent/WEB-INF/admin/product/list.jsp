<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery-1.7.2.js"></script>
		<script type="text/javascript">
			function addProduct(){
				window.location.href = "${pageContext.request.contextPath}/seller/sellerProduct_addPage.action";
			}
			
			<s:if test="[1].actionErrors[0]!=null">
			$(document).ready(function(){ 
					alert("<s:property value="[1].actionErrors[0]"/>")
				}); 
			</s:if>
			
		</script>
	</HEAD>
	<body>
		<br>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>商品列表</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="10%">
										序号
									</td>
									<td width="11%" align="center">
										商品编号
									</td>
									<td align="center" width="17%">
										商品图片
									</td>
									<td align="center" width="17%">
										商品名称
									</td>
									<td align="center" width="8%">
										商城价格
									</td>
									<td align="center" width="15%">
										市场价格
									</td>
									<td align="center" width="11%">
										库存
									</td>
									<td align="center" width="5%">
										是否热门商品
									</td>
									<td width="7%" align="center">
										编辑
									</td>
								</tr>
									<s:iterator  value="#productPageBean.list" status="status">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="10%">
												<s:property value="#status.count"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												<s:property value="pid"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												<img width="45" height="50" src="${pageContext.request.contextPath }/loadProductImage.action?pid=<s:property value="pid"/>"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="17%">
												<s:property value="pname"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="8%">
												<s:property value="shop_price"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="15%">
												<s:property value="market_price"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="11%">
												<s:property value="pnum"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												<s:if test="is_hot=='1'">是</s:if>
												<s:else>否</s:else>
											</td>
											<td align="center" style="HEIGHT: 22px">
												<a href="${pageContext.request.contextPath}/admin/adminProduct_changeIsHot.action?pid=<s:property value="pid"/>">
													<s:if test="is_hot=='1'">取消热门</s:if>
													<s:else>设置热门</s:else>
												</a>
											</td>
										</tr>
									</s:iterator>	
						 		<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="18%" colspan="9">
									<s:if test="#productPageBean.totalPage!=0">
										第 <s:property value="#productPageBean.currentPage"/>/<s:property value="#productPageBean.totalPage"/>页
										<s:if test="#productPageBean.currentPage != 1">
											<a href="${ pageContext.request.contextPath }/admin/adminProduct_listProductPage.action?page=1">首页</a> |
											<a href="${ pageContext.request.contextPath }/admin/adminProduct_listProductPage.action?page=<s:property value="#productPageBean.currentPage-1"/>">上一页</a> |
										</s:if>
										<s:if test="#productPageBean.currentPage != #productPageBean.totalPage">
											<a href="${ pageContext.request.contextPath }/admin/adminProduct_listProductPage.action?page=<s:property value="#productPageBean.currentPage+1"/>">下一页</a> |
											<a href="${ pageContext.request.contextPath }/admin/adminProduct_listProductPage.action?page=<s:property value="#productPageBean.totalPage"/>">尾页</a>
										</s:if>
									</td>
									</s:if>
								</tr>
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>

