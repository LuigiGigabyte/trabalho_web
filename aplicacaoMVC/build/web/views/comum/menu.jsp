<%@page 
    contentType="text/html" 
    pageEncoding="UTF-8" 
    import="entidade.Administrador"
    import="entidade.Aluno"
    import="entidade.Professor"
%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/aplicacaoMVC/home">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <%
                    // testar se está logado
                    HttpSession sessao = request.getSession(false);
                    
                    if (sessao != null) {
                        Administrador AdministradorLogado = (Administrador) session.getAttribute("administrador");
                        Aluno alunoLogado = (Aluno) session.getAttribute("alunoLogado");
                        Professor professorLogado = (Professor) session.getAttribute("professorLogado");
                        if (AdministradorLogado != null) { %>
                            <a class="nav-link" href="/aplicacaoMVC/admin/AlunoController?acao=Listar">Alunos</a>
                            <a class="nav-link" href="/aplicacaoMVC/admin/ProfessorController?acao=Listar">Professores</a>
                            <a class="nav-link" href="/aplicacaoMVC/admin/AdministradorController?acao=Listar">Admin</a>
                            <a class="nav-link" href="/aplicacaoMVC/admin/DisciplinaController?acao=Listar">Disciplinas</a>
                            <a class="nav-link" href="/aplicacaoMVC/admin/TurmaController?acao=Listar">Turmas</a>
                            <a class="nav-link" href="/aplicacaoMVC/admin/RelatorioController">Relatório</a>
                            <a class="nav-link" href="/aplicacaoMVC/logOut">Logout</a>
                            
                    <%  }   else{
                                if(alunoLogado != null){ %>
                                    <a class="nav-link" href="/aplicacaoMVC/admin/TurmaController?acao=Listar">Turmas</a>
                                    <a class="nav-link" href="/aplicacaoMVC/aluno/NotaController">Notas</a>
                                    <a class="nav-link" href="/aplicacaoMVC/logOut">Logout</a>
                    <%          }   else{
                                        if(professorLogado != null){ %>
                                            <a class="nav-link" href="/aplicacaoMVC/professor/NotaController?acao=Listar">Notas</a>
                                            <a class="nav-link" href="/aplicacaoMVC/logOut">Logout</a>
                    <%                  } else {%>
                    
                                            <a class="nav-link" href="/aplicacaoMVC/AutenticaController?acao=Login">Login</a>
                    <%                  }
                                    }
                            }
                    }%>



            </div>
        </div>
    </div>
</nav>
