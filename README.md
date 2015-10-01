## 目录
	1.  环境说明与搭建
	2.  开发规范（重要必读）
	3.  异常处理机制
	4.  单元测试方法
	5.  第三方应用调用方法
	6.  其他(eclipse debug 模式下实时编译，节省开发时间)

## 环境说明与搭建
	项目JDK版本需要1.6, Tomcat 需要 6以上;
	代码check下来之后，设置好JDK 1.6, 使用maven install 即可

## 开发规范（重要必读）
1. 配置文件

	- 配置文件按照各个环境不同而进行区分: 本地、dev、sit、uat、pre、prd
	- 本地开发环境的配置的配置文件(数据库等)，统一在src\main\resources目录下；
	- 如果不同环境需要有不同的配置，则这些配置需要独立出来，放在src\main\resources目录下的对应环境的文件夹

2. 代码结构

		com.trade.trade		按照功能模块进行命名
			|———— remote		最终将打包成接口包，提供第三方系统调用
			|———— service		接口的实现类
			|———— dao			数据库实现层
			|———— vo			数据载体，主要用于与第三方应用进行数据传输的载体
			|———— constans		放置系统常量
			|———— util			工具类
			|———— ...			其他(如果有的话)

3. 开发方法

	未完待续

4. 规范文件及示例代码
  
	- [参考规范文件][spec_JAVA]
[spec_JAVA]: http://gitlab.globalmarket.com/spec/gmc/blob/master/JAVA%E7%BC%96%E7%A8%8B%E8%A7%84%E8%8C%83.md
	- 示例代码见: `com.gm.trade.manufactory.*`

	

## 异常处理机制

所有业务异常都应该封装成GMSOAException返回，并且 Type = GMSOAExceptionType.BUSINESS，关键代码：

	/** 异常分类 **/
	private GMSOAExceptionType type;
	/** 异常代码 **/
	private GMSOAExceptionCode code;
	/** 异常数据 **/
	private Object errorData;

异常分类分为两类：
	
	GMSOAExceptionType.BUSINESS为业务逻辑错误，例如账号不能重复，业务逻辑异常为可以引导用户进行处理的异常。
	GMSOAExceptionType.SYSTEM 为系统异常错误，例如被除数不能为0，数据库中断，是用户无法直接影响的异常，可以统一提示用户“服务异常”。

异常代码为具体的错误类型。

异常数据为发生异常时，需要用于提醒用户的数据，例如添加的产品已经于数据库中的p1、p2重复，则可以返回p1\p2，用于对用户进行提示

系统异常不需要抓取，也不需要手动log