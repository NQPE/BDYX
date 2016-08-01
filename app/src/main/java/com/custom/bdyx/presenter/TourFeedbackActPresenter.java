package com.custom.bdyx.presenter;

import com.custom.bdyx.model.bean.SubstationVo;
import com.custom.bdyx.view.IDetailActView;
import com.custom.bdyx.view.ITourFeedbackActView;
import com.huaxi100.hxcommonlib.base.HxBaseActivity;
import com.huaxi100.hxcommonlib.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by levi on 2016/7/26.
 */
public class TourFeedbackActPresenter extends CommonPresenter<ITourFeedbackActView<SubstationVo>>{
    public TourFeedbackActPresenter(HxBaseActivity activity) {
        super(activity);
    }

    public void loadData(String id){
        getView().onLoadData(mockData(id));
    }

    private SubstationVo mockData(String id) {
        int index=Integer.parseInt(id);
        SubstationVo vo=new SubstationVo();
        vo.setName("第" + id + "个变电站");
        if (index%4==0){
            vo.setImg("http://img1.imgtn.bdimg.com/it/u=2024668784,2798189433&fm=21&gp=0.jpg");
            vo.setStatusId("0");
            vo.setStatusName("正常");
        }else if (index%4==1){
            vo.setImg("http://img2.imgtn.bdimg.com/it/u=3687836391,11766917&fm=21&gp=0.jpg");
            vo.setStatusId("1");
            vo.setStatusName("全面");
        }else if (index%4==2){
            vo.setImg("http://img1.imgtn.bdimg.com/it/u=3787462654,2785683535&fm=21&gp=0.jpg");
            vo.setStatusId("2");
            vo.setStatusName("特殊");
        }else if (index%4==3){
            vo.setImg("http://img5.imgtn.bdimg.com/it/u=1550967956,3801214441&fm=21&gp=0.jpg");
            vo.setStatusId("3");
            vo.setStatusName("熄灯");
        }
        List<SubstationVo.Record> records=new ArrayList<>();
        for (int i=0;i<index;i++){
            SubstationVo.Record record=new SubstationVo.Record();
            record.setId(i + "");
            record.setTourName("小" + i);
            if (i%4==0){
                record.setTourStatusId("0");
                record.setTourStatusName("正常");
            }else if (i%4==1){
                record.setTourStatusId("1");
                record.setTourStatusName("全面");
            }else if (i%4==2){
                record.setTourStatusId("2");
                record.setTourStatusName("特殊");
            }else if (i%4==3){
                record.setTourStatusId("3");
                record.setTourStatusName("熄灯");
            }
            record.setTourTime(DateUtils.formatTime(System.currentTimeMillis()+""));
            records.add(record);
            if(i>20){
                break;
            }
        }
        vo.setRecords(records);
        return vo;
    }
}
