package filtro;

import entidade.Aluno;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "filtroRestritoAluno", urlPatterns = {"/aluno/*"})
public class filtroRestritoAluno implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        Aluno aluno = (Aluno)((HttpServletRequest) request).getSession().getAttribute("aluno");

        if ((aluno != null) && (!((String) aluno.getNome()).isEmpty())) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("http://localhost:8080/aplicacaoMVC/home");
        }
    }
    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
