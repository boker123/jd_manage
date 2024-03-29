<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>商品管理</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<style type="text/css">
	body{ font-family: "微软雅黑"; background-color: #EDEDED; }
	h2{ text-align: center; }
	table{ margin: 0 auto; text-align: center; border-collapse:collapse; font-size:16px }
	td, th{ padding: 7px;}
	th{ background-color: #DCDCDC; width:120px; }
	th.desc{ width: 500px; }
	hr{ margin-bottom:20px; border:1px solid #aaa; }
</style>

<!--引入jquery的js库-->
<script src="${app}/../js/jquery-1.4.2.js"></script>
<script type="text/javascript">
    function deleteProdById(id) {
        var res = window.confirm("您真的要删除该商品吗?");
        if(res) {
            window.location.href="${app}/ProdDelServlet?id="+id;

        }
    }
</script>
</head>
<body>
	<h2>商品管理</h2>
	<hr/>
	<table border="1">
		<tr>
			<th>商品ID</th>
			<th>商品名称</th>
			<th>商品种类</th>
			<th>商品单价</th>
			<th>库存数量</th>
			<th class="desc">描述信息</th>
			<th>操 作</th>
		</tr>

		<!-- 模版数据 -->
		<c:forEach items="${ list }" var="prod">
			<tr>
				<td>${ prod.id }</td>
				<td>${ prod.name }</td>
				<td>${ prod.category }</td>
				<td>${ prod.price }</td>
				<td>
					${ prod.pnum }
				</td>
				<td>${ prod.description }</td>
				<td>
					<a id="delProd" href="javascript:void(0)" onclick="deleteProdById(${prod.id})">删除</a> |
					<a id="updProd" href="${app}/ProdInfoServlet?id=${prod.id}">修改</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>