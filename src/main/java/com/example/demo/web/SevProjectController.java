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

        //返回列表
        Map map=new HashMap();
        java.util.List<SevProject> sev_projects = sevProjectService.selectByMap(map);
        model.addAttribute("sev_projects",sev_projects);
        java.util.List<Type> types = typeService.selectByMap(map);
        model.addAttribute("types",types);
        //显示用户名
        HttpServletRequest request1= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        SysUser user1 =(SysUser)request1.getSession().getAttribute("user");
        model.addAttribute("user",user1);
        System.out.print("stringname..."+sevProject.getPrice());
        //添加
        this.sevProjectService.insert(sevProject);
        //返回列表

        return "pro/sevproject_list";

    }

    @RequestMapping("/toAdd")
    public String ToaddSevProject(HttpServletRequest request,Model model){
        Map map=new HashMap();
        java.util.List<Type> types = typeService.selectByMap(map);
        model.addAttribute("types",types);

        //显示用户名
        HttpServletRequest request1= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        SysUser user1 =(SysUser)request1.getSession().getAttribute("user");
        model.addAttribute("user",user1);

        return "pro/add_pro";

    }


    @RequestMapping("/Select")
    public String SelectByName(HttpServletRequest request,Model model){
        String proName=request.getParameter("proName");
        Map map=new HashMap();
        java.util.List<Type> types = typeService.selectByMap(map);
        model.addAttribute("types",types);
        map.put("pro_name",proName);
        System.out.print("Select..."+proName);
        java.util.List<SevProject> sev_projects = sevProjectService.selectByMap(map);
        //返回列表
        model.addAttribute("sev_projects",sev_projects);

        //显示用户名
        HttpServletRequest request1= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        SysUser user1 =(SysUser)request1.getSession().getAttribute("user");
        model.addAttribute("user",user1);

        return "pro/sevproject_list";
    }

    @RequestMapping("/SelectByType")
    public String SelectByType(int typeid,Model model){
        Map map=new HashMap();
        java.util.List<Type> types = typeService.selectByMap(map);
        model.addAttribute("types",types);
        map.put("typeid",typeid);
        java.util.List<SevProject> sev_projects = sevProjectService.selectByMap(map);
        //返回列表
        model.addAttribute("sev_projects",sev_projects);

        //显示用户名
        HttpServletRequest request1= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        SysUser user1 =(SysUser)request1.getSession().getAttribute("user");
        model.addAttribute("user",user1);

        return "pro/sevproject_list";
    }


    @RequestMapping("/delete")
    public String deleteSevProject(int proId,Model model){
        this.sevProjectService.deleteById(proId);
        //返回列表
        Map map=new HashMap();
        java.util.List<SevProject> sev_projects = sevProjectService.selectByMap(map);
        model.addAttribute("sev_projects",sev_projects);
        java.util.List<Type> types = typeService.selectByMap(map);
        model.addAttribute("types",types);

        //显示用户名
        HttpServletRequest request1= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        SysUser user1 =(SysUser)request1.getSession().getAttribute("user");
        model.addAttribute("user",user1);
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


        //添加
        sevProjectService.updateById(sevProject);
        //返回列表
        Map map=new HashMap();
        java.util.List<SevProject> sev_projects = sevProjectService.selectByMap(map);
        model.addAttribute("sev_projects",sev_projects);
        java.util.List<Type> types = typeService.selectByMap(map);
        model.addAttribute("types",types);

        //显示用户名
        HttpServletRequest request1= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        SysUser user1 =(SysUser)request1.getSession().getAttribute("user");
        model.addAttribute("user",user1);
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
       // System.out.print("ToEdit。。。。。"+proId);
        Map map=new HashMap();
        java.util.List<Type> types = typeService.selectByMap(map);
        model.addAttribute("types",types);

        //显示用户名
        HttpServletRequest request1= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        SysUser user1 =(SysUser)request1.getSession().getAttribute("user");
        model.addAttribute("user",user1);
        return "pro/edit_pro";

    }
    // 访问所有sevProject列表

    @RequestMapping("/list")
    public String showSevProject(Model model){
        Map map=new HashMap();
        java.util.List<SevProject> sev_projects = sevProjectService.selectByMap(map);
        model.addAttribute("sev_projects",sev_projects);
        java.util.List<Type> types = typeService.selectByMap(map);
        model.addAttribute("types",types);

        //显示用户名
        HttpServletRequest request1= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        SysUser user1 =(SysUser)request1.getSession().getAttribute("user");
        model.addAttribute("user",user1);
  //      System.out.println("session2... username:"+user1.getUsername()+";password:"+user1.getPassword());
        return "pro/sevproject_list";

    }

    @RequestMapping("/byidlist")
    public String showSevProjectById(HttpServletRequest request,Model model){
        Map map=new HashMap();
        int typeid = Integer.parseInt(request.getParameter("typeid"));
        Type type=new Type();
        type=typeService.selectById(typeid);
        java.util.List<Type> types = typeService.selectByMap(map);
        model.addAttribute("types",types);
        model.addAttribute("type",type);
        //显示用户名
        HttpServletRequest request1= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        SysUser user1 =(SysUser)request1.getSession().getAttribute("user");
        model.addAttribute("user",user1);
        return "pro/sevproject_list";

    }

}
