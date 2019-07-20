这里你可以说一下代码改动和更新;

yy: 用逆向工程生成了dao层的相关代码（mapper、mapper.xml、entity）  
基本方法说明(详细自己去百度)：  
int deleteByPrimaryKey 按主键删除 
int insert(User record) 	插入数据（返回值为ID）  
User selectByPrimaryKey(Integer id) thorws SQLException	按主键查询  
int updateByPrimaryKey(User record) 	按主键更新  
int updateByPrimaryKeySelective(User record) 	按主键更新值不为null的字段
