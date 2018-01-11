<#assign projectName="http://localhost:8080"/>
<#assign imageService="http://localhost:8010"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script type="text/javascript" src="/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/css/bootstrap.min.css" />
    <title>home</title>
</head>
<body>
    <h2 style="background-color: #3c763d;text-align: center">欢迎
        <#if user??>${user.userName}</#if>登陆</h2>

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="${projectName}/user/index.do">登录 <span class="sr-only">(current)</span></a></li>
                    <li><a href="${projectName}/user/registerHtml.do">注册</a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>


    <h2>

        <table style="width: 60%;font-size: large">
            <tr class="tr1">
                <th>选择</th>
                <th>用户ID</th>
                <th>用户名</th>
                <th>性别</th>
                <th>工资</th>
                <th>朋友</th>
                <th>操作</th>
            </tr>
            <#list users as userTempl>
                <tr class="tr1">
                    <td>
                        <input type="checkbox" name="operation" id="operation_${userTempl_index}">
                    </td>
                    <td>
                        <img src="${imageService}${userTempl.userPhoto}" style="width: 50px;height: 50px"> ${userTempl.userId}
                    </td>
                    <td>
                        ${userTempl.userName}
                    </td>
                    <td>
                        ${userTempl.userSex}
                    </td>
                    <td>
                        ${userTempl.userSalary}
                    </td>
                    <td>
                        <#if userTempl.userFriend??>
                            <#if userTempl.userFriend>
                                有
                            <#else>
                                无
                            </#if>
                        </#if>
                    </td>
                    <td>

                        <div class="dropup">
                            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                选择操作
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
                                <li><a href="${projectName}/rest/user/deleteUserById/${userTempl.userId}">删除</a></li>
                                <li><a href="${projectName}/user/rest/updateUser.do?userId=${userTempl.userId}">修改</a></li>
                            </ul>
                        </div>


                    </td>
                </tr>
            </#list>
            <tr class="tr1">
                <td>
                    <nav aria-label="...">
                        <ul class="pagination pagination-sm">
                            <li>
                                <a href="${projectName}/rest/user/home/${pageCurrent - 1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li><a href="${projectName}/rest/user/home/1">1</a></li>
                            <li><a href="${projectName}/rest/user/home/2">2</a></li>
                            <li><a href="${projectName}/rest/user/home/3">3</a></li>
                            <li><a href="${projectName}/rest/user/home/4">4</a></li>
                            <li><a href="${projectName}/rest/user/home/5">5</a></li>
                            <li>
                                <a href="${projectName}/rest/user/home/${pageCurrent + 1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </td>
            </tr class="tr1">
        </table>
    </h2>




</body>
</html>
