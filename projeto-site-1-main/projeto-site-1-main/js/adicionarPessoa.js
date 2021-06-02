var addP = document.getElementById('addPessoa')
addP.addEventListener('click', verificar) 

function verificar(){  
    // Desabilita o botão: addPessoa.
    document.getElementById('addPessoa').disabled = true;

    // Troca o conteúdo (vazia) da <div>: formularioPessoa.
    // Por um formulário: registroPessoa. 
    var formularioPessoa = document.getElementById('formularioPessoa') 
    formularioPessoa.innerHTML = '<form id="registroPessoa"><fieldset id="dadosPessoa"><legend>Adicionar colaborador(es): </legend><p><label for="cIDp">ID(s): </label><textarea name="tIDp" id="cIDp" cols="50" rows="10" placeholder="Para a inserçaõ de vários IDs, separe cada um deles com vírgula..."></textarea></p></fieldset></form>'
    
    // Cria um botão.
    var elemento = document.createElement('button');

    // Define um ID para o botão criado (objetivo: possibilidade de referenciar o mesmo futuramente).
    elemento.setAttribute("id", "salvarPessoa")

    // Na página determinada, adiciona o botão: salvarPessoa (obrigatório para haver o registro de um mesmo).
    var button = document.getElementById('buttonPessoaTurno')
    button.append(elemento)
    elemento.innerHTML = 'Salvar Pessoa(s)' 
    
    // Linka o evento 'click' no botão: salvarPessoa.
    // "Chama" a função SALVAR.
    elemento.addEventListener('click', salvar)

}     

function salvar(){      
    
    // Remove o formulário: registroPessoa.
    var formularioPessoa = document.getElementById('formularioPessoa') 
    formularioPessoa.removeChild(document.getElementById('registroPessoa'));

    // Remove o botão: salvarPessoa.
    var button = document.getElementById('buttonPessoaTurno');
    button.removeChild(document.getElementById('salvarPessoa'));

    // Habilita o botão: addPessoa.
    document.getElementById('addPessoa').disabled = false 
    
} 