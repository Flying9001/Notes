# SQL查询笔记      

### 1 查询  

#### 1.1 与 Mybatis 搭配      

SQL 语句 & Mybatis mapper xml

```sql
    <!-- queryMemberByUserMap -->
    <resultMap id="queryMemberByUserMap" type="com.nanochap.mall.entity.FamilyMember">
        <id column="id" property="id" jdbcType="BIGINT"/>
        <result column="userId" property="userId" jdbcType="BIGINT" />
        <result column="memberId" property="memberId" jdbcType="BIGINT" />
        <result column="markName" property="markName" jdbcType="VARCHAR" />
        <result column="createTime" property="createTime" jdbcType="VARCHAR" />
        <result column="updateTime" property="updateTime" jdbcType="VARCHAR" />
        <collection property="member" javaType="java.util.List" ofType="com.nanochap.mall.entity.User">
            <id column="u_id" property="id" jdbcType="BIGINT" />
            <result column="u_account" property="account" jdbcType="VARCHAR"/>
            <result column="u_headPortrait" property="headPortrait" jdbcType="VARCHAR"/>
        </collection>
    </resultMap>
    <!-- 查询某用户的所有家庭成员 -->
    <select id="queryMembersByUser" resultMap="queryMemberByUserMap">
        SELECT f.id,f.userId,f.memberId,f.markName,f.createTime,f.updateTime,
            u.id u_id,u.account u_account,u.headPortrait u_headPortrait
        FROM t_family_member f
        inner join t_user u
        on f.memberId = u.id
        WHERE f.userId = #{userId}
    </select>
	
```

#### 1.2 多表联合查询分页  

```sql
    <!-- 获取商品列表 -->
    <select id="goodsList" resultMap="goodsListMap">
        select g.id,g.name,g.title,g.price,g.description,g.stockCount,g.status,g.createTime,g.updateTime,
            i.imgUrl i_imgUrl,i.createTime i_createTime,i.updateTime i_updateTime,
            c.name c_name,c.value c_value
        FROM (
            select id, name, title, price, description, stockCount, status, createTime, updateTime
            from t_goods
            LIMIT ${(pagination.curPage-1) * pagination.limitRows}, #{pagination.limitRows}
        ) g
        LEFT JOIN t_goods_img i
        ON g.id = i.goodsId
        LEFT JOIN t_goods_color gc
        ON g.id = gc.goodsId
        LEFT JOIN t_color c
        ON gc.colorId = c.id
        WHERE g.status = 1
        ORDER BY g.id asc
    </select>

```


#### 1.3 关联查询

[MySQL SELECT COUNT 一对多关联查询去重](https://blog.csdn.net/Mrqiang9001/article/details/100180644 "https://blog.csdn.net/Mrqiang9001/article/details/100180644")  

​    

#### 1.4 条件查询  

[MySQL范围查询 IN 使用注意事项](https://blog.csdn.net/Mrqiang9001/article/details/100074408 "https://blog.csdn.net/Mrqiang9001/article/details/100074408")  



### 2 数据库设计方案  

#### 2.1 具有用户已读状态的公告

[具有用户已读状态功能的公告(1):数据库设计](https://blog.csdn.net/Mrqiang9001/article/details/98335457 "https://blog.csdn.net/Mrqiang9001/article/details/98335457")  

[具有用户已读状态功能的公告(2):用户查询公告列表，同时包含读取状态](https://blog.csdn.net/Mrqiang9001/article/details/98340623 "https://blog.csdn.net/Mrqiang9001/article/details/98340623")  

[具有用户已读状态功能的公告(3):一键已读所有公告](https://blog.csdn.net/Mrqiang9001/article/details/98347922 "https://blog.csdn.net/Mrqiang9001/article/details/98347922")  





