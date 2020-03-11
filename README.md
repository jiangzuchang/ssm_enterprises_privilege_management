<h2>项目概况</h2>  <br/>
该项目属于SSM整合项目，在其中也运用了spring-security技术，运用了pageHelper插件进行分页展示。使用AdminLTE作为前端页面框架，考虑到是后台的系统，不过于注重用户体验，所有的页面都是jsp形式的。  

<h2>实现的功能</h2>  <br/>
<h3>产品操作</h3>  <br/>  
包含对所有产品的查询分页展示，添加产品的功能

<h3>订单操作</h3>  <br/>    
包含对所有订单的查询分页展示，包含对某个订单详情的查询功能

<h3>权限控制</h3>  <br/>    
使用spring-security框架对登录流程进行权限控制，对整个系统的访问进行权限控制，关闭了跨域请求，也支持服务器端的方法级权限控制

<h3>用户操作</h3>  <br/>    
包含查询所有用户，用户登录、退出功能，用户添加操作。密码加密方式（BCryptPasswordEncoder）。用户关联角色操作。

<h3>角色操作</h3>  <br/>    
包含查询展示所有角色的功能，角色添加的功能，角色关联权限的操作

<h3>权限关联与控制</h3>  <br/>    
包含查询展示所有权限的功能，添加资源权限的功能。

<h3>系统操作日志</h3>  <br/>    
运用代理、反射和注解等原理，使用spring的AOP功能，对所有访问Controller的操作进行访问日志记录。详细的记录方法，在代码注释中写得很清楚。


    作者：江祖昌
