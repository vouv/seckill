package org.seckill.controller;


import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillResult;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.entity.Seckill;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/seckill")
public class SeckillController {

    private final Logger  logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private SeckillService seckillService;



    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {


        List<Seckill> seckills = seckillService.getSeckillList();

        model.addAttribute("list",seckills);

        return "list";
    }
    

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {

        if (seckillId == null){
            return "rediect:/seckill/list";
        }

        Seckill seckill = seckillService.getById(seckillId);
        if (seckill == null){
            return "forward:/seckill/list";
        }

        model.addAttribute("seckill",seckill);

        return "detail";
    }





    //ajax json
    @RequestMapping(value = "/{seckillId}/exposer",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
        SeckillResult<Exposer> result;
        try{
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true,exposer);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            result = new SeckillResult<Exposer>(false,e.getMessage());
        }

        return result;

    }

    @RequestMapping(value = "/{seckillId}/{md5}/execution",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId, @PathVariable("md5") String md5,
                                                   @CookieValue(value = "userPhone",required = false)Long userPhone){
        SeckillResult<SeckillExecution> result;
        if (userPhone == null){
            result = new SeckillResult<SeckillExecution>(false,"未注册");
            return result;
        }

//        catch (RepeatKillException e){
//            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
//            result = new SeckillResult<SeckillExecution>(true,execution);
//        }catch (SeckillCloseException e){
//            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.END);
//            result = new SeckillResult<SeckillExecution>(true,execution);
//        }

        try{
            SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId,userPhone,md5);
            result = new SeckillResult<SeckillExecution>(true,execution);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
            result = new SeckillResult<SeckillExecution>(false,e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time(){
        return new SeckillResult<Long>(true,new Date().getTime());
    }


}
