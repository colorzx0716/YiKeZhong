package com.bawie.yikezhong.view;

import com.bawie.yikezhong.base.BaseView;
import com.bawie.yikezhong.bean.PraiseBean;

/**
 * Created by 张肖肖 on 2017/12/2.
 */

public interface PraiseView extends BaseView{

    void praiseSuccess(PraiseBean praiseBean);
    void praiseFailure(String e);

}
