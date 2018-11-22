package boot.factory.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import boot.factory.CrawlerFactory;
import boot.factory.service.CrawlerService;
import boot.shedule.mapper.master.CrawlerDataMasterMapper;
import boot.shedule.model.req.CrawlerDocToDataReq;
import boot.shedule.model.req.CrawlerSetDataReq;
import boot.shedule.model.req.FlipReq;
import boot.shedule.model.req.LoginReq;
import boot.shedule.model.req.MainReq;
import boot.shedule.model.res.CrawlerDocToDataRes;
import boot.shedule.model.res.FlipRes;
import boot.shedule.model.res.LoginRes;
import boot.util.URLFecter;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.helper.StringUtil;


public abstract class AbstractCrawlerImpl implements CrawlerService,InitializingBean{
	protected abstract String getCode();
	
	protected abstract String getUrl();
	@Autowired
	private CrawlerFactory crawlerFactory;
	@Autowired
	private CrawlerService crawlerService;
	@Override
	public void afterPropertiesSet() throws Exception {
		crawlerFactory.setServiceImpl(getCode(), this);		
	}
	
	//登陆应对验证码登陆
	public LoginRes login(LoginReq req) {
		LoginRes res = new LoginRes();
		// map装载表单数据
		Map<String, String> map = new HashMap<String, String>();
		map.put(req.getLoginNameHtml(), req.getLoginName());
		map.put(req.getPasswordHtml(), req.getPassword());
		map.put(req.getVerificationCodeHtml(), req.getVerificationCode());
		// 模拟浏览器创建连接，发起请求
		Connection conn = Jsoup.connect(req.getUrl());
		conn.header("User-Agent",
				"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; MALC)");
		try {
			Response response = conn
					.ignoreContentType(true)
					.method(Method.POST)
					.data(map).execute();
			if (response.statusCode() == 200) {
				// 获取cookies
				Map<String, String> cookies = response.cookies();
				res.setCookies(cookies);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return res;
	}
	@Override
	public void main(MainReq reqMain) throws Exception{
		String code = reqMain.getCode();
		// 初始化一个httpclient
		HttpClient client = new DefaultHttpClient();
		// 我们要爬取的一个地址，这里可以从数据库中抽取数据，然后利用循环，可以爬取一个URL队列
		String url = getUrl();
		int page = 0;
		do{
		// 抓取的数据
		String html = URLFecter.uRLParser(client, url);
		System.out.println(url);
		if(stop(html)){//暂停爬虫
			break;
		}
		CrawlerDocToDataReq reqHtml = new CrawlerDocToDataReq();
		//页面数据处理
		reqHtml.setHtml(html);
		reqHtml.setCode(code);
		CrawlerDocToDataRes resHtml = crawlerService.docToData(reqHtml);
		CrawlerSetDataReq req = new CrawlerSetDataReq();
		// 将抓取的数据插入数据库
		setDataList(resHtml, req);
		crawlerService.setData(req);
		FlipReq reqFlip = new FlipReq();
		reqFlip.setPage(page);
		reqFlip.setHtml(html);
		reqFlip.setCode(code);
		reqFlip.setUrl(url);
		FlipRes resFlip = crawlerService.flip(reqFlip);
		url = resFlip.getUrl();
		page = resFlip.getPage();
		}while(url!=null);
		crawlerService.clearData(code);
	}
	
	public abstract void setDataList(CrawlerDocToDataRes resHtml,CrawlerSetDataReq req);
	
	public abstract boolean stop(String html);
}
