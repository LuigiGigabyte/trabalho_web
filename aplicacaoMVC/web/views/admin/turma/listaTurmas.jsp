<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="entidade.Professor"%>
<%@page import="entidade.Disciplina"%>
<%@page import="model.DisciplinaDAO"%>
<%@page import="model.ProfessorDAO"%>
<%@page import="entidade.Turma"%>
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
                <h1>Área Restrita</h1>
                <h2>Lista de Turmas</h2>

                <a href="/aplicacaoMVC/admin/TurmaController?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                <div class="table-responsive">
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
                            <%
                 
                                ArrayList<Turma> listaTurmas = (ArrayList<Turma>) request.getAttribute("listaTurmas");
                                
   
                                DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
                                ProfessorDAO professorDAO = new ProfessorDAO();
                                

                                Map<String, ArrayList<Turma>> turmasPorCodigo = new HashMap<>();
                                
                                
                                for (Turma turma : listaTurmas) {
                                    ArrayList<Turma> turmasComMesmoCodigo = turmasPorCodigo.get(turma.getCodigoTurma());
                                    if (turmasComMesmoCodigo == null) {
                                        turmasComMesmoCodigo = new ArrayList<>();
                                        turmasPorCodigo.put(turma.getCodigoTurma(), turmasComMesmoCodigo);
                                    }
                                    turmasComMesmoCodigo.add(turma);
                                }


                                for (Map.Entry<String, ArrayList<Turma>> entry : turmasPorCodigo.entrySet()) {
                                    String codigoTurma = entry.getKey();
                                    ArrayList<Turma> turmasDoCodigo = entry.getValue();
                                    
     
                                    Turma turmaExemplo = turmasDoCodigo.get(0);
                                    Disciplina disciplina = disciplinaDAO.get(turmaExemplo.getDisciplinaId());
                                    Professor professor = professorDAO.get(turmaExemplo.getProfessorId());
                            %>
                                        <tr>
                                            <td><%= codigoTurma %></td>
                                            
                                            <td><%= disciplina.getNome() %></td>
                                            
                                            <td><%= professor.getNome() %></td>
                                            
                                            <td>
                                                <a href="/aplicacaoMVC/admin/TurmaController?acao=Alterar&id=<%=codigoTurma%>" class="btn btn-warning">Alterar</a>
                                                <a href="/aplicacaoMVC/admin/TurmaController?acao=Excluir&id=<%=codigoTurma%>" class="btn btn-danger">Excluir</a>
                                            </td>
                                        </tr>
                            <%  
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
