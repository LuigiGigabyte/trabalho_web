package controller.admin;

import entidade.Turma;
import model.TurmaDAO; 
import model.ProfessorDAO; 
import model.DisciplinaDAO; 
import model.AlunoDAO; 
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TurmaController", urlPatterns = {"/admin/TurmaController"})
public class TurmaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String msgOperacao = "";
        String id = request.getParameter("id");

        Turma turma = null;
        TurmaDAO turmaDAO = new TurmaDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        AlunoDAO alunoDAO = new AlunoDAO();

        request.setAttribute("acao", acao);

        request.setAttribute("listaProfessores", professorDAO.getAll());
        request.setAttribute("listaDisciplinas", disciplinaDAO.getAll());
        request.setAttribute("listaAlunos", alunoDAO.getAll());


        RequestDispatcher rd;

        switch (acao) {
            case "Listar":
                ArrayList<Turma> listaTurmas = turmaDAO.getAll();
                request.setAttribute("listaTurmas", listaTurmas);
                rd = request.getRequestDispatcher("/views/admin/turma/listaTurmas.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                rd = request.getRequestDispatcher("/views/admin/turma/formTurma.jsp");
                rd.forward(request, response);
                break;
            case "Alterar":
                int turmaId = Integer.parseInt(request.getParameter("id"));
                turma = turmaDAO.get(turmaId);
                request.setAttribute("turma", turma);
                rd = request.getRequestDispatcher("/views/admin/turma/formTurma.jsp");
                rd.forward(request, response);
                break;
            case "Excluir":
                turmaId = Integer.parseInt(request.getParameter("id"));
                turmaDAO.delete(turmaId);
                request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso!");
                request.setAttribute("link", "/aplicacaoMVC/admin/TurmaController?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String msgOperacao = "";
        String link = "/aplicacaoMVC/admin/TurmaController?acao=Listar";

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
                    turmaDAO.insert(turma);
                    msgOperacao = "Cadastro realizado com sucesso!";
                    break;
                case "Alterar":
                    turma.setId(Integer.parseInt(request.getParameter("id")));
                    turmaDAO.update(turma);
                    msgOperacao = "Alteração realizada com sucesso!";
                    break;
                case "Excluir":
                    int turmaId = Integer.parseInt(request.getParameter("id"));
                    turmaDAO.delete(turmaId);
                    msgOperacao = "Exclusão realizada com sucesso!";
                    break;
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
