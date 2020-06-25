package financas.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import financas.model.dto.CredenciasDTO;

@WebFilter(urlPatterns = "/faces/protected/*", dispatcherTypes = {DispatcherType.REQUEST})
public class AutorizacaoFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
        HttpSession sessao = ((HttpServletRequest) request).getSession();
        CredenciasDTO user = (CredenciasDTO) sessao.getAttribute("usuario");
        if (user != null)
            chain.doFilter(request, response);
        else {
            sessao.setAttribute("message", "Faça o login");
            String contextPath = ((HttpServletRequest) request).
                                                    getContextPath();
            ((HttpServletResponse) response).
                    sendRedirect(contextPath + "/faces/index.xhtml");
        }
	}

	@Override
	public void destroy() {
	}

}
