package Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
/**
 * 编码过滤器
 *
 */
public class EncodingFilter implements Filter{

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        /**
         * 思路： 使用装饰者模式，去装饰HttpServletRequest对象
         */
        //解决post参数提交的问题
        request.setCharacterEncoding("utf-8");
        MyRequest myRequest = new MyRequest((HttpServletRequest)request);
        //放行，放行的请求时装饰后的请求的对象
        chain.doFilter(myRequest, response);

    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

}
//装饰类
class MyRequest extends HttpServletRequestWrapper{
    private HttpServletRequest request;

    public MyRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        try {
            //GET提交参数问题
            String value = this.request.getParameter(name);
            if(value!=null){
                if("GET".equals(this.request.getMethod())){
                    value = new String(value.getBytes("iso-8859-1"),"utf-8");
                }
            }
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
