var editarT = document.getElementById('editarTurno')
editarT.addEventListener('click', verificar2) 

var criarT = document.getElementById('criarTurno')

function verificar2(){  
    
    // Desabilita o botão: criarTurno.
    document.getElementById('criarTurno').disabled = true;

    // Desabilita o botão: editarTurno.
    document.getElementById('editarTurno').disabled = true;

    // Troca o conteúdo (vazia) da <div>: formularioTurno.
    // Por um formulário: alterarTurno. 
    var formularioTurno = document.getElementById('formularioTurno') 
    formularioTurno.innerHTML = '<form id="alterarTurno"><fieldset id="dadosTurno"><legend>Edição de Turno</legend><p><label for="cIDp">ID: </label><input type="number" name="tIDp" id="cIDp" min="1"></p><p><label for="cNomeT">Nome do Turno: </label><input type="text" name="tNomeT" id="cNomeT" size="20" maxlength="20" placeholder="..."></p><p><label for="cHoraI">Hora de Início: </label><input type="time" name="tHoraI" id="cHoraI"></p><p><label for="cHoraF">Hora do Fim: </label><input type="time" name="tHoraF" id="cHoraF"></p></fieldset></form>'
    
    // Cria um botão.
    var elemento = document.createElement('button');

    // Define um ID para o botão criado (objetivo: possibilidade de referenciar o mesmo futuramente).
    elemento.setAttribute("id", "salvarTurno")

    // Na página principal, adiciona o botão: salvarTurno (obrigatório para haver a atualização de um mesmo).
    var button = document.getElementById('buttonTurno')
    button.append(elemento)
    elemento.innerHTML = 'Salvar Turno'     
    
    editarT.setAttribute("style", "background-color: black;")
    criarT.setAttribute("style", "background-color: black;")

    // Linka o evento 'click' no botão: salvarTurno.
    // "Chama" a função SALVAR.
    elemento.addEventListener('click', salvar2)

}     

function salvar2(){      
    
    // Remove o formulário: alterarTurno.
    var formularioTurno = document.getElementById('formularioTurno') 
    formularioTurno.removeChild(document.getElementById('alterarTurno'));

    // Remove o botão: salvarTurno.
    var button = document.getElementById('buttonTurno');
    button.removeChild(document.getElementById('salvarTurno'));

    // Habilita o botão: editarTurno.
    document.getElementById('editarTurno').disabled = false 

    // Habilita o botão: criarTurno.
    document.getElementById('criarTurno').disabled = false 

    editarT.setAttribute("style", "background-color: #31D0D0;")
    criarT.setAttribute("style", "background-color: #31D0D0;")
    
} 