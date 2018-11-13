package boot.factory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import boot.factory.CrawlerFactory;
import boot.factory.service.CrawlerService;
import boot.shedule.model.req.CrawlerDocToDataReq;
import boot.shedule.model.req.CrawlerSetDataReq;
import boot.shedule.model.req.FlipReq;
import boot.shedule.model.req.MainReq;
import boot.shedule.model.res.CrawlerDocToDataRes;
import boot.shedule.model.res.CrawlerSetDataRes;
import boot.shedule.model.res.FlipRes;

@Service("crawlerService")
public class CrawlerServiceImpl implements CrawlerService{
	@Autowired
	private CrawlerFactory crawlerFactory;
	@Override
	public CrawlerSetDataRes setData(CrawlerSetDataReq req) {
		return crawlerFactory.getServiceImpl(req.getCode()).setData(req);
	}
	@Override
	public CrawlerDocToDataRes docToData(CrawlerDocToDataReq req) {
		return crawlerFactory.getServiceImpl(req.getCode()).docToData(req);
	}
	@Override
	public FlipRes flip(FlipReq req) {
		return crawlerFactory.getServiceImpl(req.getCode()).flip(req);
	}
	@Override
	public void main(MainReq req) throws Exception {
		crawlerFactory.getServiceImpl(req.getCode()).main(req);
	}
	@Override
	public void clearData(String code) {
		crawlerFactory.getServiceImpl(code).clearData(code);
	}
	@Override
	public String testAspect(String code) {
		return crawlerFactory.getServiceImpl(code).testAspect(code);
	}
}
