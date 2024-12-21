<%@page import="entidade.Aluno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Cadastro de Aluno</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
        <script src="../../public/scriptValidaCampos.js"></script>
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="col-sm-6 offset-3 mt-5">
                <% Aluno aluno = new Aluno();
                    String acao = (String) request.getAttribute("acao"); 
                    switch(acao) { 
                        case "Incluir": 
                            out.println("<h1>Incluir Aluno</h1>"); 
                            break; 
                        case "Alterar":
                            aluno = (Aluno) request.getAttribute("aluno");
                            out.println("<h1>Alterar Aluno</h1>");
                            break; 
                    }; 
                    String msgError = (String) request.getAttribute("msgError"); 
                    if ((msgError != null) && (!msgError.isEmpty())) {
                    %>
                    <div class="alert alert-danger" role="alert">
                    <%=msgError
                    %>
                    </div>
                    <% }%>

                <form action="/aplicacaoMVC/admin/AlunoController" method="POST" id="formulario">
                    <input
                            type="hidden"
                            name="id"
                            value="<%=aluno.getId()%>"
                            class="form-control"
                    />
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome</label>
                        <input value="<%=aluno.getNome()%>" type="text" name="nome" class="form-control" placeholder="Nome do aluno" required>
                    
                        <label for="email" class="form-label">Email</label>
                        <input value="<%=aluno.getEmail()%>" type="email" name="email" class="form-control" placeholder="Email do aluno" required>
                    
                        <label for="celular" class="form-label">Celular</label>
                        <input value="<%=aluno.getCelular()%>" minlength=11 maxlength=11 type="text" name="celular" class="form-control" placeholder="21999999999" required>
                    
                        <label for="cpf" class="form-label">CPF</label>
                        <input value="<%=aluno.getCpf()%>" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" type="text" name="cpf" class="form-control" placeholder="999.999.999-99" required>
                    
                        <label for="senha" class="form-label">Senha</label>
                        <input value="<%=aluno.getSenha()%>" type="password" name="senha" class="form-control" placeholder="Senha do aluno" required>
                    
                        <label for="endereco" class="form-label">Endereço</label>
                        <input value="<%=aluno.getEndereco()%>" type="text" name="endereco" class="form-control" placeholder="Endereço" required>
                    
                        <label for="cidade" class="form-label">Cidade</label>
                        <input value="<%=aluno.getCidade()%>" type="text" name="cidade" class="form-control" placeholder="Cidade" required>
                    
                        <label for="bairro" class="form-label">Bairro</label>
                        <input value="<%=aluno.getBairro()%>" type="text" name="bairro" class="form-control" placeholder="Bairro" required>
                    
                        <label for="cep" class="form-label">CEP</label>
                        <input value="<%=aluno.getCep()%>" minlength=8 maxlength=8 type="text" name="cep" class="form-control" placeholder="00000-000" required>
                    </div>
                    <div >
                        
                        <input type="submit" name="btEnviar"  value="<%=acao%>" class="btn btn-primary">
                        
                        
                        <a href="/aplicacaoMVC/admin/AlunoController?acao=Listar" class="btn btn-danger">Retornar</a>
                        
                    </div>
                </form>

            </div>
        </div>
        <script src="views/bootstrap/bootstrap.bundle.min.js"></script>
        <script>
        
        console.log("teste");

        function validarCPF(cpf) {
            cpf = cpf.replace(/[^\d]+/g, ''); // Remove qualquer caractere não numérico

            if (cpf.length !== 11) return false; // O CPF deve ter 11 dígitos

            // Validação de CPF
            let soma = 0;
            let resto;

            for (let i = 1; i <= 9; i++) soma += parseInt(cpf.charAt(i - 1)) * (11 - i);
            resto = (soma * 10) % 11;
            if ((resto === 10 || resto === 11) ? parseInt(cpf.charAt(9)) !== 0 : parseInt(cpf.charAt(9)) !== resto) return false;

            soma = 0;
            for (let i = 1; i <= 10; i++) soma += parseInt(cpf.charAt(i - 1)) * (12 - i);
            resto = (soma * 10) % 11;
            if ((resto === 10 || resto === 11) ? parseInt(cpf.charAt(10)) !== 0 : parseInt(cpf.charAt(10)) !== resto) return false;

            return true;
          }

           // Função para validar o celular com exatamente 11 números e sem letras
          function validarCelular(celular) {
            const celularRegex = /^\d{11}$/; // Verifica se o celular tem exatamente 11 números
            return celularRegex.test(celular);
          }

          // Função para validar se o valor contém letras ou espaços
          function validarNumerosSemEspacos(value) {
            const regex = /^\d+$/; // Apenas números são permitidos
            return regex.test(value);
          }


          // Função de validação do formulário
          document.getElementById("formulario").addEventListener("submit", function(event) {
            let isValid = true;

            // Obtém todos os campos do formulário
            const nome = document.querySelector("[name='nome']").value;
            const email = document.querySelector("[name='email']").value;
            const celular = document.querySelector("[name='celular']").value;
            const cpf = document.querySelector("[name='cpf']").value;
            const senha = document.querySelector("[name='senha']").value;
            const endereco = document.querySelector("[name='endereco']").value;
            const cidade = document.querySelector("[name='cidade']").value;
            const bairro = document.querySelector("[name='bairro']").value;
            const cep = document.querySelector("[name='cep']").value;

            // Verificação de campos vazios
            if (!nome || !email || !celular || !cpf || !senha || !endereco || !cidade || !bairro || !cep) {
              alert("Por favor, preencha todos os campos.");
              isValid = false;
            }

            // Validação do CPF
            if (!validarCPF(cpf)) {
              alert("CPF inválido.");
              isValid = false;
            }

                      // Validação do formato de celular (11 dígitos numéricos)
           if (!validarCelular(celular.replace(/\D/g, ''))) {
             alert("Celular inválido. O número deve conter exatamente 11 dígitos numéricos, sem espaços ou caracteres especiais.");
             isValid = false;
           }

           // Validação de campos que devem conter apenas números (sem letras ou espaços)
           if (!validarNumerosSemEspacos(celular.replace(/\D/g, ''))) {
             alert("O campo Celular não pode conter letras ou espaços.");
             isValid = false;
           }

           if (!validarNumerosSemEspacos(cep.replace(/\D/g, ''))) {
                  alert("O campo CEP não pode conter letras ou espaços.");
                  isValid = false;
                }

            // Se houver algum erro, impede o envio do formulário
            if (!isValid) {
              event.preventDefault();
            }
          });
        
        
        </script>

    </body>
</html>
