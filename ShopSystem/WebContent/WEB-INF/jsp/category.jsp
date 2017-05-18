 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>一级分类</title>
<link href="./css/common.css" rel="stylesheet" type="text/css">
<link href="./css/product.css" rel="stylesheet" type="text/css">
<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="http://localhost:8080/mango/">
				<img src="./image/r___________renleipic_01/logo.gif" alt="zhku">
			</a>
		</div>
	</div>
	<div class="span9">
		<div class="headerAd">
			<img src="./image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障">
		</div>	
	</div>
	<%@ include file="header.jsp" %>
	<%@ include file="navigationbar.jsp" %>
</div>	


<div class="container productList">
		<div class="span6">
			<div class="hotProductCategory">
					<s:iterator var="c" value="#categories">
						<dl>
							<dt>
								<a href="${pageContext.request.contextPath }/category.action?cid=<s:property value="#c.cid"/>"><s:property value="#c.cname"/></a>
							</dt>
						<s:iterator value="#c.categoryseconds">
							<dd>
								<a ><s:property value="csname"/></a>
							</dd>
						</s:iterator>
						</dl>
					</s:iterator>
			</div>
		</div>
		<div class="span18 last">
			
			<form id="productForm" action="./image/蔬菜 - Powered By Mango Team.htm" method="get">
				<input type="hidden" id="brandId" name="brandId" value="">
				<input type="hidden" id="promotionId" name="promotionId" value="">
				<input type="hidden" id="orderType" name="orderType" value="">
				<input type="hidden" id="pageNumber" name="pageNumber" value="1">
				<input type="hidden" id="pageSize" name="pageSize" value="20">
					
				<div id="result" class="result table clearfix">
						<ul>
								<s:iterator value="#productPageBean.list">
								<li>
									<a>
										<img src="${pageContext.request.contextPath}/loadProductImage.action?pid=<s:property  value="pid"/>" width="170" height="170"  style="display: inline-block;"/>
											   
										<span style='color:green'>
											<s:property  value="pname"/>
										</span>
										<span class="price">
												商城价： ￥<s:property  value="shop_price"/>
										</span>
									</a>
								</li>
								</s:iterator>
						</ul>
				</div>
	<div class="pagination">
	<!--
			<span class="firstPage">&nbsp;</span>
			<span class="previousPage">&nbsp;</span>
				<span class="currentPage">1</span>
				<a>2</a>
			<a class="nextPage" href="javascript: $.pageSkip(2);">&nbsp;</a>
			
			<a class="lastPage" href="javascript: $.pageSkip(2);">&nbsp;</a>
			-->
			<s:if test="#productPageBean.totalPage!=null&&#productPageBean.totalPage!=0">
			
				<s:if test="#productPageBean.currentPage==1">
					<span class="firstPage">&nbsp;</span>
					<span class="previousPage">&nbsp;</span>
				</s:if>
				<s:else>
					<a class="firstPage" href="${pageContext.request.contextPath}/category.action?cid=<s:property value="cid"/>&page=1">&nbsp;</a>
					<a class="previousPage" href="${pageContext.request.contextPath}/category.action?cid=<s:property value="cid"/>&page=<s:property value="#productPageBean.currentPage-1"/>">&nbsp;</a>
				</s:else>
				
			</s:if>
			
			<s:if test="#productPageBean.totalPage<=5">
				<s:iterator begin="1" end="#productPageBean.totalPage" step="1" var="i">
					<s:if test="#i == #productPageBean.currentPage">
						<span class="currentPage"><s:property value="#i"/></span>
					</s:if>
					<s:else>
						<a href="${pageContext.request.contextPath}/category.action?cid=<s:property value="cid"/>&page=<s:property value="#i"/>">  <s:property value="#i"/></a>
					</s:else>
				 </s:iterator>
			</s:if>
			<s:else>
				<s:if test="#productPageBean.currentPage<=3">
				<s:iterator begin="1" end="5" step="1" var="i">
					<s:if test="#i == #productPageBean.currentPage">
						<span class="currentPage"><s:property value="#i"/></span>
					</s:if>
					<s:else>
						<a href="${pageContext.request.contextPath}/category.action?cid=<s:property value="cid"/>&page=<s:property value="#i"/>">  <s:property value="#i"/></a>
					</s:else>
				 </s:iterator>			
				</s:if>		
				<s:elseif test="#productPageBean.currentPage>3&&#productPageBean.currentPage<#productPageBean.totalPage-3+1">
				<s:iterator begin="#productPageBean.currentPage-2" end="#productPageBean.currentPage+2" step="1" var="i">
					<s:if test="#i == #productPageBean.currentPage">
						<span class="currentPage"><s:property value="#i"/></span>
					</s:if>
					<s:else>
						<a href="${pageContext.request.contextPath}/category.action?cid=<s:property value="cid"/>&page=<s:property value="#i"/>">  <s:property value="#i"/></a>
					</s:else>
				 </s:iterator>
				</s:elseif>
				<s:else>
					<s:iterator begin="#productPageBean.totalPage-5+1" end="#productPageBean.totalPage" step="1" var="i">
					<s:if test="#i == #productPageBean.currentPage">
						<span class="currentPage"><s:property value="#i"/></span>
					</s:if>
					<s:else>
						<a href="${pageContext.request.contextPath}/category.action?cid=<s:property value="cid"/>&page=<s:property value="#i"/>">  <s:property value="#i"/></a>
					</s:else>
				 </s:iterator>
				</s:else>
			</s:else>
			
			
			
			<s:if test="#productPageBean.totalPage!=null&&#productPageBean.totalPage!=0">
			
				<s:if test="#productPageBean.currentPage==#productPageBean.totalPage">
					<span class="nextPage">&nbsp;</span>
					<span class="lastPage">&nbsp;</span>
				</s:if>
				<s:else>
					<a class="nextPage" href="${pageContext.request.contextPath}/category.action?cid=<s:property value="cid"/>&page=<s:property value="#productPageBean.currentPage+1"/>">&nbsp;</a>
					<a class="lastPage" href="${pageContext.request.contextPath}/category.action?cid=<s:property value="cid"/>&page=<s:property value="#productPageBean.totalPage"/>">&nbsp;</a>
				</s:else>
				
			</s:if>
			
	</div>
			</form>
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="./image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
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
						<a >诚聘英才</a>
						|
					</li>
					<li>
						<a >法律声明</a>
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
						<a >SHOP++官网</a>
						|
					</li>
					<li>
						<a >SHOP++论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2013 Mango商城 版权所有</div>
	</div>
</div>
<s:debug></s:debug>
</body></html>