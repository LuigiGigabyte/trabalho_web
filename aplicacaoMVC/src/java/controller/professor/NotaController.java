package controller.professor;

import controller.professor.*;
import entidade.Turma;
import entidade.Professor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ProfessorDAO;
import model.TurmaDAO;

@WebServlet(name = "ProfessorNotaController", urlPatterns ={"/professor/NotaController"})
public class NotaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
                String acao = request.getParameter("acao");

                ProfessorDAO professorDAO = new ProfessorDAO();

                HttpSession session = request.getSession();
                Professor professorLogado = (Professor) session.getAttribute("professorLogado");

                 RequestDispatcher rd;

                switch (acao) {
                    case "Listar":

                        ArrayList<Turma> listaTurmas = professorDAO.getNotas(professorLogado);
                        request.setAttribute("listaTurmas", listaTurmas);
                        request.getRequestDispatcher("/views/professor/nota/listaNota.jsp").forward(request, response);
                        break;
                    case "Alterar":
                        int turmaId = Integer.parseInt(request.getParameter("id"));
                        TurmaDAO turmaDAO = new TurmaDAO();
                        Turma turma = (Turma) turmaDAO.get(turmaId);
                        request.setAttribute("turma", turma);
                        request.setAttribute("msgError", "");
                        request.setAttribute("acao", acao);

                        rd = request.getRequestDispatcher("/views/professor/nota/formNota.jsp");
                        rd.forward(request, response);
                        break;
                }
        
        
            }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("btEnviar");
        String msgOperacao = "";
        String link = "/aplicacaoMVC/professor/NotaController?acao=Listar";

        try {
            Turma turma = new Turma();
            turma.setCodigoTurma(request.getParameter("codigo_turma"));
            turma.setProfessorId(Integer.parseInt(request.getParameter("professor_id")));
            turma.setDisciplinaId(Integer.parseInt(request.getParameter("disciplina_id")));
            turma.setAlunoId(Integer.parseInt(request.getParameter("aluno_id")));
            turma.setNota(Double.parseDouble(request.getParameter("nota")));

            TurmaDAO turmaDAO = new TurmaDAO();
            RequestDispatcher rd;

            switch (acao) {
                case "Incluir":
                case "Alterar":
                    turma.setId(Integer.parseInt(request.getParameter("id")));
                    turmaDAO.update(turma);
                    msgOperacao = "Alteração realizada com sucesso!";
                    break;
                case "Excluir":
                default:
                    msgOperacao = "Ação inválida.";
            }

            request.setAttribute("msgOperacaoRealizada", msgOperacao);
            request.setAttribute("link", link);
            rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("msgError", "Erro ao processar operação: " + e.getMessage());
            request.getRequestDispatcher("/views/comum/showMessage.jsp").forward(request, response);
        }
    }
}
