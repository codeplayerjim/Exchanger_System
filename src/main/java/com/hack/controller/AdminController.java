package com.hack.controller;

import com.hack.entity.*;
import com.hack.exception.CustomException;
import com.hack.service.CollegeService;
import com.hack.service.CourseService;
import com.hack.service.ExchangerService;
import com.hack.service.UserLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;
@Controller
@RequestMapping("/admin")
public class AdminController {

        @Resource(name = "exchangerServiceImpl")
        private ExchangerService exchangerService;

//        @Resource(name = "teacherServiceImpl")
//        private TeacherService teacherService;

        @Resource(name = "courseServiceImpl")
        private CourseService courseService;

        @Resource(name = "collegeServiceImpl")
        private CollegeService collegeService;

        @Resource(name = "userLoginServiceImpl")
        private UserLoginService userloginService;

        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<交换生操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

        //  交换生信息显示
        @RequestMapping("/showExchanger")
        public String showExchanger(Model model, Integer page) throws Exception {

            List<ExchangerCustom> list = null;
            //页码对象
            PagingVO pagingVO = new PagingVO();
            //设置总页数
            pagingVO.setTotalCount(exchangerService.getCountExchanger());
            if (page == null || page == 0) {
                pagingVO.setToPageNo(1);
                list = exchangerService.findByPaging(1);
            } else {
                pagingVO.setToPageNo(page);
                list = exchangerService.findByPaging(page);
            }

            model.addAttribute("exchangerList", list);
            model.addAttribute("pagingVO", pagingVO);

            return "admin/showExchanger";

        }

        //  添加交换生信息页面显示
        @RequestMapping(value = "/addExchanger", method = {RequestMethod.GET})
        public String addExchangerUI(Model model) throws Exception {

            List<College> list = collegeService.findAll();

            model.addAttribute("collegeList", list);

            return "admin/addExchanger";
        }

        // 添加学生信息操作
        @RequestMapping(value = "/addExchanger", method = {RequestMethod.POST})
        public String addExchanger(ExchangerCustom exchangerCustom, Model model) throws Exception {

            Boolean result = exchangerService.save(exchangerCustom);

            if (!result) {
                model.addAttribute("message", "学号重复");
                return "error";
            }
            //添加成功后，也添加到登录表
            UserLogin userlogin = new UserLogin();
            userlogin.setUsername(exchangerCustom.getE_id().toString());
            userlogin.setPassword("123");
            userlogin.setRole(2);
            userloginService.save(userlogin);

            //重定向
            return "redirect:/admin/showExchanger";
        }

        // 修改交换生信息页面显示
        @RequestMapping(value = "/editExchanger", method = {RequestMethod.GET})
        public String editExchangerUI(Integer id, Model model) throws Exception {
            if (id == null) {
                //加入没有带交换生id就进来的话就返回学生显示页面
                return "redirect:/admin/showExchanger";
            }
            ExchangerCustom exchangerCustom = exchangerService.findById(id);
            if (exchangerCustom == null) {
                throw new CustomException("未找到该名交换生");
            }
            List<College> list = collegeService.findAll();

            model.addAttribute("collegeList", list);
            model.addAttribute("exchanger", exchangerCustom);


            return "admin/editStudent";
        }

        // 修改学生信息处理
        @RequestMapping(value = "/editStudent", method = {RequestMethod.POST})
        public String editStudent(ExchangerCustom exchangerCustom) throws Exception {

            exchangerService.updataById(exchangerCustom.getE_id(), exchangerCustom);

            //重定向
            return "redirect:/admin/showExchanger";
        }

        // 删除学生
        @RequestMapping(value = "/removeExchanger", method = {RequestMethod.GET} )
        private String removeExchanger(Integer id) throws Exception {
            if (id == null) {
                //加入没有带学生id就进来的话就返回学生显示页面
                return "admin/showStudent";
            }
            exchangerService.removeById(id);
            userloginService.removeByName(id.toString());

            return "redirect:/admin/showExchanger";
        }

