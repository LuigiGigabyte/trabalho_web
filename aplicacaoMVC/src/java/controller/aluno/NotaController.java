package controller.aluno;

import entidade.Turma;
import entidade.Aluno;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AlunoDAO;

@WebServlet("/aluno/NotaController")
public class NotaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            AlunoDAO alunoDAO = new AlunoDAO();
        
            HttpSession session = request.getSession();
            Aluno alunoLogado = (Aluno) session.getAttribute("alunoLogado");
            ArrayList<Turma> listaTurmas = alunoDAO.getNotas(alunoLogado);

            request.setAttribute("listaTurmas", listaTurmas);

            request.getRequestDispatcher("/views/aluno/nota/listaNota.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msgError", "Erro ao processar operação: " + e.getMessage());
            request.getRequestDispatcher("/views/comum/showMessage.jsp").forward(request, response);
        }
    }
}
