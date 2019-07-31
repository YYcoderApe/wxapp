更改记录：
yy:创建好了分包
    config:配置类相关
    controller_cancer:控制程   service_cancer:服务层 
    有yycoder的都是我的
    dao:含有mapper 和 mapper.xml文件
    entity:实体类   util：工具类  web:程序入口
    只需要开启web模块下的webapplication即可，即可访问web,公共配置
    也直接在web模块下application.property配置就行了
    
yy:增加一个Ajax包装类在util中，只要在controller引入依赖就能使用了


   增加了一个上传图片的工具类，开启访问http://localhost:8080/swagger-ui.html#/即可，进入接口
    选择文件上传，返回结果中包含图片的地址：return_url进行访问就可以，将链接存放在数据库中即
    可，本地在e盘下会创建一个temp文件夹，里面放着你上传的图片。
    
cxy:使用jedis整合redis，创建redisUtil即可使用。
cxy:修改依赖顺序，dao引入util，config引入controller_cancer,web引入config
    