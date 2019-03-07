<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-2">
    <ul class="nav nav-pills nav-stacked" id="nav">
        <li><a href="${pageContext.request.contextPath}/exchanger/showCourse">所有课程<span class="badge pull-right">26</span></a></li>
        <li><a href="${pageContext.request.contextPath}/exchanger/selectedCourse">已选课程<span class="badge pull-right">5</span></a></li>
        <li><a href="${pageContext.request.contextPath}/exchanger/overCourse">已修课程<span class="badge pull-right">8</span></a></li>
        <li><a href="${pageContext.request.contextPath}/exchanger/passwordRest">修改密码<span class="glyphicon glyphicon-pencil pull-right"></span></a></li>
        <li><a href="${pageContext.request.contextPath}/logout">退出系统<span class="glyphicon glyphicon-log-out pull-right"></span></a></li>

    </ul>
</div>