# SQL语句    

### 1 多表联合查询    

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

### 2. 多表联合查询分页  

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









