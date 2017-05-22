create table users(
 	uid int primary key auto_increment,	#用户id
	username varchar(30),	#用户名
	password varchar(30),	#密码		
	name varchar(30),		#用户真实姓名
	email varchar(30),		#用户邮箱
	phone varchar(30),		#用户手机号码
	addr varchar(30),		#用户收货地址
	sex char(1),			#用户性别:1代表男,0代表女
	birthday date,			#用户生日
	state int,				#用户激活状态:1代表激活,0代表未激活
	code varchar(64)		#用户激活码
);

create table categorys(
	cid int primary key auto_increment,  #一级分类id 
	cname varchar(20)		#一级分类的名称	
);

create table categoryseconds(
	csid int primary key auto_increment, #二级分类id
	csname varchar(20),		#二级分类名称
	cid int ,  				#一级分类的id(外键)
	foreign key (cid) references categorys (cid)
);

create table sellers(
	sid int primary key auto_increment,#商家id
	saccount varchar(30),   #商家账号,登录时使用的账号   
	spassword varchar(30),  #商家密码,登录时使用的密码
	sname varchar(30),		#商家名称,显示给用户看的
	sphone varchar(30),  	#商家联系方式(指的是手机)
	sdesc  varchar(200)     #店铺描述
	registdate date,		#店铺注册的时间
);

create table products(
	pid int primary key auto_increment,#商品id
	pname varchar(20), #商品名称
	market_price double, #商品市场价
	shop_price double,  #商品商城价
	imageurl varchar(200), #商品图片对象的url地址
	pnum int(11), 		#商品的库存 
	pdesc varchar(200), #商品的描述
	is_hot char(1),	#是否是热门商品:1.代表是热门商品,0.代表不是热门商品
	pdate datetime, #商品上架时间
	csid int(11),  #商品对应的二级分类id
	sid	 int(11),   #商品属于的商户id
	foreign key (csid) references categoryseconds (csid),
	foreign key (sid) references sellers (sid)
);

create table cartitems(
	ciid int primary key auto_increment,#购物车项id 
	quantity int,#购物车项的商品的数量
	pid int,#商品id
	uid int,#用户id 
	foreign key (pid) references products (pid),
	foreign key (uid) references users (uid)
);


