package controller.admin;

import entidade.Professor; // Importando a classe Professor
import model.ProfessorDAO; // Importando o DAO de Professor
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProfessorController", urlPatterns = {"/admin/ProfessorController"})
public class ProfessorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String msgOperacao = "";
        String id = request.getParameter("id");

        Professor professor = null;
        ProfessorDAO professorDAO = new ProfessorDAO();

        request.setAttribute("professor", professor);
        request.setAttribute("acao", acao);
        RequestDispatcher rd;

        switch (acao) {
            case "Listar":
                ArrayList<Professor> listaProfessores = professorDAO.getAll();
                request.setAttribute("listaProfessores", listaProfessores);

                rd = request.getRequestDispatcher("/views/admin/professor/listaProfessores.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                rd = request.getRequestDispatcher("/views/admin/professor/formProfessor.jsp");
                rd.forward(request, response);
                break;
            case "Excluir":
                int professorId = Integer.parseInt(request.getParameter("id"));
                professorDAO.delete(professorId);
                request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso!");
                request.setAttribute("link", "/aplicacaoMVC/admin/ProfessorController?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);
            case "Alterar":
                int idAlterar = Integer.parseInt(request.getParameter("id"));
                professor = professorDAO.get(idAlterar);
                request.setAttribute("professor", professor);
                rd = request.getRequestDispatcher("/views/admin/professor/formProfessor.jsp");
                rd.forward(request, response);
                break;
                
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("btEnviar");
        String msgOperacao = "";

        String link = "/aplicacaoMVC/admin/ProfessorController?acao=Listar";

        try {
            Professor professor = new Professor();

            professor.setNome(request.getParameter("nome"));
            professor.setEmail(request.getParameter("email"));
            professor.setCpf(request.getParameter("cpf"));
            professor.setSenha(request.getParameter("senha"));
            professor.setId(Integer.parseInt(request.getParameter("id")));

            ProfessorDAO professorDAO = new ProfessorDAO();
            RequestDispatcher rd;

            switch (acao) {
                case "Incluir":
                    professorDAO.insert(professor);
                    msgOperacao = "Cadastro realizado com sucesso!";
                    break;
                case "Alterar":
                    professorDAO.update(professor);
                    msgOperacao = "Alteração realizada com sucesso!";
                    break;
                case "Excluir":
                    int professorId = Integer.parseInt(request.getParameter("id"));
                    professorDAO.delete(professorId);
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
