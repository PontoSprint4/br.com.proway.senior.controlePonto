var addP = document.getElementById('addPessoa')
addP.addEventListener('click', verificar) 

function verificar(){  
    // Desabilita o botão: addPessoa.
    document.getElementById('addPessoa').disabled = true;

    // Troca o conteúdo (vazia) da <div>: formularioPessoa.
    // Por um formulário: registroPessoa. 
    var formularioPessoa = document.getElementById('formularioPessoa') 
    formularioPessoa.innerHTML = '<form id="registroPessoa"><fieldset id="dadosPessoa"><legend>Registro de Turno</legend><p><label for="cNomeT">Nome do Turno: </label><input type="text" name="tNomeT" id="cNomeT" size="20" maxlength="20" placeholder="..."></p><p><label for="cHoraI">Hora de Início: </label><input type="time" name="tHoraI" id="cHoraI"></p><p><label for="cHoraF">Hora do Fim: </label><input type="time" name="tHoraF" id="cHoraF"></p></fieldset></form>'
    
    // Cria um botão.
    var elemento = document.createElement('button');

    // Define um ID para o botão criado (objetivo: possibilidade de referenciar o mesmo futuramente).
    elemento.setAttribute("id", "salvarPessoa")

    // Na página determinada, adiciona o botão: salvarPessoa (obrigatório para haver o registro de um mesmo).
    var button = document.getElementById('buttonPessoaTurno')
    button.append(elemento)
    elemento.innerHTML = 'Salvar Pessoa/s' 
    
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