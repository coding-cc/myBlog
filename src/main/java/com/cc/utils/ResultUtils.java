package com.cc.utils;

import com.cc.model.ResultVO;

/**
 * @author : cc
 * @date : 2018-10-02  20:05
 */
public class ResultUtils {

    public static ResultVO success(Object data){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(200);
        resultVO.setMsg("成功");
        resultVO.setData(data);
        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO error(Integer code, String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }

}
