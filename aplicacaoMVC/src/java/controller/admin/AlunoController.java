package controller.admin;

import entidade.Aluno; // Importando a classe Aluno
import model.AlunoDAO; // Importando o DAO de Aluno
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AlunoController", urlPatterns = {"/admin/AlunoController"})
public class AlunoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String id = request.getParameter("id");

        Aluno aluno = null;
        //if (id != null && !id.isEmpty()) {
        //   aluno = AlunoDAO.buscarPorId(Integer.parseInt(id));
        //}

        request.setAttribute("aluno", aluno);
        request.setAttribute("acao", acao);


        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/aluno/formAluno.jsp");
        rd.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String msgOperacao = "";
        String link = "/admin/AlunoController?acao=Listar";
        AlunoDAO alunoDAO = new AlunoDAO();
        try {
            Aluno aluno = new Aluno();
            
          //  String idParam = request.getParameter("id");
           // if (idParam != null && idParam.matches("\\d+")) {
           //     aluno.setId(Integer.parseInt(idParam));
           // } else {
           //     System.err.println("O parâmetro 'id' não é válido: " + idParam);
           // }

 aluno.setNome(request.getParameter("nome"));
            aluno.setEmail(request.getParameter("email"));
            aluno.setCelular(request.getParameter("celular"));
            aluno.setCpf(request.getParameter("cpf"));
            aluno.setSenha(request.getParameter("senha"));
            aluno.setEndereco(request.getParameter("endereco"));
            aluno.setCidade(request.getParameter("cidade"));
            aluno.setBairro(request.getParameter("bairro"));
            aluno.setCep(request.getParameter("cep"));
            switch (acao) {
                case "Incluir":
                    alunoDAO.insert(aluno);
                    msgOperacao = "Cadastro realizado com sucesso!";
                    break;
                case "Alterar":
                    alunoDAO.update(aluno);
                    msgOperacao = "Alteração realizada com sucesso!";
                    break;
                case "Excluir":
                    int alunoId = Integer.parseInt(request.getParameter("id"));
                    alunoDAO.delete(alunoId);
                    msgOperacao = "Exclusão realizada com sucesso!";
                    break;
                default:
                    msgOperacao = "Ação inválida.";
            }

            request.setAttribute("msgOperacaoRealizada", msgOperacao);
            request.setAttribute("link", link);
            RequestDispatcher rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("msgError", "Erro ao processar operação: " + e.getMessage());
            request.getRequestDispatcher("/views/comum/showMessage.jsp").forward(request, response);
        }
    }
}
