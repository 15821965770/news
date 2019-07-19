package com.qf.controller;

import com.qf.pojo.Item;
import com.qf.service.ItemService;
import com.qf.utils.PageInfo;
import com.qf.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static com.qf.constant.SsmConstant.REDIRECT;

@Controller
@RequestMapping("/item")
public class ItemController {
//    http://localhost/item/list
//    请求方法:GET

    @Autowired
    ItemService itemService;

    @GetMapping("/list")
    public String list(String name,
                       @RequestParam(value = "page",defaultValue = "1")Integer page,
                       @RequestParam(value = "size",defaultValue = "5")Integer size,
                        Model model){


        PageInfo<Item> pageInfo=itemService.findByNameAndLimit(name,page,size);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("name", name);
        return "/item/item_list";
    }


    @GetMapping("/add-ui")
    public String addUI(){
        return "item/item_add";
    }

//    http://localhost/item/add
//    请求方法:POST
//
    @Value("${pic_types}")
    public String picTypes;

    @PostMapping("/add")
    public String add(@Valid Item item, BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            MultipartFile picFile, HttpServletRequest req) throws IOException {


        if (bindingResult.hasErrors()){
            String message = bindingResult.getFieldError().getDefaultMessage();
            redirectAttributes.addAttribute("addInfo",message );
            return REDIRECT+"/item/add-ui";
        }



        long size = picFile.getSize();
        if (size>5242880L){
            //todo 图片过大
            redirectAttributes.addAttribute("addInfo","图片过大" );

            return REDIRECT+"/item/add-ui";
        }


        boolean flag=false;

        String[] split = picTypes.split(",");
        for (String type : split) {
            if (StringUtils.endsWithIgnoreCase(
                    picFile.getOriginalFilename(),type )){

                flag=true;
                break;
            }
        }

        if (!flag){
//          todo  图片类型不正确
            return null;
        }

        BufferedImage image = ImageIO.read(picFile.getInputStream());
            if (image==null){
                redirectAttributes.addAttribute("addInfo","图片已损坏" );

                return REDIRECT+"/item/add-ui";
            }


//   =========================将图片保存到本地================================================


        String prefix = UUID.randomUUID().toString();
        String suffix  = StringUtils.substringAfterLast(picFile.getOriginalFilename(), ".");
        String newName=prefix+"."+suffix;
        String path = req.getServletContext().getRealPath("") + "\\static\\img\\" + newName;
        System.out.println(path);
        File file=new File(path);

        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }



        picFile.transferTo(file);
        String str="http://localhost/static/img/"+newName;
        item.setPic(str);

       Integer count =itemService.save(item);

        if (count==1){
            return REDIRECT+"/item/list";

        }else {
            redirectAttributes.addAttribute("addInfo","添加商品信息失败" );

            return REDIRECT+"/item/add-ui";
        }

    }

//    ========================保存商品信息======================================
        @DeleteMapping("/del/{id}")
        @ResponseBody
    public ResultVo del(@PathVariable Long id){
            Integer count =itemService.del(id);
//            Integer count=1;
            if (count==1){
                return new ResultVo(0,"成功",null);
            }else {
                return new ResultVo(1,"删除商品失败",null);

            }
        }


}