        // 搜索学生
        @RequestMapping(value = "selectExchanger", method = {RequestMethod.POST})
        private String selectExchanger(String findByName, Model model) throws Exception {

            List<ExchangerCustom> list = exchangerService.findByName(findByName);

            model.addAttribute("exchangerList", list);
            return "admin/showExchanger";
        }

        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<教师操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

//        // 教师页面显示
//        @RequestMapping("/showTeacher")
//        public String showTeacher(Model model, Integer page) throws Exception {
//
//            List<TeacherCustom> list = null;
//            //页码对象
//            PagingVO pagingVO = new PagingVO();
//            //设置总页数
//            pagingVO.setTotalCount(teacherService.getCountTeacher());
//            if (page == null || page == 0) {
//                pagingVO.setToPageNo(1);
//                list = teacherService.findByPaging(1);
//            } else {
//                pagingVO.setToPageNo(page);
//                list = teacherService.findByPaging(page);
//            }
//
//            model.addAttribute("teacherList", list);
//            model.addAttribute("pagingVO", pagingVO);
//
//            return "admin/showTeacher";
//
//        }
//
//        // 添加教师信息
//        @RequestMapping(value = "/addTeacher", method = {RequestMethod.GET})
//        public String addTeacherUI(Model model) throws Exception {
//
//            List<College> list = collegeService.finAll();
//
//            model.addAttribute("collegeList", list);
//
//            return "admin/addTeacher";
//        }
//
//        // 添加教师信息处理
//        @RequestMapping(value = "/addTeacher", method = {RequestMethod.POST})
//        public String addTeacher(TeacherCustom teacherCustom, Model model) throws Exception {
//
//            Boolean result = teacherService.save(teacherCustom);
//
//            if (!result) {
//                model.addAttribute("message", "工号重复");
//                return "error";
//            }
//            //添加成功后，也添加到登录表
//            Userlogin userlogin = new Userlogin();
//            userlogin.setUsername(teacherCustom.getUserid().toString());
//            userlogin.setPassword("123");
//            userlogin.setRole(1);
//            userloginService.save(userlogin);
//
//            //重定向
//            return "redirect:/admin/showTeacher";
//        }
//
//        // 修改教师信息页面显示
//        @RequestMapping(value = "/editTeacher", method = {RequestMethod.GET})
//        public String editTeacherUI(Integer id, Model model) throws Exception {
//            if (id == null) {
//                return "redirect:/admin/showTeacher";
//            }
//            TeacherCustom teacherCustom = teacherService.findById(id);
//            if (teacherCustom == null) {
//                throw new CustomException("未找到该名学生");
//            }
//            List<College> list = collegeService.finAll();
//
//            model.addAttribute("collegeList", list);
//            model.addAttribute("teacher", teacherCustom);
//
//
//            return "admin/editTeacher";
//        }
//
//        // 修改教师信息页面处理
//        @RequestMapping(value = "/editTeacher", method = {RequestMethod.POST})
//        public String editTeacher(TeacherCustom teacherCustom) throws Exception {
//
//            teacherService.updateById(teacherCustom.getUserid(), teacherCustom);
//
//            //重定向
//            return "redirect:/admin/showTeacher";
//        }
//
//        //删除教师
//        @RequestMapping("/removeTeacher")
//        public String removeTeacher(Integer id) throws Exception {
//            if (id == null) {
//                //加入没有带教师id就进来的话就返回教师显示页面
//                return "admin/showTeacher";
//            }
//            teacherService.removeById(id);
//            userloginService.removeByName(id.toString());
//
//            return "redirect:/admin/showTeacher";
//        }
//
//        //搜索教师
//        @RequestMapping(value = "selectTeacher", method = {RequestMethod.POST})
//        private String selectTeacher(String findByName, Model model) throws Exception {
//
//            List<TeacherCustom> list = teacherService.findByName(findByName);
//
//            model.addAttribute("teacherList", list);
//            return "admin/showTeacher";
//        }
//
//        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<课程操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
//
//        // 课程信息显示
//        @RequestMapping("/showCourse")
//        public String showCourse(Model model, Integer page) throws Exception {
//
//            List<CourseCustom> list = null;
//            //页码对象
//            PagingVO pagingVO = new PagingVO();
//            //设置总页数
//            pagingVO.setTotalCount(courseService.getCountCouse());
//            if (page == null || page == 0) {
//                pagingVO.setToPageNo(1);
//                list = courseService.findByPaging(1);
//            } else {
//                pagingVO.setToPageNo(page);
//                list = courseService.findByPaging(page);
//            }
//
//            model.addAttribute("courseList", list);
//            model.addAttribute("pagingVO", pagingVO);
//
//            return "admin/showCourse";
//
//        }
//
//        //添加课程
//        @RequestMapping(value = "/addCourse", method = {RequestMethod.GET})
//        public String addCourseUI(Model model) throws Exception {
//
//            List<TeacherCustom> list = teacherService.findAll();
//            List<College> collegeList = collegeService.finAll();
//
//            model.addAttribute("collegeList", collegeList);
//            model.addAttribute("teacherList", list);
//
//            return "admin/addCourse";
//        }
//
//        // 添加课程信息处理
//        @RequestMapping(value = "/addCourse", method = {RequestMethod.POST})
//        public String addCourse(CourseCustom courseCustom, Model model) throws Exception {
//
//            Boolean result = courseService.save(courseCustom);
//
//            if (!result) {
//                model.addAttribute("message", "课程号重复");
//                return "error";
//            }
//
//
//            //重定向
//            return "redirect:/admin/showCourse";
//        }
//
//        // 修改教师信息页面显示
//        @RequestMapping(value = "/editCourse", method = {RequestMethod.GET})
//        public String editCourseUI(Integer id, Model model) throws Exception {
//            if (id == null) {
//                return "redirect:/admin/showCourse";
//            }
//            CourseCustom courseCustom = courseService.findById(id);
//            if (courseCustom == null) {
//                throw new CustomException("未找到该课程");
//            }
//            List<TeacherCustom> list = teacherService.findAll();
//            List<College> collegeList = collegeService.finAll();
//
//            model.addAttribute("teacherList", list);
//            model.addAttribute("collegeList", collegeList);
//            model.addAttribute("course", courseCustom);
//
//
//            return "admin/editCourse";
//        }
//
//        // 修改教师信息页面处理
//        @RequestMapping(value = "/editCourse", method = {RequestMethod.POST})
//        public String editCourse(CourseCustom courseCustom) throws Exception {
//
//            courseService.upadteById(courseCustom.getCourseid(), courseCustom);
//
//            //重定向
//            return "redirect:/admin/showCourse";
//        }
//
//        // 删除课程信息
//        @RequestMapping("/removeCourse")
//        public String removeCourse(Integer id) throws Exception {
//            if (id == null) {
//                //加入没有带教师id就进来的话就返回教师显示页面
//                return "admin/showCourse";
//            }
//            courseService.removeById(id);
//
//            return "redirect:/admin/showCourse";
//        }
//
//        //搜索课程
//        @RequestMapping(value = "selectCourse", method = {RequestMethod.POST})
//        private String selectCourse(String findByName, Model model) throws Exception {
//
//            List<CourseCustom> list = courseService.findByName(findByName);
//
//            model.addAttribute("courseList", list);
//            return "admin/showCourse";
//        }

        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<其他操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

        // 普通用户账号密码重置
        @RequestMapping("/userPasswordReset")
        public String userPasswordRestUI() throws Exception {
            return "admin/userPasswordRest";
        }

        // 普通用户账号密码重置处理
        @RequestMapping(value = "/userPasswordReset", method = {RequestMethod.POST})
        public String userPasswordRest(UserLogin userlogin) throws Exception {

            UserLogin u = userloginService.findByName(userlogin.getUsername());

            if (u != null) {
                if (u.getRole() == 0) {
                    throw new CustomException("该账户为管理员账户，没法修改");
                }
                u.setPassword(userlogin.getPassword());
                userloginService.updateByName(userlogin.getUsername(), u);
            } else {
                throw new CustomException("没找到该用户");
            }

            return "admin/userPasswordRest";
        }

        // 本账户密码重置
        @RequestMapping("/passwordRest")
        public String passwordRestUI() throws Exception {
            return "/passwordRest";
        }

    }
