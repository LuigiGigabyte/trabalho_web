package controller.professor;

import controller.professor.*;
import entidade.Turma;
import entidade.Professor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ProfessorDAO;

@WebServlet("/professor/NotaController")
public class NotaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            ProfessorDAO professorDAO = new ProfessorDAO();
        
            HttpSession session = request.getSession();
            Professor professorLogado = (Professor) session.getAttribute("professorLogado");
            ArrayList<Turma> listaTurmas = professorDAO.getNotas(professorLogado);

            request.setAttribute("listaTurmas", listaTurmas);

            request.getRequestDispatcher("/views/professor/nota/listaNota.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msgError", "Erro ao processar operação: " + e.getMessage());
            request.getRequestDispatcher("/views/comum/showMessage.jsp").forward(request, response);
        }
    }
}
