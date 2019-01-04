package com.hand.exam.censu.controllers;

import com.hand.exam.order.dto.Order;
import com.hand.exam.order.service.IOrderService;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OrderController extends BaseController{

@Autowired
private IOrderService service;


@RequestMapping(value = "/hap/om/order/lines/query")
@ResponseBody
public ResponseData query(Long id,Order dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
    @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
    System.out.println(id);
    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
    IRequest requestContext = createRequestContext(request);
    return new ResponseData(service.select(requestContext,dto,page,pageSize));
}

@RequestMapping(value = "/hap/om/order/lines/submit")
@ResponseBody
public ResponseData update(@RequestBody List<Order> dto, BindingResult result, HttpServletRequest request){
    getValidator().validate(dto, result);
    if (result.hasErrors()) {
    ResponseData responseData = new ResponseData(false);
    responseData.setMessage(getErrorMessage(result, request));
    return responseData;
    }
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}

@RequestMapping(value = "/hap/om/order/lines/remove")
@ResponseBody
public ResponseData delete(HttpServletRequest request,@RequestBody List<Order> dto){
    service.batchDelete(dto);
    return new ResponseData();
}
}