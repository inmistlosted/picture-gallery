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
                    <a href="#">Художник</a>
                </li>
                <li class="active">Список художників</li>
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
                    Список художників
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        Всі художники
                    </small>
                </h1>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${empty as}">
                        <h2><fmt:message key="dashboard.nocategory" />，<a href="${pageContext.request.contextPath}/manage/addArtist.jsp">Додати художника</a></h2>
                    </c:if>
                    <c:if test="${!empty as}">
                        <table class="table  table-bordered table-hover">
                            <tr>
                                <Th> Обрати </th>
                                     <Th> Номер </th>
                                     <Th> Ім'я </th>
                                     <Th> Країна </th>
                                     <Th> Управління </th>
                            </tr>
                            <c:forEach items="${as}" var="a" varStatus="as">
                                <tr class="${as.index%2==0?'even':'odd'}">
                                    <td>
                                        <input type="checkbox" name="ids" value="${a.id}"/>
                                    </td>
                                    <td>${as.count}</td>
                                    <td>${a.name}</td>
                                    <td>${a.country}</td>
                                    <td>
                                        [<a href="${pageContext.request.contextPath}/servlet/ManageServlet?op=editArtistUI&artistId=${a.id}">Редагувати</a>]
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
        var Vname=$(obj).parent().prev().prev().text();
        $.post("${pageContext.request.contextPath}/servlet/ManageServlet",{op:'delArtist',name:Vname},function(data){


        });
    }
</script>
<%@ include file="/manage/commons/footer.jsp"%>
