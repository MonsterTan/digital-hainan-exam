# Digital Hainan Exam

基于 DDD（领域驱动设计）分层架构的 Spring Boot 3 多模块项目。

---

## 技术栈

| 技术 | 版本 | 说明 |
|------|------|------|
| Spring Boot | 3.2.6 | 基础框架 |
| MyBatis | - | 持久层 |
| Druid | - | 连接池 |
| H2 Database | - | 嵌入式内存数据库 |
| FastJSON2 | - | JSON 序列化 |
| MapStruct | - | 对象映射 |
| Lombok | 1.18.46 | 代码简化 |
| SpringDoc | - | Swagger API 文档 |

---

## 项目架构

项目采用 **DDD 分层架构**，共包含 8 个 Maven 模块：

```
digital-hainan-exam
├── digital-hainan-exam-workbench    # 启动入口、Mapper XML、配置文件
├── digital-hainan-exam-web          # Controller、拦截器、Filter、异常处理
├── digital-hainan-exam-app          # 应用层：CommandHandler、Assembler
├── digital-hainan-exam-domain       # 领域层：实体、仓储接口、领域服务
├── digital-hainan-exam-infrastructure  # 基础设施层：仓储实现、DAO、DO
├── digital-hainan-exam-common       # 公共层：Command、Query、VO、上下文
├── digital-hainan-exam-shared       # 共享内核：异常、Result、分页、Query 基类
└── digital-hainan-exam-client       # 对外 API 客户端（预留）
```


### 依赖方向

```
web → app → domain ← infrastructure
         ↘ common ↗
            ↓
          shared
```

---

## 快速启动

```bash
# 编译项目
mvn clean package -DskipTests

# 启动应用
java -jar digital-hainan-exam-workbench/target/digital-hainan-exam-workbench-*.jar
```

应用默认端口：**8899**

---

## Swagger API 文档

项目集成了 SpringDoc（OpenAPI 3），启动后可通过以下地址访问：

| 资源 | 地址 |
|------|------|
| Swagger UI | http://localhost:8899/swagger-ui.html |
| OpenAPI JSON | http://localhost:8899/v3/api-docs |

> Swagger 相关路径为公开路径，无需登录即可访问。

---

## 用户鉴权

### 认证方式

项目采用 **JWT（JSON Web Token）** 进行用户认证：

- Token 有效期：**2 小时**
- 签发者：`digital-hainan-exam`
- 密钥配置：`application-dev.properties` 中的 `jwt.secret`

### 鉴权流程

1. 用户通过 `POST /public/login` 接口登录，提交用户名和密码
2. 服务端校验密码（MD5 + 盐值加密），校验通过后签发 JWT Token
3. 后续请求在请求头中携带 Token，拦截器自动解析并注入用户上下文
4. 未登录用户访问 `/api/**` 受保护接口时，返回认证错误 JSON 响应（不跳转）

### 相关路径规则

| 路径模式 | 说明 |
|----------|------|
| `/public/**` | 公开接口，免鉴权（登录接口在此路径下） |
| `/api/**` | 受保护接口，需携带有效 JWT Token |


### 预置账号

| 用户名 | 密码 |
|--------|------|
| admin | admin123 |

---

## 数据库

### 类型

项目使用 **H2 内存数据库**（开发环境），特点：

- 嵌入式运行，无需额外安装数据库服务
- 内存模式，应用重启后数据重置
- 启动时自动执行 DDL 初始化脚本（`schema.sql`）

### 连接信息

| 配置项 | 值 |
|--------|---|
| Driver | `org.h2.Driver` |
| URL | `jdbc:h2:mem:digital_hainan_exam` |
| 用户名 | `sa` |
| 密码 | （空） |

### 数据表

| 表名 | 说明 |
|------|------|
| `book` | 图书信息表 |
| `sys_user` | 系统用户表 |

---

## API 端点

### 公开接口

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | `/public/login` | 用户登录 |

### 图书管理（需登录）

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/api/book/get` | 查询图书 |
| POST | `/api/book/create` | 创建图书 |

---

## 项目配置

配置文件位于 `digital-hainan-exam-workbench/src/main/resources/`：

| 文件 | 说明 |
|------|------|
| `application.properties` | 主配置（端口、profile） |
| `application-dev.properties` | 开发环境配置（数据源、JWT、Swagger） |
| `application-prd.properties` | 生产环境配置 |
| `schema.sql` | 数据库 DDL 初始化脚本 |
| `logback.xml` | 日志配置 |
