# 安卓mvp教程
博客地址 http://blog.csdn.net/jinfulin/article/details/71975455
## 一.前言
在早起的安卓应用,大概2014年之前,大部分的应用都是使用传统的mvc进行架构.而传统的mvc有它的弊端.我们先从理论上对比下mvc与mvp.
mvc的弊端:大部分情况下view和controller都是由activity 控制,activity责任太重了.这样mvp优势就凸显出来了.
##二.mvp介绍

![这里写图片描述](http://img.blog.csdn.net/20170513143623234?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvamluZnVsaW4=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
     随着UI创建技术的功能日益增强，UI层也履行着越来越多的职责。为了更好地细分视图(View)与模型(Model)的功能，让View专注于处理数据的可视化以及与用户的交互，同时让Model只关系数据的处理，基于MVC概念的MVP(Model-View-Presenter)模式应运而生。
     在MVP模式里通常包含4个要素：
     (1)View:负责绘制UI元素、与用户进行交互(在Android中体现为Activity);
     (2)View interface:需要View实现的接口，View通过View interface与Presenter进行交互，降低耦合，方便进行单元测试;
     (3)Model:负责存储、检索、操纵数据(有时也实现一个Model interface用来降低耦合);
     (4)Presenter:作为View与Model交互的中间纽带，处理与用户交互的负责逻辑。


<font color = "red">mvp是把UI逻辑抽成view接口,把业务逻辑抽成presenter接口,modle类还是原来的model.view和presenter互相持有对方的引用.</font>

## 三.mvp splash实例
好了,废话不多说,现在看一个最最简单实例,splash页面的mvp实现.
这里多说一句,很多人看到这会觉得splash页面也用mvp?是不是过渡设计?这里确实有过渡设计的意思,只是为了讲解方便,找个最简单的例子才能举一反三.

###.1.SplashActivity 
```
public class SplashActivity extends Activity implements SplashView{

    @InjectView(R.id.tv)
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.inject(this);
        //view 持有一个p层引用.
        SplashPresenterImp presenterImp = new SplashPresenterImp(this);
        //调用方法
        presenterImp.checkLogin();

    }



    @Override
    public void onGetLoginState(boolean isLogin) {
        if(isLogin){
            tv.setText("登录成功");
            SplashActivity.this.startActivity(new Intent(SplashActivity.this,LoginActivity.class));
        }else{
            tv.setText("登录失败");
        }
    }
}

```

### 2.splashActivity实现的接口
```
public interface SplashView {
    void onGetLoginState(boolean isLogin);
}
```

### 3.view层讲解

我们先不管p层怎么实现的,只看结果.
 onGetLoginState(boolean isLogin)这个方法就是返回的状态.
只要isLogin是true,就认为是登录成功,然后做我们应该界面展示的操作就行.
同理,只要isLogin是false,我们就认为登录失败.

那具体怎么判断的登录成功失败呢,这就叫是p层的事情了.

我们再看句话
        <font color = 'red'>//view 持有一个p层引用.</font>
        SplashPresenterImp presenterImp sb= new SplashPresenterImp(this);
        //调用方法
        presenterImp.checkLogin();


### 4.presenter层

```
public class SplashPresenterImp implements SplashPresenter {
    private SplashView splashView;

    public SplashPresenterImp(SplashView splashView) {
        this.splashView = splashView;
    }

    @Override
    public void checkLogin() {
		  //登录操作
		if(登录成功){
	        splashView.onGetLoginState(true);
        }else{
	         splashView.onGetLoginState(false);
        }
    }
}

```
SplashPresenter接口同样只写了一个方法
```
public interface SplashPresenter {
    void checkLogin();
}
```
### 5.presenter层讲解
注意这句话
 public SplashPresenterImp(SplashView splashView) {
        this.splashView = splashView;
    }
    p层会持有view层的一个引用,这样p层做登录的逻辑操作,如果成功给view层返回对应的状态


### 6.总接
可以看到，View只负责处理与用户进行交互，并把数据相关的逻辑操作都扔给了Presenter去做
这里主要的注意的地方就是view层与p层互相持有对方的引用.


源码地址.
https://github.com/jin870132/mvpDemo
