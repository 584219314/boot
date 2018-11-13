package boot.factory.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;










import com.alibaba.dubbo.common.utils.StringUtils;

import boot.aspect.RedisLock;
import boot.enums.CrawlerEnum;
import boot.factory.impl.AbstractCrawlerImpl;
import boot.shedule.model.CrawlerData;
import boot.shedule.model.req.CrawlerDocToDataReq;
import boot.shedule.model.req.CrawlerSetDataReq;
import boot.shedule.model.req.FlipReq;
import boot.shedule.model.res.CrawlerDocToDataRes;
import boot.shedule.model.res.CrawlerSetDataRes;
import boot.shedule.model.res.FlipRes;
import boot.util.RedisUtil;
@Service("gsBankServiceImpl")
public class GsBankServiceImpl extends AbstractCrawlerImpl{

	@Autowired
	RedisUtil redisUtil;
	@Override
	public CrawlerSetDataRes setData(CrawlerSetDataReq req) {
		// TODO Auto-generated method stub
		System.out.println(req.getPrice());
		return null;
	}

	@Override
	public CrawlerDocToDataRes docToData(CrawlerDocToDataReq req) {
		CrawlerDocToDataRes res = new CrawlerDocToDataRes();
        //获取的数据，存放在集合中
        List<CrawlerData> data = new ArrayList<CrawlerData>();
        //采用Jsoup解析
        Document doc = Jsoup.parse(req.getHtml());
        //获取html标签中的内容
        Elements elements = doc.select("table[class=smalllst_m]").select("tr");
        Element ele = elements.get(1);
        String pice=ele.select("td").get(3).text();
        //创建一个对象，这里可以看出，使用Model的优势，直接进行封装
        CrawlerData jdModel=new CrawlerData();
        jdModel.setPice(new BigDecimal(pice));
        //对象的值
        //将每一个对象的值，保存到List集合中
        data.add(jdModel);
		return res;
	}

	@Override
	public FlipRes flip(FlipReq req) {
		// TODO Auto-generated method stub
		return new FlipRes();
	}

	@Override
	public void clearData(String code) {
		
	}

	@Override
	protected String getCode() {
		return CrawlerEnum.GSNY.getCode();
	}

	@Override
	protected String getUrl() {
		return "https://mybank.icbc.com.cn/icbc/newperbank/perbank3/acctproduct/subframe_acctotlist_left.jsp?BUSITYPE=18&AcctOTOpenPro=130060000951|130060000952|130060000953|130060000954|130060000971|130060000972|130060000973|130060000974|130060000991|130060000992|130060000993|130060000994|130060001031|130060001032|130060001051|130060001052|130060001071|130060001072|130060001091|130060001092|130060001111|130060001112|130060001131|130060001132|130060001151|130060001152|130060001171|130060001172|130060001213|130060001214|130060001231|130060001232|130060001251|130060001252|130060001291|130060001292|130060001293|130060001294|130060001371|130060001372|130060001373|130060001374|130060001472|130060001473|130060001474|130060001475|130060001691|130060001692|130060001693|130060001694|130060002011|130060002012|130060002013|130060002014|130060002496|130060002497|130060002498|130060002499|130060002883|130060002884|130060002885|130060002886|130060003232|130060003239|130060003240|130060003241|130060005595|130060005596|130060005597|130060005598|130060012094|130060012095|130060012096|130060012097|130060020591|130060020594|130060020595|130060020596|130060030095|130060030096|130060030098|130060030099|130060038591|130060038592|130060038594|130060038595|130060047094|130060047096|130060047097|130060047098|130060056591|130060056592|130060056593|130060056594|130060066095|130060066096|130060066097|130060066098|130060076096|130060076097|130060076100|130060076101|130060087093|130060087095|130060087097|130060087099|130060095591|130060095592|130060095593|130060095594|130060100091|130060100092|130060100093|130060100094|130060107092|130060107093|130060107094|130060107095|130060114091|130060114092|130060114093|130060114094|130060122591|130060122592|130060122593|130060122594|130060129601|130060129603|130060129612|130060129613|130060134096|130060134097|130060134098|130060134099|130060136591|130060136592|130060136593|130060136594|130060142091|130060142092|130060145091|130060145092|130060151591|130060151592|130060151593|130060151594|130060153591|130060153592|130060153593|130060153594|130060158095|130060158096|130060158097|130060158098|130060163091|130060163092|130060163093|130060163094|130060168591|130060168592|130060168593|130060168594|130060173591|130060173592|130060173593|130060173594|130060176591|130060176592|130060179591|130060179592|130060179593|130060179594|130060187091|130060187092|130060187093|130060187094|130060194591|130060194592|130060197091|130060197092|130060197598|130060197602|130060203091|130060203092|130060206591|130060206592|130060209091|130060209092|130060211591|130060211592|130060212591|130060212592|130060216091|130060216092|130060219592|130060219593|130060223591|130060223592|130060225593|130060225594|130060226591|130060226592|130060231091|130060231092|130060233091|130060233092|130060235591|130060235592|130060236591|130060236592|130060241591|130060241592|130060243091|130060243092|130060248091|130060248092|130060251091|130060251092|130060255091|130060255092|130060256591|130060257091|130060262091|130060262591|130060264091|130060264092|130060272091|130060272092|130060273092|130060273093|130060279591|130060279592|130060282091|130060282092|130060285593|130060285594|130060287091|130060287092|130060288591|130060288592|130060289591|130060289592|130060290091|130060290092|130060290591|130060290592|130060291091|130060291092|130060292091|130060292092|130060292591|130060292592|130060293091|130060293092|130060293591|130060293592|130060294091|130060294092|130060295591|130060295592|130060295593|130060295594|130060296091|130060296092|130060296591|130060296592|130060297591|130060297592|130060298091|130060298092|130060298591|130060298592|130060299093|130060299094";
	}

	@Override
	public void setDataList(CrawlerDocToDataRes resHtml, CrawlerSetDataReq req) {
		req.setCode(getCode());
		req.setList(resHtml.getData());	
	}

	@Override
	public boolean stop(String html) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@RedisLock(keyName = "testAspect")
	@Override
	public String testAspect(String code) {
		return String.valueOf(redisUtil.get("testAspect"));
	}
}