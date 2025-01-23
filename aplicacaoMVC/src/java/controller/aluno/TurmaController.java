package controller.aluno;

import entidade.Aluno;
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
import javax.servlet.http.HttpSession;

@WebServlet("/aluno/TurmaController")
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
                HttpSession sessao = request.getSession(false);
                Aluno alunoLogado = (Aluno) sessao.getAttribute("alunoLogado");
                ArrayList<Turma> listaTurmas = turmaDAO.getTurmasNaoInscritas(alunoLogado.getId());
                request.setAttribute("listaTurmas", listaTurmas);
                ArrayList<Turma> listaTurmasInscritas = turmaDAO.getTurmasInscritas(alunoLogado.getId());
                request.setAttribute("listaTurmasInscritas", listaTurmasInscritas);
                rd = request.getRequestDispatcher("/views/aluno/turma/listaTurmas.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                int turmaId = Integer.parseInt(request.getParameter("id"));
                int alunoId = Integer.parseInt(request.getParameter("aluno"));
                turma = turmaDAO.get(turmaId);
                turma.setAlunoId(alunoId);
                request.setAttribute("turma", turma);
                if(turmaDAO.getTurmaCheia(turma.getProfessorId(), turma.getDisciplinaId(), turma.getCodigoTurma()) == true){
                    msgOperacao = "Não há vagas para essa turma";
                }
                else{
                    turmaDAO.insertAluno(turma, alunoId);
                    msgOperacao = "Inscrição realizada com sucesso!";
                }
                request.setAttribute("msgOperacaoRealizada", msgOperacao);
                request.setAttribute("link", "/aplicacaoMVC/aluno/TurmaController?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);
                break;
        }
    }
}
