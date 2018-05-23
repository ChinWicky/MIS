package com.example.demo.web;
import com.example.demo.entity.SevProject;
import com.example.demo.entity.SysUser;
import com.example.demo.entity.Type;
import com.example.demo.service.SevProjectService;
import com.example.demo.service.TypeService;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Wicky
 * @since 2018-03-26
 */
@Controller
@RequestMapping("/sevProject")
public class SevProjectController {

    @Autowired
    private SevProjectService sevProjectService;
    @Autowired
    private TypeService typeService;



    @RequestMapping("/add")
    public String addSevProject(HttpServletRequest request,Model model){
        //得到html传进来的数据
        String proName = request.getParameter("proName");
        String a=request.getParameter("price");
        double price=Double.parseDouble(a);
        String c=request.getParameter("serTime");
        double serTime=Double.parseDouble(c);
        String b=request.getParameter("higPrice");
        double higPrice=Double.parseDouble(b);
        String d=request.getParameter("priPrice");
        double priPrice=Double.parseDouble(d);
        int typeid  = Integer.parseInt(request.getParameter("typeid"));

        SevProject sevProject=new SevProject();
        sevProject.setProName(proName);
        sevProject.setPrice(price);
        sevProject.setSerTime(serTime);
        sevProject.setHigPrice(higPrice);
        sevProject.setPriPrice(priPrice);
        sevProject.setTypeid(typeid);
        sevProject.setType(typeService.selectById(typeid));

        //添加
        this.sevProjectService.insert(sevProject);

        GeneralController generalController=new GeneralController();
        //得到用户
        model=generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);
        //显示所有服务
        model= this.returnAllSevPro(model);

        return "pro/sevproject_list";

    }

    @RequestMapping("/toAdd")
    public String ToaddSevProject(HttpServletRequest request,Model model){
        GeneralController generalController=new GeneralController();
        //得到用户
        model=generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);

        return "pro/add_pro";

    }


    @RequestMapping("/Select")
    public String SelectByName(HttpServletRequest request,Model model){

        Map map=new HashMap();
        java.util.List<Type> types = typeService.selectByMap(map);
        model.addAttribute("types",types);
        String proName=request.getParameter("proName");
        java.util.List<SevProject> sev_projects = sevProjectService.findByName(proName);
        //返回列表
        model.addAttribute("sev_projects",sev_projects);

        GeneralController generalController=new GeneralController();
        //得到用户
        model=generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);
        return "pro/sevproject_list";
    }

    @RequestMapping("/SelectByType")
    public String SelectByType(HttpServletRequest request,Model model){
        Map map=new HashMap();
        java.util.List<Type> types = typeService.selectByMap(map);
        model.addAttribute("types",types);
        int typeid  = Integer.parseInt(request.getParameter("typeid"));
        // map.put("typeid",typeid);
        java.util.List<SevProject> sev_projects = sevProjectService.findType(typeid);
        //返回列表
        model.addAttribute("sev_projects",sev_projects);

        GeneralController generalController=new GeneralController();
        //得到用户
        model=generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);
        return "pro/sevproject_list";
    }


    @RequestMapping("/delete")
    public String deleteSevProject(int proId,Model model){
        this.sevProjectService.deleteById(proId);

        GeneralController generalController=new GeneralController();
        //得到用户
        model=generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);
        //显示所有服务
        model= this.returnAllSevPro(model);
        return "pro/sevproject_list";
    }


    //修改成功之后返回列表
    @RequestMapping("/edit")
    public String editSevProject(HttpServletRequest request,Model model){
        //得到html传进来的数据
        int proId = Integer.parseInt(request.getParameter("proId"));
        String proName = request.getParameter("proName");
        String a=request.getParameter("price");
        double price=Double.parseDouble(a);
        String c=request.getParameter("serTime");
        double serTime=Double.parseDouble(c);
        String b=request.getParameter("higPrice");
        double higPrice=Double.parseDouble(b);
        String d=request.getParameter("priPrice");
        double priPrice=Double.parseDouble(d);
        int typeid  = Integer.parseInt(request.getParameter("typeid"));
//要查询出来之后再set
        SevProject sevProject = this.sevProjectService.selectById(proId);
        sevProject.setProName(proName);
        sevProject.setPrice(price);
        sevProject.setSerTime(serTime);
        sevProject.setHigPrice(higPrice);
        sevProject.setPriPrice(priPrice);
        sevProject.setTypeid(typeid);
        sevProject.setType(typeService.selectById(typeid));

        //添加
        sevProjectService.updateById(sevProject);
        GeneralController generalController=new GeneralController();
        //得到用户
        model=generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);
        //显示所有服务
        model= this.returnAllSevPro(model);
        return "pro/sevproject_list";
    }

    //修改种类名称
    @RequestMapping("/toEdit")
    public String toEditSevProject(HttpServletRequest request,Model model){
        int proId = Integer.parseInt(request.getParameter("proId"));
        SevProject sevProject=new SevProject();
        sevProject=sevProjectService.selectById(proId);
        model.addAttribute("proId",proId);
        model.addAttribute("sevProject",sevProject);

        GeneralController generalController=new GeneralController();
        //得到用户
        model=generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);

        return "pro/edit_pro";

    }
    // 访问所有sevProject列表

    @RequestMapping("/list")
    public String showSevProject(Model model){
        GeneralController generalController=new GeneralController();
        //得到用户
        model=generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);
        //显示所有服务
        model= this.returnAllSevPro(model);
        return "pro/sevproject_list";

    }

    @RequestMapping("/byidlist")
    public String showSevProjectById(HttpServletRequest request,Model model){
        int typeid = Integer.parseInt(request.getParameter("typeid"));
        Type type=new Type();
        type=typeService.selectById(typeid);
        model.addAttribute("type",type);

        GeneralController generalController=new GeneralController();
        //得到用户
        model=generalController.returnUser(model);
        //显示所有种类
        model= this.returnAllType(model);

        return "pro/sevproject_list";

    }
    //通用方法查所有种类
    public Model returnAllType(Model model){
        Map map=new HashMap();
        java.util.List<Type> types = typeService.selectByMap(map);
        model.addAttribute("types",types);
        return model;

    }

    //通用方法查所有服务
    public Model returnAllSevPro(Model model){
        java.util.List<SevProject> sev_projects = sevProjectService.findAll();
        model.addAttribute("sev_projects",sev_projects);
        return model;
    }

}
