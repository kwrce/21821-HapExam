package com.hand.exam.censu.controllers;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import com.hand.exam.censu.dto.Censu;
import com.hand.exam.censu.service.ICensuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CensuController extends BaseController {
    @Autowired
    private ICensuService censuService;

    @RequestMapping("/hap/sub/census/query")
    @ResponseBody
    public ResponseData selectList(Long orderNum,
                                   HttpServletRequest request, Censu condition,
                                   @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                   @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
        System.out.println("===================================================");
        System.out.println(orderNum);
        System.out.println("===================================================");
        if(orderNum!=null)
            condition.setOrderNumber(orderNum+"");
        IRequest iRequest = createRequestContext(request);
        List<Censu> datas = censuService.selectByCensu(iRequest, condition, page,
                pagesize);
        for(Censu censu:datas){
            System.out.println(censu);
        }
        return new ResponseData(datas);
    }
    @RequestMapping("/hap/sub/census/detail")
    @ResponseBody
    public ResponseData selectListDetail(
                                   String btstu,
                                   Long orderNum,
                                   HttpServletRequest request, Censu condition,
                                   @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                   @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {

        System.out.println(")))))))))))))))))))))))))))))))))");
        System.out.println(btstu);
        System.out.println(orderNum);
        System.out.println(condition);
        System.out.println(")))))))))))))))))))))))))))))))))");
        IRequest iRequest = createRequestContext(request);
        if ("NEW".equals(condition.getOrderStatus())){
            condition.setOrderStatus("SUBMITED");
            int row= censuService.addCensu(iRequest, condition);
            if (row>0)
                System.out.println("添加成功");
        }
        if ("SUBMITED".equals(condition.getOrderStatus())){
            int row= censuService.updateCensu(iRequest, condition);
            if (row>0)
                System.out.println("更新成功");
        }
        if ("delete".equals(btstu)){
            List<Censu> datas = censuService.selectByCensuDetail(iRequest, condition, page,
                    pagesize);
            censuService.batchDelete(datas);
            int row= censuService.deleteCensu(iRequest, condition);
            if (row>0)
                System.out.println("删除成功");
        }
        if(orderNum==null){
            condition.setOrderNumber("-1");
        }
        else
            condition.setOrderNumber(""+orderNum);
        List<Censu> datas = censuService.selectByCensuDetail(iRequest, condition, page,
                pagesize);
        for(Censu censu:datas){
            System.out.println(censu);
        }
        return new ResponseData(datas);
    }

    @RequestMapping(value = "/hap/sub/census/submit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData submit(HttpServletRequest request, @RequestBody
            List<Censu> census) {
        IRequest iRequest = createRequestContext(request);
        System.out.println(")))))))))))))))))))))))))))))))))");
        for (Censu censu:census){
            System.out.println(censu);
        }
        System.out.println(")))))))))))))))))))))))))))))))))");
        List<Censu> datas = censuService.batchUpdate(iRequest, census);
        return new ResponseData(datas);
    }

//    @RequestMapping(value = "/hap/sub/census/saveorder", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseData saveorder(Long orderNum,
//                                  HttpServletRequest request, Censu condition,
//                                  @RequestParam(defaultValue = DEFAULT_PAGE) int page,
//                                  @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
//        IRequest iRequest = createRequestContext(request);
//        System.out.println(")))))))))))))))))))))))))))))))))");
//            System.out.println(condition);
//        System.out.println(")))))))))))))))))))))))))))))))))");
//        int row= censuService.addCensu(iRequest, condition);
//        if (row>0)
//            System.out.println("添加成功");
//        return new ResponseData();
//    }

    @RequestMapping(value = "/hap/sub/census/remove", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody
            List<Censu> census) {
        IRequest iRequest = createRequestContext(request);
        System.out.println("0000000000000000000000000000000000000");
        for (Censu censu:census){
            System.out.println(censu);
        }
        System.out.println("0000000000000000000000000000000000000");
        censuService.batchDelete(census);
        return new ResponseData();
    }

    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}