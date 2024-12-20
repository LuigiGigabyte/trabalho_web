package controller.admin;

import entidade.Turma;
import entidade.Aluno;
import entidade.Disciplina;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TurmaDAO;

@WebServlet("/admin/RelatorioController")
public class RelatorioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TurmaDAO turmaDAO = new TurmaDAO();
        List<Turma> listaTurmas = turmaDAO.getAllTurmasComAlunosNotas();
        
        request.setAttribute("listaTurmas", listaTurmas);
        
        request.getRequestDispatcher("/views/admin/relatorio/listaRelatorio.jsp").forward(request, response);
    }
}
