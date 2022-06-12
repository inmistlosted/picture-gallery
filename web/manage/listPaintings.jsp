<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/manage/commons/header.jsp"%>
<div class="main-content" style="width: 100%">
    <div class="main-content-inner" >
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="${pageContext.request.contextPath}/manage/">Головна</a>
                </li>

                <li>
                    <a href="#">Картина</a>
                </li>
                <li class="active">Список Картин</li>
            </ul>

            <div class="nav-search" id="nav-search">
                <form class="form-search">
                    <span class="input-icon">
                        <input type="text" placeholder="Пошук ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
                        <i class="ace-icon fa fa-search nav-search-icon"></i>
                    </span>
                </form>
            </div>
        </div>

        <div class="page-content">
            <div class="page-header">
                <h1>
                    Список Картин
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        Всі Картини
                    </small>
                </h1>
            </div>
            <div class="row">
                <div class="col-xs-12">
    <c:if test="${empty page.records}">
    	<h2><fmt:message key="dashboard.nobook" />，<a href="${pageContext.request.contextPath}/servlet/ManageServlet?op=addPaintingUI">Додати Картину</a></h2>
    </c:if>
    <c:if test="${!empty page.records}">
    	<table class="table  table-bordered table-hover">
    		<tr>
    			<Th> Вигляд </th>
                <Th> Назва </th>
                <Th> Художник </th>
                <Th> Ціна </th>
                <Th> Напрям </th>
                <Th> Опис </th>
                <Th> Категорія </th>
                <Th> Управління </th>
    		</tr>
    		<c:forEach items="${page.records}" var="b" varStatus="vs">
    			<tr class="${vs.index%2==0?'even':'odd'}">
	    			<td>
                        <img style="width: 64px;height: 64px" src="${pageContext.request.contextPath}/res/shop_assets/images/painting/${b.filename}"/>
	    			</td>
	    			<td>${b.name}</td>
	    			<td>${b.artist.name}</td>
	    			<td>$${b.price}</td>
                    <td>${b.way.name}</td>
	    			<td>${b.des}</td>
	    			<td>${b.category.name}</td>
	    			<td >
	    				[<a href="${pageContext.request.contextPath}/servlet/ManageServlet?op=editPaintingUI&paintingId=${b.id}" >Редагувати</a>]
	    				[<a href="${pageContext.request.contextPath}/servlet/ManageServlet?op=delPainting&id=${b.id}" >Видалити</a>]
	    			</td>
	    		</tr>
    		</c:forEach>
    	</table>
    	<%@ include file="/manage/commons/page.jsp"%>
    </c:if></div>
            </div>
        </div>
    </div>
</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/manage_assets/js/jquery-1.11.3.min.js"  ></script>
<script type="text/javascript">
    function del(id){
        $.post("${pageContext.request.contextPath}/servlet/ManageServlet",{op:'delPainting',id:id},function(data){
        });
    }
</script>
<%@ include file="/manage/commons/footer.jsp"%>
