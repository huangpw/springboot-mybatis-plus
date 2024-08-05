# 🔥实战001-SpringBoot+Vue前后端分离实现数据增删改查教学（mybatis-plus版）

## 1. 资源下载

https://www.bilibili.com/video/BV17s4y1d7F1/?spm_id_from=333.788&vd_source=536bbd11950db7cbf64ebd982db0e30f

教程地址：https://www.bilibili.com/video/BV17s4y1d7F1/

源码下载：

​		链接：https://pan.baidu.com/s/1TFLCKuFw42R_HqTBKENPxQ?pwd=85ym 
​		提取码：85ym



## 2. 开发文档

### 2.1 SpringBoot+Vue增删改查演示

mybatis-plus官方网址：https://baomidou.com/

iviewUI网址：https://iview.github.io/

### 2.2 SpringBoot项目创建并配置mysql数据库

#### 2.2.1 mysql配置pom.xml

```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
```

#### 2.2.2 导入数据库

```sql
/*
 Navicat Premium Data Transfer

 Source Server         : root@localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : crud

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 17/04/2023 08:04:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '用户名称',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '用户密码',
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `city` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '所在城市',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', '123456', '123456@qq.com', '浙江');
INSERT INTO `user` VALUES (2, '李四', '123456', '111111@qq.com', '浙江');
INSERT INTO `user` VALUES (3, '张三', '123456', '123456@qq.com', '浙江');
INSERT INTO `user` VALUES (5, '张三2', '123456', '123456@qq.com', '浙江');
INSERT INTO `user` VALUES (7, '1', '1', '1@qq.com', '1');
INSERT INTO `user` VALUES (9, '1', '1', '11@qq.com', '1');
INSERT INTO `user` VALUES (10, '张三', '123456', '123456@qq.com', '浙江');
INSERT INTO `user` VALUES (11, '张三', '123456', '123456@qq.com', '浙江');
INSERT INTO `user` VALUES (12, 'aaa', 'aaa', 'aaa@qq.com', 'aaa');
INSERT INTO `user` VALUES (13, '张三', '123456', '123456@qq.com', '浙江123');
INSERT INTO `user` VALUES (14, '1', '1', '234@qq.com', '1');
INSERT INTO `user` VALUES (15, '测试用户', '4234234', NULL, NULL);
INSERT INTO `user` VALUES (16, '16', '16', '16', '16');

SET FOREIGN_KEY_CHECKS = 1;
```

#### 2.2.3 SpringBoot项目配置文件

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/crud?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai
    username: root
    password: root
```

#### 2.2.4 完整pom.xml文件

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <excludes>
                    <exclude>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok</artifactId>
                    </exclude>
                </excludes>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### 2.3 SpringBooot集成mybatis-plus

[MyBatis-Plus (opens new window)](https://github.com/baomidou/mybatis-plus)（简称 MP）是一个 [MyBatis (opens new window)](https://www.mybatis.org/mybatis-3/)的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。

#### 2.3.1 特性

- **无侵入**：只做增强不做改变，引入它不会对现有工程产生影响，如丝般顺滑

- **损耗小**：启动即会自动注入基本 CURD，性能基本无损耗，直接面向对象操作

- **强大的 CRUD 操作**：内置通用 Mapper、通用 Service，仅仅通过少量配置即可实现单表大部分 CRUD 操作，更有强大的条件构造器，满足各类使用需求

- **支持 Lambda 形式调用**：通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错

- **支持主键自动生成**：支持多达 4 种主键策略（内含分布式唯一 ID 生成器 - Sequence），可自由配置，完美解决主键问题

- **支持 ActiveRecord 模式**：支持 ActiveRecord 形式调用，实体类只需继承 Model 类即可进行强大的 CRUD 操作

- **支持自定义全局通用操作**：支持全局通用方法注入（ Write once, use anywhere ）

- **内置代码生成器**：采用代码或者 Maven 插件可快速生成 Mapper 、 Model 、 Service 、 Controller 层代码，支持模板引擎，更有超多自定义配置等您来使用

- **内置分页插件**：基于 MyBatis 物理分页，开发者无需关心具体操作，配置好插件之后，写分页等同于普通 List 查询

- **分页插件支持多种数据库**：支持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、Postgre、SQLServer 等多种数据库

- **内置性能分析插件**：可输出 SQL 语句以及其执行时间，建议开发测试时启用该功能，能快速揪出慢查询

- **内置全局拦截插件**：提供全表 delete 、 update 操作智能分析阻断，也可自定义拦截规则，预防误操作

**mybtis-plus安装pom.xml**

```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.5.2</version>
</dependency>
```

#### 2.3.2 防全表更新与删除插件

```java
/**
 * 针对 update 和 delete 语句 作用: 阻止恶意的全表更新删除
 */
