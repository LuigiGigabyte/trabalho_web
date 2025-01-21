<%@page import="entidade.Aluno"%>
<%@page import="model.AlunoDAO"%>
<%@page import="entidade.Disciplina"%>
<%@page import="model.DisciplinaDAO"%>
<%@page import="entidade.Turma"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Professor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Alteração de Nota</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="col-sm-6 offset-3 mt-5">
                <% Turma turma = new Turma();
                   turma = (Turma) request.getAttribute("turma");
                   out.println("<h1>Alterar Nota</h1>");
                   String msgError = (String) request.getAttribute("msgError");
                   String acao = (String) request.getAttribute("acao"); 
                    if ((msgError != null) && (!msgError.isEmpty())) {
                    %>
                    <div class="alert alert-danger" role="alert">
                    <%=msgError
                    %>
                    </div>
                    <% }%>

                <form action="/aplicacaoMVC/professor/NotaController" method="POST">
                    <input
                            type="hidden"
                            name="id"
                            value="<%=turma.getId()%>"
                            class="form-control"
                    />
                    <input
                            type="hidden"
                            name="professor_id"
                            value="<%=((Professor) session.getAttribute("professorLogado")).getId()%>"
                            class="form-control"
                    />
                    <div class="mb-3">
                        <label for="codigo_turma" class="form-label">Código da Turma</label>
                        <input maxlength="2" value="<%=turma.getCodigoTurma()%>" type="hidden" name="codigo_turma" class="form-control" placeholder="Código da turma">
                        <input maxlength="2" value="<%=turma.getCodigoTurma()%>" type="text" class="form-control" placeholder="Código da turma" disabled>
                    </div>

                    <div class="mb-3">
                        <label for="disciplina_id" class="form-label">Disciplina</label>
                            
                            <% 
                                DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
                                Disciplina disciplina = disciplinaDAO.get(turma.getDisciplinaId());
                            %>
                            <input value="<%=turma.getDisciplinaId()%>" type="hidden" name="disciplina_id" class="form-control" >
                            <input value="<%=disciplina.getNome()%>" type="text" name="disciplina_nome" class="form-control" disabled>
                    </div>

                    <div class="mb-3">
                        <label for="aluno_id" class="form-label">Aluno</label>
                            <% 
                                AlunoDAO alunoDAO = new AlunoDAO();
                                Aluno aluno = alunoDAO.get(turma.getAlunoId());
                            %>
                            <input value="<%=turma.getAlunoId()%>" type="hidden" name="aluno_id" class="form-control" >
                            <input value="<%=aluno.getNome()%>" type="text" name="aluno_nome" class="form-control" disabled>
                    </div>

                    <div class="mb-3">
                        <label for="nota" class="form-label">Nota</label>
                        <input  min="0" min="10" value="<%=turma.getNota()%>" type="number" name="nota" class="form-control" step="0.01" placeholder="Nota da turma" required>
                    </div>

                    <div class="row">
                        <div class="col-sm-2">
                            <input value="<%=acao%>" type="submit" name="btEnviar" class="btn btn-primary">
                        </div>
                        <div class="col-sm-2">
                            <a href="/aplicacaoMVC/professor/NotaController?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </div>
                </form>

            </div>
        </div>
        <script src="views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
