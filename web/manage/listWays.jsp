<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/manage/commons/header.jsp"%>
<div class="main-content" style="width: 100%">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="${pageContext.request.contextPath}/manage/">Головна</a>
                </li>

                <li>
                    <a href="#">Напрям</a>
                </li>
                <li class="active">Список напрямів</li>
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
                    Список напрямів
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        Всі напрями
                    </small>
                </h1>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${empty ws}">
                        <h2><fmt:message key="dashboard.nocategory" />，<a href="${pageContext.request.contextPath}/manage/addWay.jsp">Додати напрям</a></h2>
                    </c:if>
                    <c:if test="${!empty ws}">
                        <table class="table  table-bordered table-hover">
                            <tr>
                                <Th> Обрати </th>
                                     <Th> Номер </th>
                                     <Th> Назва </th>
                                     <Th> Управління </th>
                            </tr>
                            <c:forEach items="${ws}" var="w" varStatus="ws">
                                <tr class="${ws.index%2==0?'even':'odd'}">
                                    <td>
                                        <input type="checkbox" name="ids" value="${w.id}"/>
                                    </td>
                                    <td>${ws.count}</td>
                                    <td>${w.name}</td>
                                    <td>
                                        [<a href="${pageContext.request.contextPath}/servlet/ManageServlet?op=editWayUI&wayId=${w.id}">Редагувати</a>]
                                        [<a href="" onclick="del(this)">Видалити</a>]
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/manage_assets/js/jquery-1.11.3.js"  ></script>
<script type="text/javascript">
    function del(obj){
        var Vname=$(obj).parent().prev().text();
        console.log(Vname);
        $.post("${pageContext.request.contextPath}/servlet/ManageServlet",{op:'delWay',name:Vname},function(data){
        });
    }
</script>
<%@ include file="/manage/commons/footer.jsp"%>
