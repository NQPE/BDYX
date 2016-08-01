package com.custom.bdyx.presenter;

import com.custom.bdyx.model.bean.SubstationVo;
import com.custom.bdyx.view.ITab1FragView;
import com.huaxi100.hxcommonlib.base.HxBaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by levi on 2016/7/26.
 */
public class Tab1FragPresenter extends CommonPresenter<ITab1FragView<List<SubstationVo>>>{
    public Tab1FragPresenter(HxBaseActivity activity) {
        super(activity);
    }

    public void loadData(int page){
        List<SubstationVo> list=new ArrayList<>();
        mockData(list,page);
        getView().onLoadListData(list,page);
        getView().onLoadEnd();
    }

    private void mockData(List<SubstationVo> list,int page) {
        page=page-1;
        for (int i=0+10*page;i<10+10*page;i++){
            SubstationVo vo=new SubstationVo();
            vo.setId(i + "");
            vo.setName("第" + i + "个变电站");
            if (i%3==0){
                vo.setThumb("http://www.easyicon.net/api/resizeApi.php?id=570536&size=96");
            }else if (i%3==1){
                vo.setThumb("http://www.easyicon.net/api/resizeApi.php?id=569795&size=96");
            }else if (i%3==2){
                vo.setThumb("http://www.easyicon.net/api/resizeApi.php?id=569794&size=96");
            }
            list.add(vo);
        }
    }
}
