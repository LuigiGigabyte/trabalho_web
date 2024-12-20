package controller.admin;

import entidade.Administrador; 
import model.AdministradorDAO; 
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AdminController", urlPatterns = {"/admin/AdministradorController"})
public class AdministradorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String msgOperacao = "";
        String id = request.getParameter("id");

        Administrador admin = null;
        AdministradorDAO adminDAO = new AdministradorDAO();

        request.setAttribute("admin", admin);
        request.setAttribute("acao", acao);
        RequestDispatcher rd;

        switch (acao) {
            case "Listar":
                ArrayList<Administrador> listaAdmins = adminDAO.getAll();
                request.setAttribute("listaAdmins", listaAdmins);

                rd = request.getRequestDispatcher("/views/admin/administrador/listaAdmins.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                rd = request.getRequestDispatcher("/views/admin/administrador/formAdmin.jsp");
                rd.forward(request, response);
                break;
            case "Excluir":
                try{
                    int adminId = Integer.parseInt(id);
                    adminDAO.delete(adminId);
                    request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso!");
                    request.setAttribute("link", "/aplicacaoMVC/admin/AdministradorController?acao=Listar");
                    rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                    rd.forward(request, response);
                }catch (Exception e) {
                    request.setAttribute("msgError", "Erro ao processar operação: " + e.getMessage());
                    request.getRequestDispatcher("/views/comum/showMessage.jsp").forward(request, response);
                }
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String msgOperacao = "";

        String link = "/aplicacaoMVC/admin/AdministradorController?acao=Listar";

        try {
            Administrador admin = new Administrador();

            admin.setNome(request.getParameter("nome"));
            admin.setCpf(request.getParameter("cpf"));
            admin.setSenha(request.getParameter("senha"));
            admin.setAprovado(request.getParameter("aprovado"));
            admin.setEndereco(request.getParameter("endereco"));

            AdministradorDAO adminDAO = new AdministradorDAO();
            RequestDispatcher rd;

            switch (acao) {
                case "Incluir":
                    adminDAO.insert(admin);
                    msgOperacao = "Cadastro realizado com sucesso!";
                    break;
                case "Alterar":
                    admin.setId(Integer.parseInt(request.getParameter("id")));
                    adminDAO.update(admin);
                    msgOperacao = "Alteração realizada com sucesso!";
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
