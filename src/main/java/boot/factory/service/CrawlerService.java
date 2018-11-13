package boot.factory.service;

import boot.aspect.RedisLock;
import boot.shedule.model.req.CrawlerDocToDataReq;
import boot.shedule.model.req.CrawlerSetDataReq;
import boot.shedule.model.req.FlipReq;
import boot.shedule.model.req.MainReq;
import boot.shedule.model.res.CrawlerDocToDataRes;
import boot.shedule.model.res.CrawlerSetDataRes;
import boot.shedule.model.res.FlipRes;


public interface CrawlerService {
	/**
	 * 数据入库
	 */
	CrawlerSetDataRes setData(CrawlerSetDataReq req);
	
	/**
	 * 页面数据处理
	 * @param req
	 * @return
	 */
	CrawlerDocToDataRes docToData(CrawlerDocToDataReq req);
	
	/**
	 * 获取翻页链接
	 */
	FlipRes flip(FlipReq req);
	
	/**
	 * 整体流程
	 * @throws Exception 
	 */
	void main(MainReq req) throws Exception;
	
	void clearData(String code);
	
	String testAspect(String code);
}
