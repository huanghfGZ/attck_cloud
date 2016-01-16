package cn.attck.M.scan;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.attck.util.MvUtil;

@Controller
@RequestMapping("/scan")
public class ScanAction {

	@Resource
	private ScanService scanService;

	/**
	 * @author lauix
	 * @return 返回端口扫描视图
	 */
	@RequestMapping("/port")
	public ModelAndView port(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		MvUtil mu = new MvUtil();
		boolean is = mu.is_login(request);
		if (is == false) {
			mv.setViewName("redirect:/index.html");
			return mv;
		}
		mv.setViewName("scan/port");
		return mv;
	}

	/**
	 * @author lauix
	 * @return 返回网站扫描视图
	 */
	@RequestMapping("/web")
	public ModelAndView web(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		MvUtil mu = new MvUtil();
		boolean is = mu.is_login(request);
		if (is == false) {
			mv.setViewName("redirect:/index.html");
			return mv;
		}
		mv.setViewName("scan/web");
		return mv;
	}

	/**
	 * @author lauix
	 * @return 获取开放端口数据 添加
	 */
	@RequestMapping("/addPort")
	public @ResponseBody ModelAndView addPort(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		MvUtil mu = new MvUtil();
		boolean is = mu.is_login(request);
		if (is == false) {
			mv.setViewName("redirect:/index.html");
			return mv;
		}
		String ip = request.getParameter("ip");
		String ports = request.getParameter("ports");
		Object id = request.getSession().getAttribute("id");
		int user_id = Integer.parseInt(id.toString());
		scanService.portScan(ip, user_id, ports);
		mv.addObject("ip", ip);
		mv.addObject("status", '1');
		return mv;
	}

	/**
	 * @author lauix
	 * @return 获取开放端口数据
	 */
	@RequestMapping("/queryPort")
	public @ResponseBody ModelAndView queryPort(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		MvUtil mu = new MvUtil();
		boolean is = mu.is_login(request);
		if (is == false) {
			mv.setViewName("redirect:/index.html");
			return mv;
		}
		Object id = request.getSession().getAttribute("id");
		int user_id = Integer.parseInt(id.toString());
		List<Map<String, Object>> result = scanService.queryPort(user_id);
		mv.addObject("result", result);
		return mv;
	}
}