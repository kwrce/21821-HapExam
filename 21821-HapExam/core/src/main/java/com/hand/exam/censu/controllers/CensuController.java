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
    public ResponseData selectList(HttpServletRequest request, Censu condition,
                                   @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                   @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
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
    public ResponseData selectListDetail(String headerId,
                                   HttpServletRequest request, Censu condition,
                                   @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                   @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        System.out.println(headerId);
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        if(headerId==null){
            condition.setOrderNumber("-1");
        }
        else
            condition.setOrderNumber(""+headerId);
        IRequest iRequest = createRequestContext(request);
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
        List<Censu> datas = censuService.batchUpdate(iRequest, census);
        return new ResponseData(datas);
    }

    @RequestMapping(value = "/hap/sub/census/remove", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody
            List<Censu> census) {
        IRequest iRequest = createRequestContext(request);
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