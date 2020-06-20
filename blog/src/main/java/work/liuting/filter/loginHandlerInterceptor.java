package work.liuting.filter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截未登录的用户
 */
public class loginHandlerInterceptor implements HandlerInterceptor {
    /**
     * 目标方法执行之前
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Object user = httpServletRequest.getSession().getAttribute("loginUser");
        if (user==null){
//            未登录
            httpServletRequest.setAttribute("msg","未登录用户，请先登录");
            httpServletRequest.getRequestDispatcher("/admin/login").forward(httpServletRequest,httpServletResponse);
            return false;
        }else {
//            已登录
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
