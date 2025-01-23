<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="entidade.Professor"%>
<%@page import="entidade.Disciplina"%>
<%@page import="model.DisciplinaDAO"%>
<%@page import="model.ProfessorDAO"%>
<%@page import="entidade.Turma"%>
<%@page import="entidade.Aluno"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista de Turmas</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="mt-5">
                <h2>Lista de Turmas</h2>
                <div class="table-responsive">
                    <h3>Turmas Disponíveis</h3>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Código da Turma</th>
                                <th scope="col">Disciplina</th>
                                <th scope="col">Professor</th>
                                <th scope="col">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% ArrayList<Turma> listaTurmas = (ArrayList<Turma>) request.getAttribute("listaTurmas"); %>
                            <% DisciplinaDAO disciplinaDAO = new DisciplinaDAO(); %>
                            <% ProfessorDAO professorDAO = new ProfessorDAO(); %>

                            <%
                                for (Turma turma : listaTurmas) {
                                    Disciplina disciplina = disciplinaDAO.get(turma.getDisciplinaId());
                                    Professor professor = professorDAO.get(turma.getProfessorId());
                            %>
                                <tr>
                                    <td><%= turma.getCodigoTurma() %></td>
                                    <td><%= disciplina.getNome() %></td>
                                    <td><%= professor.getNome() %></td>
                                    <td>
                                        <a href="/aplicacaoMVC/aluno/TurmaController?acao=Incluir&id=<%=turma.getId()%>&aluno=<%
                                            HttpSession sessao = request.getSession(false);
                                            if (sessao != null) {
                                                Aluno alunoLogado = (Aluno) sessao.getAttribute("alunoLogado");
                                                if (alunoLogado != null) {
                                                    out.print(alunoLogado.getId());
                                                }
                                            }
                                        %>"
                                           class="btn btn-warning">Inscrever-se</a>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>

                <!-- Lista de Turmas Inscritas -->
                <div class="table-responsive">
                    <h3>Turmas Inscritas</h3>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Código da Turma</th>
                                <th scope="col">Disciplina</th>
                                <th scope="col">Professor</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% ArrayList<Turma> listaTurmasInscritas = (ArrayList<Turma>) request.getAttribute("listaTurmasInscritas"); %>

                            <% 
                                for (Turma turma : listaTurmasInscritas) {
                                    Disciplina disciplina = disciplinaDAO.get(turma.getDisciplinaId());
                                    Professor professor = professorDAO.get(turma.getProfessorId());
                            %>
                                <tr>
                                    <td><%= turma.getCodigoTurma() %></td>
                                    <td><%= disciplina.getNome() %></td>
                                    <td><%= professor.getNome() %></td> 
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
