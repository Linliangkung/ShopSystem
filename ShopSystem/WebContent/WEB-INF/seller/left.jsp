<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>菜单</title>
<link href="${pageContext.request.contextPath}/css/left.css" rel="stylesheet" type="text/css">
</head>
<body>
<table width="100" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="12"></td>
  </tr>
</table>
<table width="100%" border="0">
  <tr>
    <td>
<div class="dtree">
	<a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a>
	<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree/dtree.js"></script>
	<script type="text/javascript">
		/*d = new dTree('d');
		d.add('01',-1,'系统菜单树');
		d.add('0101','01','用户管理','${pageContext.request.contextPath}/seller/sellerHome_welcomePage.action','','mainFrame');
		d.add('010101','0101','用户管理','${pageContext.request.contextPath}/user_adminFindAll.action?page=1','','mainFrame');
		d.add('0102','01','商品管理','${pageContext.request.contextPath}/seller/sellerHome_welcomePage.action','','mainFrame');
		d.add('010201','0102','商品管理','${pageContext.request.contextPath}/product_adminFindAll.action?page=1','','mainFrame');
		d.add('0103','01','订单管理','${pageContext.request.contextPath}/seller/sellerHome_welcomePage.action','','mainFrame');
		d.add('010301','0103','订单管理','${pageContext.request.contextPath}/order_adminFindAll.action?page=1','','mainFrame');
		d.add('0104','01','一级分类管理','${pageContext.request.contextPath}/seller/sellerHome_welcomePage.action','','mainFrame');
		d.add('010401','0104','一级分类管理','${pageContext.request.contextPath}/category_adminFindAll.action','','mainFrame');
		d.add('0105','01','二级分类管理','${pageContext.request.contextPath}/seller/sellerHome_welcomePage.action','','mainFrame');
		d.add('010501','0105','二级分类管理','${pageContext.request.contextPath}/categorySecond_adminFindAll.action?page=1','','mainFrame');
		document.write(d);*/
		d=new dTree('d');
		d.add('01',-1,'系统菜单树');
		d.add('0101','01','商品管理','${pageContext.request.contextPath}/seller/sellerHome_welcomePage.action','','mainFrame');
		d.add('010101','0101','添加商品','${pageContext.request.contextPath}/seller/sellerProduct_addPage.action','','mainFrame');
		d.add('010102','0101','商品列表','${pageContext.request.contextPath}/seller/sellerProduct_listProductPage.action?page=1','','mainFrame');
		d.add('0102','01','订单管理','${pageContext.request.contextPath}/seller/sellerHome_welcomePage.action','','mainFrame');
		d.add('010201','0102','待支付订单','${pageContext.request.contextPath}/seller/sellerOrder_listOrderPage.action?state=0&page=1','','mainFrame');
		d.add('010202','0102','已支付订单','${pageContext.request.contextPath}/seller/sellerOrder_listOrderPage.action?state=1&page=1','','mainFrame');
		d.add('010203','0102','待收货订单','${pageContext.request.contextPath}/seller/sellerOrder_listOrderPage.action?state=2&page=1','','mainFrame');
		d.add('010204','0102','已确认订单','${pageContext.request.contextPath}/seller/sellerOrder_listOrderPage.action?state=3&page=1','','mainFrame');
		document.write(d);
	</script>
</div>	</td>
  </tr>
</table>
</body>
</html>
