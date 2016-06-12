package faststore.configserver.admin.group.controller;

import faststore.configserver.api.group.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.annotation.Resource;

/**
 * 配件业务控制器
 */
@RequestMapping("group")
@Controller
public class GroupController {

    @Resource
    private GroupService groupService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        return "group/add";
    }

//    /**
//     * 添加动作
//     *
//     * @param httpServletRequest
//     * @param response
//     * @return
//     */
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity<String> create(@ModelAttribute("GroupEntity") Group GroupEntity, HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {
//        GroupEntity.setBuyId(System.nanoTime());
//        GroupEntity.setBuyDate(new Timestamp(new Date().getTime()));
//        GroupEntity.setInputDate(new Timestamp(new Date().getTime()));
//        GroupEntity.setInputPersonId("3");
////        GroupService.save(GroupEntity);
//        return getStringResponseEntity(new DwzActionResult());
//    }
//
//
////    private void redirect(HttpServletResponse response, String url) throws IOException {
////        response.sendRedirect(url); //内部跳转, 如果直接返回url并不会跳转
////    }
//
//    /**
//     * 编辑
//     *
//     * @param GroupEntity 表单提交封装
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity<String> edit(@ModelAttribute("GroupEntity") Group GroupEntity, ModelMap model, HttpServletResponse response) throws Exception {
//        GroupService.edit(GroupEntity);
//        return getStringResponseEntity(new DwzActionResult());
//    }
//
//    /**
//     * 编辑请求UI
//     *
//     * @param id
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
//    public String edit(@PathVariable String id, Model model) throws Exception {
//        Group GroupEntity = GroupService.getBeanById(id);
//        if (GroupEntity != null) {
//            model.addAttribute("GroupEntity", GroupEntity);      // 隐藏表单
//        } else {
//            //  没有发现这个
//            return "";
//        }
//
//        return "group/edit";
//    }
//
//    /**
//     * 删除请求
//     * delete ajax使用，但是有的web服务器可能不支持。 所以使用 get替代
//     *
//     * @param id
//     * @return
//     */
////    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
////    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
//    @ResponseBody
//    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
//    public ResponseEntity<String> del(@PathVariable String id) throws Exception {
//        GroupService.remove(id);
//        return getStringResponseEntity(new DwzActionResult());
//    }
//
//    private ResponseEntity<String> getStringResponseEntity(DwzActionResult dwzActionResult) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        String s = mapper.writeValueAsString(dwzActionResult);
//        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.set("Content-type", "application/json;charset=UTF-8");
//        return new ResponseEntity<String>(s, responseHeaders, HttpStatus.OK);
//    }
//
//    /**
//     * 对应路径 /template
//     *
//     * @return
//     */
//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public String index2() {
//        return "group/index";
//    }
//
//    /**
//     * 显示主页 , 实际的路径是/group/
//     *
//     * @return
//     */
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String index() {
//        return "group/list";
//    }
//
//    /**
//     * 分页显示所有的用户
//     *
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/show", method = RequestMethod.GET)
//    public String list(Model model) throws Exception {
//        List<Group> list = groupService.find();
//        model.addAttribute("list", list);
//        return "group/show";
//    }
//    /**
//     * 显示单个id
//     *
//     * @param id
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/{id}/show", method = RequestMethod.GET)
//    public String getById(@PathVariable String id, Model model) throws Exception {
//        Group GroupEntity = GroupService.getBeanById(id);
//        System.out.println(GroupEntity.toString());
//        if (GroupEntity != null) {
//            model.addAttribute("GroupEntity", GroupEntity);
//        }
//        return "group/view";
//    }

}