@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //针对 update 和 delete 语句 作用: 阻止恶意的全表更新删除
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return interceptor;
    }
}
```

#### 2.3.3 编码

编写实体类 `User.java`（此处使用了 [Lombok (opens new window)](https://www.projectlombok.org/)简化代码）

```java
@Data
public class User {
    /**
     * 主键
     */
    private Long id;
    /**
     *用户名称
     */
    private String username;
    /**
     *用户密码
     */
    private String password;
    /**
     * 所在城市
     */
    private String city;
    /**
     *用户邮箱
     */
    private String email;

}
```

编写 Mapper 包下的 `UserMapper`接口

```java
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
```

#### 2.3.4 开始使用

添加测试类，进行功能测试：

```java
@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

}
```

提示

UserMapper 中的 `selectList()` 方法的参数为 MP 内置的条件封装器 `Wrapper`，所以不填写就是无任何条件

访问页面，输入http://localhost:8080/test

网页中输出：

![image001](https://github.com/huangpw/springboot-mybatis-plus/blob/master/images/build102/image-20230424183513735.png)

![image001](.\images\build102\image-20230424183513735.png)

### 2.4 SpringBoot三层架构Controller、Service、Dao

画图讲解

![image002](https://github.com/huangpw/springboot-mybatis-plus/blob/master/images/build102/image002.png)

![image002](.\images\build102\image002.png)

### 2.5 SpringBoot编写增删改查接口

#### 2.5.1 mybtis-plus配置分页和阻止恶意的全表更新删除

```java
/**
 * 针对 update 和 delete 语句 作用: 阻止恶意的全表更新删除
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        //针对 update 和 delete 语句 作用: 阻止恶意的全表更新删除
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());

        //分页配置
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
```

#### 2.5.2 IUserService

```java
public interface IUserService  extends IService<User> {

    Page<User> page(Integer current, Integer size, String username);

    boolean saveOrUpdateById(User user);

    boolean deleteBatchIds(List<Integer> ids);
}
```

#### 2.5.3 UserServiceImpl

```java

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public Page<User> page(Integer current, Integer size, String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if (!"".equals(username)) {
            //精确查询
//            wrapper.eq(User::getUsername,username);
            //模糊查询
            wrapper.like(User::getUsername, username);

        }
        Page<User> page = page(
                new Page<>(
                        current,
                        size
                ),
                wrapper
        );
        return page;
    }
    @Override
    public boolean saveOrUpdateById(User user) {
        if (user.getId() != null) {
            return updateById(user);
        } else {
            return save(user);
        }
    }

    @Override
    public boolean deleteBatchIds(List<Integer> ids) {
        return deleteBatchIds(ids);
    }
}
```

#### 2.5.4 UserController

```java
@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/page")
    public Page<User> list(
            @RequestParam("username") String username,
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "15") Integer size
    ) {
        return userService.page(current,size,username);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("/delete")
    public boolean delete(@RequestBody List<Integer> ids) {
        return userService.deleteBatchIds(ids);
    }

}
```

### 2.6 postman测试Springboot接口

切记配置自增

UserController 参数修改

```java
// @RequestParam("username") String username,
@RequestParam(defaultValue = "") String username,
```

实体类 修改 id 配置自增

```java
@TableId(value = "id", type = IdType.AUTO)
```

实体类 修改  Long修改为Integer

```java
@TableId(value = "id",type = IdType.AUTO)
private Integer id;
```

修改 service删除方法调用修改

```java
@Override
public boolean deleteBatchIds(List<Integer> ids) {
    return removeBatchByIds(ids);
}
```

配置日志

https://baomidou.com/pages/e131bd/

```xml
# 方式一
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

### 2.7 SpringBoot项目跨域问题解决

https://www.axios-http.cn/docs/example

### 什么是跨域

浏览器遵循**同源政策**(scheme(协议)、host(主机)和port(端口)都相同则为同源)。

跨域是如何形成的？
当我们请求一个url的 协议、域名、端口三者之间任意一个与当前页面url的协议、域名、端口 不同这种现象我们把它称之为跨域

跨域会导致：
1、无法读取非同源网页的 Cookie、LocalStorage 和 IndexedDB
2、无法接触非同源网页的 DOM
3、无法向非同源地址发送 AJAX 请求（可以发送，但浏览器会拒绝接受响应）

导致跨域的根本原因是请求浏览器的同源策略导致的 ，而跨域请求报错的原因是： 浏览器同源策略 && 请求是ajax类型 && 请求确实跨域了。

#### 2.7.1 解决跨域问题-方法一

```java
@CrossOrigin("*")
```

#### 2.7.2 解决跨域问题-方法二

```java
@Configuration
public class CorsConfig {

    // 当前跨域请求最大有效时长。这里默认1天
    private static final long MAX_AGE = 24 * 60 * 60;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1 设置访问源地址
        corsConfiguration.addAllowedHeader("*"); // 2 设置访问源请求头
        corsConfiguration.addAllowedMethod("*"); // 3 设置访问源请求方法
        corsConfiguration.setMaxAge(MAX_AGE);
        source.registerCorsConfiguration("/**", corsConfiguration); // 4 对接口配置跨域设置
        return new CorsFilter(source);
    }
}
```

### 2.8 前端Vue页面对接后端列表分页接口并实现模糊查询功能

iview2.0网址：https://iview.github.io/docs/guide/install

一套基于 Vue.js 的高质量 UI 组件库

#### 2.8.1 iviewUI框架引入-方法一

```html
<!-- 引入样式 -->
<link rel="stylesheet" href="//unpkg.com/iview/dist/styles/iview.css">
<!-- 引入组件库 -->
<script src="//unpkg.com/iview/dist/iview.min.js"></script>
```

#### 2.8.2 iviewUI框架引入-方法二

**npm安装**

```sh
npm install iview --save
```

**在项目中引入**

```html
<link rel="stylesheet" href="node_modules/iview/dist/styles/iview.css">
<script type="text/javascript" src="node_modules/iview/dist/iview.min.js"></script>
```

**template**

```html
<i-Table border ref="selection" :columns="title" :data="tableData" @on-selection-change="select"></i-Table>

<Page :current="pageInfo.current" :total="pageInfo.total" show-total @on-change="currentPage"/>

currentPage(e) {
	this.pageInfo.current = e
	this.getList()
},
```

**data**

```vue
                pageInfo: {
                    current: 1,
                    size: 10,
                    username: ""
                },
				tableData: [],
                title: [
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: 'ID',
                        key: 'id'
                    },
                    {
                        title: '用户名',
                        key: 'username'
                    },
                    {
                        title: '用户邮箱',
                        key: 'email'
                    },
                    {
                        title: '所在城市',
                        key: 'city'
                    },
                    {
                        title: '操作',
                        align: 'center',
                        type: 'text',
                        width: 260,
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.isShowModal = true
                                            // 防止表格中的数据随着修改而发生改变
                                            this.ruleForm = Object.assign({}, params.row)
                                            // console.log(params.row)
                                            this.titleModal = "编辑用户"
                                        }
                                    }
                                }, '编辑'),

                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    on: {
                                        click: () => {
                                            console.log(params.row)
                                            this.delIds = [params.row.id]
                                            this.isConfirmDelete = true
                                        }
                                    }
                                }, '删除')
                            ])
                        }
                    }
                ],
```

**methods**

```vue
getList() {
    axios({
        url: '/user/page',
        methods: 'get',
        params:
        this.pageInfo

    })
        .then(response => {
            console.log(response)
            let data = response.data
            this.tableData = data.records
            this.pageInfo.current = data.current
            this.pageInfo.page = data.page
            this.pageInfo.total = data.total
        })
        .catch(function (error) {
            console.log(error);
        });
},
```

**Button 按钮**

https://iview.github.io/components/button

基础组件，触发业务逻辑时使用。

注意：非 template/render 模式下，需使用 `i-button`。

VUE created与mounted区别

一、created mounted
1）created:在模板渲染成html前调用，即通常初始化某些属性值，然后再渲染成视图。
2）mounted:在模板渲染成html后调用，通常是初始化页面完成后，再对html的dom节点进行一些需要的操作。



**生命周期中的浏览器渲染**

- **created**：已创建，在模板渲染成`html`前调用，即通常初始化某些属性值，然后再渲染成视图。

- **mounted**：已挂载，在模板渲染成`html`后调用，通常是初始化页面完成后，再对`html`的`dom`节点进行一些操作。

### 2.9 前端Vue页面对接新增编辑接口实现新增编辑功能-前端Vue页面增加表单验证

弹窗模块：https://iview.github.io/components/modal

**template**

```vue
    <i-Button @click="save('insert')">添加用户</i-Button>
    
    <Modal v-model="isShowModal" :title="titleModal" :footer-hide="true">
        <i-form ref="ruleForm" :model="ruleForm" :rules="rule" :label-width="80">
            <form-item label="用户昵称" prop="username">
                <i-input style="width:200px" v-model="ruleForm.username" placeholder="用户昵称"></i-input>
            </form-item>
            <form-item label="用户邮箱" prop="email">
                <i-input style="width:200px" v-model="ruleForm.email" placeholder="用户邮箱"></i-input>
            </form-item>
            <form-item label="城市" prop="city">
                <i-input style="width:200px" v-model="ruleForm.city" placeholder="城市"></i-input>
            </form-item>
            <form-item label="密码" prop="password">
                <i-input style="width:200px" v-model="ruleForm.password" placeholder="密码" type="password"></i-input>
            </form-item>
            <form-item>
                <i-Button type="primary" @click="save()">提交</i-Button>
                <i-Button @click="handleReset('ruleForm')" style="margin-left: 8px">重置</i-Button>
            </form-item>
        </i-form>
    </Modal>
```

**data**

```vue
isShowModal: false,

{
    title: '操作',
    align: 'center',
    type: 'text',
    width: 260,
    render: (h, params) => {
        return h('div', [
            h('Button', {
                props: {
                    type: 'primary',
                    size: 'small'
                },
                style: {
                    marginRight: '5px'
                },
                on: {
                    click: () => {
                        this.isShowModal = true
                        // 防止表格中的数据随着修改而发生改变
                        this.ruleForm = Object.assign({}, params.row)
                        // console.log(params.row)
                        this.titleModal = "编辑用户"
                    }
                }
            }, '编辑'),

            h('Button', {
                props: {
                    type: 'error',
                    size: 'small'
                },
                on: {
                    click: () => {
                        console.log(params.row)
                        this.delIds = [params.row.id]
                        this.isConfirmDelete = true
                    }
                }
            }, '删除')
        ])
    }
}
```

**methods**

```vue
save(flag) {
    this.$refs["ruleForm"].validate((valid) => {
        if (flag == "insert") {
            this.isShowModal = true
            this.titleModal = "新增用户"
            this.$refs["ruleForm"].resetFields();
            return false;
        }
        if (valid) {
            axios.post('/user/save', this.ruleForm)
                .then(response => {
                    console.log(response);
                    if (!response.data) {
                        this.$Message.error("提交失败")
                    } else {
                        this.$Message.success('提交成功!');
                        this.isShowModal = false
                        this.getList()
                    }
                })
                .catch(function (error) {
                    console.log(error);
                });

        } else {
            this.isShowModal = true
            this.$Message.error('提交失败!');
        }
    })
},
```

表单验证：https://iview.github.io/components/form

**data**

```vue
                ruleForm: {
                    username: "",
                    email: "",
                    city: "",
                    password: ""
                },
                rule: {
                    username: [
                        {required: true, message: '请输入用户昵称', trigger: 'blur'}
                    ],
                    email: [
                        {required: true, message: '请输入邮箱地址', trigger: 'blur'}
                    ],
                    city: [
                        {required: true, message: '请输入用户地址', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'}
                    ],
                },
```

### 2.10 前端Vue页面对接删除接口实现单选多选删除功能

**template**

```vue
<i-Button @click="handleSelectAll(true)">选择全部</i-Button>
<i-Button @click="handleSelectAll(false)">取消全选</i-Button>
<i-Button @click="isConfirmDelete=true" :disabled="delIds.length<=0 ? true:false">删除</i-Button>

<Modal v-model="isConfirmDelete" width="360">
    <p slot="header" style="color:#f60;text-align:center">
        <Icon type="ios-information-circle"></Icon>
        <span>数据删除</span>
    </p>
    <div style="text-align:center">
        <p>你确定删除id为{{delIds}}的数据吗</p>
    </div>
    <div slot="footer">
        <i-Button type="error" size="large" long @click="delList">确认删除</i-Button>
    </div>
</Modal>
```

**data**

```vue
  isConfirmDelete: false,
  isShowModal: false,
```

**methods**

```vue
            delList() {
                this.isConfirmDelete = false
                axios.post('/user/delete', this.delIds)
                    .then(response => {
                        console.log(response);
                        if (!response.data) {
                            this.$Message.error("删除失败")
                        } else {
                            //删除后重置分页
                            this.pageInfo.current = 1
                            this.getList()
                        }
                    })
                    .catch(function (error) {
                        console.log(error);
                    });

            },
            select(selection, row) {
                this.delIds = []
                selection.forEach(item => {
                    this.delIds.push(item.id)
                })
                console.log(this.delIds)
            },
                        save(flag) {
                this.$refs["ruleForm"].validate((valid) => {
                    if (flag == "insert") {
                        this.isShowModal = true
                        this.titleModal = "新增用户"
                        this.$refs["ruleForm"].resetFields();
                        return false;
                    }
                    if (valid) {
                        axios.post('/user/save', this.ruleForm)
                            .then(response => {
                                console.log(response);
                                if (!response.data) {
                                    this.$Message.error("提交失败")
                                } else {
                                    this.$Message.success('提交成功!');
                                    this.isShowModal = false
                                    this.pageInfo.current = 1
                                    this.getList()
                                }
                            })
                            .catch(function (error) {
                                console.log(error);
                            });

                    } else {
                        this.isShowModal = true
                        this.$Message.error('提交失败!');
                    }
                })
            },
```

### 2.11 SpringBoot+Vue增删改查项目总结

**template**

```vue
<i-Button @click="handleSelectAll(true)">选择全部</i-Button>
<i-Button @click="handleSelectAll(false)">取消全选</i-Button>
<i-Button @click="isConfirmDelete=true" :disabled="delIds.length<=0 ? true:false">删除</i-Button>

<Modal v-model="isConfirmDelete" width="360">
    <p slot="header" style="color:#f60;text-align:center">
        <Icon type="ios-information-circle"></Icon>
        <span>数据删除</span>
    </p>
    <div style="text-align:center">
        <p>你确定删除id为{{delIds}}的数据吗</p>
    </div>
    <div slot="footer">
        <i-Button type="error" size="large" long @click="delList">确认删除</i-Button>
    </div>
</Modal>
```

**data**

```vue
  isConfirmDelete: false,
  isShowModal: false,
```

### methods

```vue
            delList() {
                this.isConfirmDelete = false
                axios.post('/user/delete', this.delIds)
                    .then(response => {
                        console.log(response);
                        if (!response.data) {
                            this.$Message.error("删除失败")
                        } else {
                            //删除后重置分页
                            this.pageInfo.current = 1
                            this.getList()
                        }
                    })
                    .catch(function (error) {
                        console.log(error);
                    });

            },
            select(selection, row) {
                this.delIds = []
                selection.forEach(item => {
                    this.delIds.push(item.id)
                })
                console.log(this.delIds)
            },
                        save(flag) {
                this.$refs["ruleForm"].validate((valid) => {
                    if (flag == "insert") {
                        this.isShowModal = true
                        this.titleModal = "新增用户"
                        this.$refs["ruleForm"].resetFields();
                        return false;
                    }
                    if (valid) {
                        axios.post('/user/save', this.ruleForm)
                            .then(response => {
                                console.log(response);
                                if (!response.data) {
                                    this.$Message.error("提交失败")
                                } else {
                                    this.$Message.success('提交成功!');
                                    this.isShowModal = false
                                    this.pageInfo.current = 1
                                    this.getList()
                                }
                            })
                            .catch(function (error) {
                                console.log(error);
                            });

                    } else {
                        this.isShowModal = true
                        this.$Message.error('提交失败!');
                    }
                })
            },
```



## 3. 项目简述

SpringBoot+Vue分离实现数据增删改查

技术栈：

- SpringBoot 2.x
- mybatis-plus
- Vue2 + iview
- axios



## 4.运行截图

![image003](https://github.com/huangpw/springboot-mybatis-plus/blob/master/images/build102/image003.png)

![image003](.\images\build102\image003.png)

