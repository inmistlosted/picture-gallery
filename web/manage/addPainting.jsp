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
                    <a href="#">Картина</a>
                </li>
                <li class="active">Додати нову картину</li>
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
                    Додати нову картину
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        Підтвердити нову картину
                    </small>
                </h1>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <form class="form-horizontal" enctype="multipart/form-data" action="${pageContext.request.contextPath}/servlet/ManageServlet?op=addPainting" method="post">
                        <div class="form-group">
                            <%--@declare id="form-field-1"--%><label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Назва </label>

                            <div class="col-sm-9">
                                <input type="text" id="name" placeholder="назва" class="col-xs-10 col-sm-5" name="name"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> Художник </label>

                            <div class="col-sm-9">
                                <select class="col-xs-10 col-sm-5" name="artistId">
                                    <c:forEach items="${as}" var="a">
                                        <option value="${a.id}">${a.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <%--@declare id="form-field-1-1"--%><label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> Ціна </label>

                            <div class="col-sm-9">
                                <input type="text" name="price" class="col-xs-10 col-sm-5" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> Напрям </label>

                            <div class="col-sm-9">
                                <select class="col-xs-10 col-sm-5" name="wayId">
                                    <c:forEach items="${ws}" var="w">
                                        <option value="${w.id}">${w.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> Вигляд </label>

                            <div class="col-sm-9">
                                <input type="file" name="image"  class="col-xs-10 col-sm-5" />
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> Опис </label>

                            <div class="col-sm-9">
                                <textarea type="text" name="des" class="col-xs-10 col-sm-5" rows="5"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1-1"> Категорія </label>

                            <div class="col-sm-9">
                                <select class="col-xs-10 col-sm-5" name="categoryId">
    					            <c:forEach items="${cs}" var="c">
    						            <option value="${c.id}">${c.name}</option>
    					            </c:forEach>
    				            </select>
                            </div>
                        </div>
                        


                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <button class="btn btn-info" type="submit">
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    Додати
                                </button>

                                &nbsp; &nbsp; &nbsp;
                                <button class="btn" type="reset">
                                    <i class="ace-icon fa fa-undo bigger-110"></i>
                                    Очистити
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<%@ include file="/manage/commons/footer.jsp"%>