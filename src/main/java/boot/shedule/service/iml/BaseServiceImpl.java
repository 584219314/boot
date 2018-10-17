package boot.shedule.service.iml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import boot.shedule.mapper.BaseMapper;
import boot.shedule.service.BaseService;
import boot.shedule.service.PageModel;

public abstract class BaseServiceImpl<E, M extends BaseMapper<E>>  {

    @Autowired
    protected M mapper;

    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }
    
    public int insert(E record) {
        return mapper.insert(record);
    }

    public E selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(E record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    public List<E> list(E record) {
        return mapper.list(record);
    }

    public Page<E> pageList(PageModel<E> pageModel) {
        Page<E> page = PageHelper.startPage(pageModel.getPageNum(), pageModel.getPageSize()).doSelectPage(() -> {
            mapper.pageList(pageModel.getParam());
        });
        return page;
    }

}
