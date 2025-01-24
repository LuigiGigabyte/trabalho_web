package controller.admin;

import entidade.Disciplina;
import entidade.Turma;
import model.DisciplinaDAO; 
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TurmaDAO;

@WebServlet(name = "DisciplinaController", urlPatterns = {"/admin/DisciplinaController"})
public class DisciplinaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String msgOperacao = "";
        String id = request.getParameter("id");

        Disciplina disciplina = null;
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

        request.setAttribute("disciplina", disciplina);
        request.setAttribute("acao", acao);
        RequestDispatcher rd;

        switch (acao) {
            case "Listar":
                ArrayList<Disciplina> listaDisciplinas = disciplinaDAO.getAll();
                request.setAttribute("listaDisciplinas", listaDisciplinas);

                rd = request.getRequestDispatcher("/views/admin/disciplina/listaDisciplinas.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                rd = request.getRequestDispatcher("/views/admin/disciplina/formDisciplina.jsp");
                rd.forward(request, response);
                break;
            case "Excluir":
                int disciplinaId = Integer.parseInt(request.getParameter("id"));
                TurmaDAO turmadao = new TurmaDAO();
                disciplina = disciplinaDAO.get(disciplinaId);
                ArrayList<Turma> turmasExcluir = disciplinaDAO.getTurmas(disciplina);
                for (Turma turma : turmasExcluir){
                    turmadao.delete(turma.getId());
                }
                disciplinaDAO.delete(disciplinaId);
                request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso!");
                request.setAttribute("link", "/aplicacaoMVC/admin/DisciplinaController?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);
                break;
            case "Alterar":
                int idAlterar = Integer.parseInt(request.getParameter("id"));
                disciplina = disciplinaDAO.get(idAlterar);
                request.setAttribute("disciplina", disciplina);
                rd = request.getRequestDispatcher("/views/admin/disciplina/formDisciplina.jsp");
                rd.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("btEnviar");
        String msgOperacao = "";

        String link = "/aplicacaoMVC/admin/DisciplinaController?acao=Listar";

        try {
            Disciplina disciplina = new Disciplina();

            // Obtendo dados do formulário
            disciplina.setNome(request.getParameter("nome"));
            disciplina.setRequisito(request.getParameter("requisito"));
            disciplina.setEmenta(request.getParameter("ementa"));
            disciplina.setCargaHoraria(Integer.parseInt(request.getParameter("cargaHoraria")));

            DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
            RequestDispatcher rd;

            switch (acao) {
                case "Incluir":
                    disciplinaDAO.insert(disciplina);
                    msgOperacao = "Cadastro realizado com sucesso!";
                    break;
                case "Alterar":
                    disciplina.setId(Integer.parseInt(request.getParameter("id")));
                    disciplinaDAO.update(disciplina);
                    msgOperacao = "Alteração realizada com sucesso!";
                    break;
                case "Excluir":
                    int disciplinaId = Integer.parseInt(request.getParameter("id"));
                    disciplinaDAO.delete(disciplinaId);
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
